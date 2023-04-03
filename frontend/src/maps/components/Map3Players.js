import React from "react";
import TerritoryView from "./TerritoryView";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Container from "react-bootstrap/Container";

const Map3Players = (props) => {
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
          <TerritoryView key="Labrador" territory={getTerritory("Labrador")} />
        </Col>
        <Col></Col>
        <Col>
          <TerritoryView key="Bulldog" territory={getTerritory("Bulldog")} />
        </Col>
        <Col></Col>
        <Col></Col>
      </Row>
      <Row>
        <Col></Col>
        <Col></Col>
        <Col>
          <TerritoryView
            key="Rottweiler"
            territory={getTerritory("Rottweiler")}
          />
        </Col>
        <Col>
          <TerritoryView
            key="Dachshund"
            territory={getTerritory("Dachshund")}
          />
        </Col>
        <Col>
          <TerritoryView key="Beagle" territory={getTerritory("Beagle")} />
        </Col>
        <Col></Col>
        <Col></Col>
      </Row>
      <Row>
        <Col>
          <TerritoryView key="Boxer" territory={getTerritory("Boxer")} />
        </Col>
        <Col>
          <TerritoryView key="Spaniel" territory={getTerritory("Spaniel")} />
        </Col>
        <Col></Col>
        <Col>
          <TerritoryView key="Poodle" territory={getTerritory("Poodle")} />
        </Col>
        <Col></Col>
        <Col>
          <TerritoryView key="Pug" territory={getTerritory("Pug")} />
        </Col>
        <Col>
          <TerritoryView key="Mastiff" territory={getTerritory("Mastiff")} />
        </Col>
      </Row>
      <Row>
        <Col></Col>
        <Col>
          <TerritoryView key="Brittany" territory={getTerritory("Brittany")} />
        </Col>
        <Col>
          <TerritoryView key="Havanese" territory={getTerritory("Havanese")} />
        </Col>
        <Col></Col>
        <Col>
          <TerritoryView key="Vizsla" territory={getTerritory("Vizsla")} />
        </Col>
        <Col>
          <TerritoryView
            key="Chihuahua"
            territory={getTerritory("Chihuahua")}
          />
        </Col>
        <Col></Col>
      </Row>
      <Row>
        <Col>
          <TerritoryView key="Akita" territory={getTerritory("Akita")} />
        </Col>
        <Col>
          <TerritoryView key="Sheepdog" territory={getTerritory("Sheepdog")} />
        </Col>
        <Col></Col>
        <Col></Col>
        <Col></Col>
        <Col>
          <TerritoryView key="Maltese" territory={getTerritory("Maltese")} />
        </Col>
        <Col>
          <TerritoryView key="Collie" territory={getTerritory("Collie")} />
        </Col>
      </Row>
    </Container>
  );
};

export default Map3Players;
