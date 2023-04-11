import Modal from "react-bootstrap/Modal";
import UnitNumSlider from "./UnitNumSlider";
import { Button } from "react-bootstrap";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useContext } from "react";
import { OrderContext } from "../context/OrderProvider";

const UpgradeUnitSelectModal = (props) => {
    const { addManyOrders } = useContext(OrderContext);

    const territories = props.territories;
    const sourceName = props.source;

    const getTerritory = (name) => {
        return territories.find((territory) => territory.name === name);
    };
    const getUnit = (unitType, territoryName) => {
        return getTerritory(territoryName).units.find((unit) => unit.unitType === unitType);
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
        navigate("/", { state: { gameId: props.gameId } });
    };

    const orderArray = []
    const packageData = (sliderValue, unitType) => {
        if (sliderValue !== 0) {
            const data = {
                sourceTerritoryId: getTerritory(sourceName).id,
                unitNum: sliderValue,
                unitType: unitType,
                orderType: props.orderType
            }
            orderArray.push(data);
        }
    };

    const handleConfirmOrder = (e) => {
        e.preventDefault();
        packageData(sliderValue0, "Basic");
        packageData(sliderValue1, "Infantry");
        packageData(sliderValue2, "Cavalry");
        packageData(sliderValue3, "Artillery");
        packageData(sliderValue4, "Army Aviation");
        packageData(sliderValue5, "Special Forces");
        packageData(sliderValue6, "Combat Engineer");
        console.log("orderArray: ", orderArray);
        // store the order in context
        addManyOrders(orderArray);
        setShowModal(false);
        navigate("/", { state: { gameId: props.gameId } });
    }

    if (!sourceName) return;

    return (
        <>
            <Modal show={showModal}>
                <Modal.Header>
                    <Modal.Title>
                        Select units to {props.orderType} in {sourceName}
                    </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <div>
                        <UnitNumSlider unitType="Basic" unitNum={getUnit("LEVEL0", sourceName).unitNum} sliderValue={sliderValue0} handleSliderChange={handleSlider0Change} />
                        <UnitNumSlider unitType="Infantry" unitNum={getUnit("LEVEL1", sourceName).unitNum} sliderValue={sliderValue1} handleSliderChange={handleSlider1Change} />
                        <UnitNumSlider unitType="Cavalry" unitNum={getUnit("LEVEL2", sourceName).unitNum} sliderValue={sliderValue2} handleSliderChange={handleSlider2Change} />
                        <UnitNumSlider unitType="Artillery" unitNum={getUnit("LEVEL3", sourceName).unitNum} sliderValue={sliderValue3} handleSliderChange={handleSlider3Change} />
                        <UnitNumSlider unitType="Army Aviation" unitNum={getUnit("LEVEL4", sourceName).unitNum} sliderValue={sliderValue4} handleSliderChange={handleSlider4Change} />
                        <UnitNumSlider unitType="Special Forces" unitNum={getUnit("LEVEL5", sourceName).unitNum} sliderValue={sliderValue5} handleSliderChange={handleSlider5Change} />
                        <UnitNumSlider unitType="Combat Engineer" unitNum={getUnit("LEVEL6", sourceName).unitNum} sliderValue={sliderValue6} handleSliderChange={handleSlider6Change} />
                        <br />
                        <div style={{ display: "flex", justifyContent: "center" }}>
                            <Button onClick={handleConfirmOrder} style={confirmButtonStyles} size="lg">Confirm</Button>
                            <Button onClick={closeModal} style={cancelButtonStyles} size="lg">Cancel</Button>
                        </div>
                    </div>
                </Modal.Body>
            </Modal>
        </>
    );
};

const confirmButtonStyles = {
    backgroundColor: "#26BC26", marginRight: "50px", border: "none"
}

const cancelButtonStyles = {
    ...confirmButtonStyles,
    backgroundColor: "#D33431"
}

export default UpgradeUnitSelectModal;