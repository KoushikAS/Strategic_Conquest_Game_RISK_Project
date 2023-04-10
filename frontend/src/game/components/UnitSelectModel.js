import Modal from "react-bootstrap/Modal";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import UnitNumSlider from "./UnitNumSlider";
import {Button} from "react-bootstrap";
import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import axios, {get} from "axios";
import {API_URL} from "../../config/config";
import Slider from "rc-slider";

const UnitSelectModel = (props) => {

    const territories = props.territories;
    const sourceName = props.source;
    const targetName = props.target;

    const getTerritory = (name) => {
        return territories.find((territory) => territory.name === name);
    };
    const getUnit = (unitType, territoryName) => {
        return getTerritory(territoryName).units.find((unit) => unit.unitType = unitType);
    };

    const [sliderValue0, setSliderValue0] = useState(0);
    const [sliderValue1, setSliderValue1] = useState(0);
    const [sliderValue2, setSliderValue2] = useState(0);
    const [sliderValue3, setSliderValue3] = useState(0);
    const [sliderValue4, setSliderValue4] = useState(0);
    const [sliderValue5, setSliderValue5] = useState(0);
    const [sliderValue6, setSliderValue6] = useState(0);

    const handleSlider0Change = (event) => {
        setSliderValue0(event.target.value);
    };
    const handleSlider1Change = (event) => {
        setSliderValue1(event.target.value);
    };
    const handleSlider2Change = (event) => {
        setSliderValue2(event.target.value);
    };
    const handleSlider3Change = (event) => {
        setSliderValue3(event.target.value);
    };
    const handleSlider4Change = (event) => {
        setSliderValue4(event.target.value);
    };
    const handleSlider5Change = (event) => {
        setSliderValue5(event.target.value);
    };
    const handleSlider6Change = (event) => {
        setSliderValue6(event.target.value);
    };

    const navigate = useNavigate();
    const [showModal, setShowModal] = useState(true);
    const closeModal = () => {
        setShowModal(false);
        navigate("/");
    };

    const [orderArray, setOrderArray] = useState([]);
    const packageData = (sliderValue, unitType) => {
        if(sliderValue!=0){
            const data = {
                sourceTerritoryId: getTerritory(sourceName).id,
                destinationTerritoryId: getTerritory(targetName).id,
                unitNum: sliderValue,
                unitType: unitType,
                orderType: "MOVE"
            }
            setOrderArray(prevState => [...prevState, data]);
        }
    };

    const handleSubmitOrder = async(e) => {
        e.preventDefault();
        try{
            packageData(sliderValue0, "Basic");
            packageData(sliderValue1, "Infantry");
            packageData(sliderValue2, "Cavalry");
            packageData(sliderValue3, "Artillery");
            packageData(sliderValue4, "Army Aviation");
            packageData(sliderValue5, "Special Forces");
            packageData(sliderValue6, "Combat Engineer");

            const data = {
                orders: orderArray
            }
            const params = {
                playerId: getTerritory(sourceName).owner.id,
            }
            console.log(data);
            const response = await axios.post(`${API_URL}/submitOrder`,data, {params});
            console.log(response.data);
            setShowModal(false);
        }catch (error){
            console.log(error);
        }
    }

    if(!sourceName||!targetName) return;

    return (
        <>
            <Modal show={showModal}>
                <Modal.Header>
                    <Modal.Title>
                        Select units to move from {sourceName} to {targetName}
                    </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <div>
                        <UnitNumSlider unitType="Basic" unitNum={getUnit("LEVEL0", sourceName).unitNum} sliderValue={sliderValue0} handleSliderChange={handleSlider0Change}/>
                        <UnitNumSlider unitType="Infantry" unitNum={getUnit("LEVEL1", sourceName).unitNum} sliderValue={sliderValue1} handleSliderChange={handleSlider1Change}/>
                        <UnitNumSlider unitType="Cavalry" unitNum={getUnit("LEVEL2", sourceName).unitNum} sliderValue={sliderValue2} handleSliderChange={handleSlider2Change}/>
                        <UnitNumSlider unitType="Artillery" unitNum={getUnit("LEVEL3", sourceName).unitNum} sliderValue={sliderValue3} handleSliderChange={handleSlider3Change}/>
                        <UnitNumSlider unitType="Army Aviation" unitNum={getUnit("LEVEL4", sourceName).unitNum} sliderValue={sliderValue4} handleSliderChange={handleSlider4Change}/>
                        <UnitNumSlider unitType="Special Forces" unitNum={getUnit("LEVEL5", sourceName).unitNum} sliderValue={sliderValue5} handleSliderChange={handleSlider5Change}/>
                        <UnitNumSlider unitType="Combat Engineer" unitNum={getUnit("LEVEL6", sourceName).unitNum} sliderValue={sliderValue6} handleSliderChange={handleSlider6Change}/>
                        <div style={{ display: "flex", justifyContent: "center" }}>
                            <Button onClick={handleSubmitOrder} style={{ backgroundColor: "#26BC26", marginRight: "50px", border: "none"}}>Confirm</Button>
                            <Button onClick={closeModal} style={{ backgroundColor: "#D33431", marginLeft: "50px", border: "none"}}>Cancel</Button>
                        </div>
                    </div>
                </Modal.Body>
            </Modal>
        </>
    );
};

export default UnitSelectModel;