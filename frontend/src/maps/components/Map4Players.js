import React from "react";
import TerritoryView from "./TerritoryView";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Container from "react-bootstrap/Container";

const Map4Players = (props) => {
  const { territories } = props;
  const getTerritory = (name) => {
    return territories.find((territory) => territory.name === name);
  };

  return (
      <Container>
        <Row>
          <Col></Col>
          <Col></Col>
          <Col>
            <TerritoryView
                key="Dachshund"
                territory={getTerritory("Dachshund")}
                handleSourceOrTarget={props.handleSourceOrTarget}
            />
          </Col>
          <Col></Col>
          <Col>
            <TerritoryView key="Beagle" territory={getTerritory("Beagle")} handleSourceOrTarget={props.handleSourceOrTarget}/>
          </Col>
          <Col></Col>
          <Col></Col>
        </Row>
        <br></br>
        <Row>
          <Col></Col>
          <Col></Col>
          <Col></Col>
          <Col>
            <TerritoryView
                key="Rottweiler"
                territory={getTerritory("Rottweiler")}
                handleSourceOrTarget={props.handleSourceOrTarget}
            />
          </Col>
          <Col></Col>
          <Col>
            <TerritoryView key="Bulldog" territory={getTerritory("Bulldog")} handleSourceOrTarget={props.handleSourceOrTarget}/>
          </Col>
          <Col></Col>
          <Col>
            <TerritoryView key="Poodle" territory={getTerritory("Poodle")} handleSourceOrTarget={props.handleSourceOrTarget}/>
          </Col>
          <Col></Col>
          <Col></Col>
          <Col></Col>
        </Row>
        <br></br>
        <br></br>
        <Row>
          <Col>
            <TerritoryView key="Brittany" territory={getTerritory("Brittany")} handleSourceOrTarget={props.handleSourceOrTarget}/>
          </Col>
          <Col></Col>
          <Col></Col>
          <Col>
            <TerritoryView key="Akita" territory={getTerritory("Akita")} handleSourceOrTarget={props.handleSourceOrTarget}/>
          </Col>
          <Col></Col>
          <Col></Col>
          <Col></Col>
          <Col></Col>
          <Col></Col>
          <Col>
            <TerritoryView key="Labrador" territory={getTerritory("Labrador")} handleSourceOrTarget={props.handleSourceOrTarget}/>
          </Col>
          <Col></Col>
          <Col></Col>
          <Col></Col>
          <Col></Col>
          <Col></Col>
          <Col>
            <TerritoryView key="Mastiff" territory={getTerritory("Mastiff")} handleSourceOrTarget={props.handleSourceOrTarget}/>
          </Col>
          <Col></Col>
          <Col></Col>
          <Col>
            <TerritoryView key="Pug" territory={getTerritory("Pug")} handleSourceOrTarget={props.handleSourceOrTarget}/>
          </Col>
        </Row>
        <br></br>
        <br></br>
        <Row>
          <Col></Col>
          <Col></Col>
          <Col>
            <TerritoryView key="Havanese" territory={getTerritory("Havanese")} handleSourceOrTarget={props.handleSourceOrTarget}/>
          </Col>
          <Col></Col>
          <Col>
            <TerritoryView key="Boxer" territory={getTerritory("Boxer")} handleSourceOrTarget={props.handleSourceOrTarget}/>
          </Col>
          <Col></Col>
          <Col></Col>
          <Col>
            <TerritoryView key="Collie" territory={getTerritory("Collie")} handleSourceOrTarget={props.handleSourceOrTarget}/>
          </Col>
          <Col></Col>
          <Col>
            <TerritoryView key="Vizsla" territory={getTerritory("Vizsla")} handleSourceOrTarget={props.handleSourceOrTarget}/>
          </Col>
          <Col></Col>
          <Col></Col>
          <Col></Col>
        </Row>
        <br></br>
        <br></br>
        <Row>
          <Col>
            <TerritoryView key="Sheepdog" territory={getTerritory("Sheepdog")} handleSourceOrTarget={props.handleSourceOrTarget}/>
          </Col>
          <Col></Col>
          <Col>
            <TerritoryView key="Spaniel" territory={getTerritory("Spaniel")} handleSourceOrTarget={props.handleSourceOrTarget}/>
          </Col>
          <Col></Col>
          <Col></Col>
          <Col></Col>
          <Col>
            <TerritoryView key="Dalmatian" territory={getTerritory("Dalmatian")} handleSourceOrTarget={props.handleSourceOrTarget}/>
          </Col>
          <Col></Col>
          <Col></Col>
          <Col></Col>
          <Col>
            <TerritoryView key="Chihuahua" territory={getTerritory("Chihuahua")} handleSourceOrTarget={props.handleSourceOrTarget}/>
          </Col>
          <Col></Col>
          <Col>
            <TerritoryView key="Maltese" territory={getTerritory("Maltese")} handleSourceOrTarget={props.handleSourceOrTarget}/>
          </Col>
        </Row>
        <br></br>
        <br></br>
        <Row>
          <Col></Col>
          <Col></Col>
          <Col></Col>
          <Col>
            <TerritoryView key="Papillon" territory={getTerritory("Papillon")} handleSourceOrTarget={props.handleSourceOrTarget}/>
          </Col>
          <Col></Col>
          <Col>
            <TerritoryView key="Setter" territory={getTerritory("Setter")} handleSourceOrTarget={props.handleSourceOrTarget}/>
          </Col>
          <Col></Col>
          <Col>
            <TerritoryView key="Samoyed" territory={getTerritory("Samoyed")} handleSourceOrTarget={props.handleSourceOrTarget}/>
          </Col>
          <Col></Col>
          <Col></Col>
          <Col></Col>
        </Row>
        <br></br>
        <Row>
          <Col></Col>
          <Col></Col>
          <Col>
            <TerritoryView key="Bullmastiff" territory={getTerritory("Bullmastiff")} handleSourceOrTarget={props.handleSourceOrTarget}/>
          </Col>
          <Col></Col>
          <Col>
            <TerritoryView key="Whippet" territory={getTerritory("Whippet")} handleSourceOrTarget={props.handleSourceOrTarget}/>
          </Col>
          <Col></Col>
          <Col></Col>
        </Row>
      </Container>
  );
};

export default Map4Players;
