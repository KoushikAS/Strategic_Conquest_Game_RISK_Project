import React from "react";
import TerritoryView from "./TerritoryView";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Container from "react-bootstrap/Container";

const Map2Players = (props) => {
  const { territories } = props;
  const getTerritory = (name) => {
    return territories.find((territory) => territory.name === name);
  };

  return (
    <Container>
      <Row>
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
      </Row>

      <Row>
        <Col>
          <TerritoryView key="Labrador" territory={getTerritory("Labrador")} />
        </Col>
        <Col>
          <TerritoryView key="Poodle" territory={getTerritory("Poodle")} />
        </Col>
        <Col>
          <TerritoryView key="Bulldog" territory={getTerritory("Bulldog")} />
        </Col>
      </Row>

      <Row>
        <Col>
          <TerritoryView key="Boxer" territory={getTerritory("Boxer")} />
        </Col>
        <Col>
          <TerritoryView key="Havanese" territory={getTerritory("Havanese")} />
        </Col>
        <Col>
          <TerritoryView key="Spaniel" territory={getTerritory("Spaniel")} />
        </Col>
      </Row>

      <Row>
        <Col>
          <TerritoryView key="Sheepdog" territory={getTerritory("Sheepdog")} />
        </Col>
        <Col>
          <TerritoryView key="Akita" territory={getTerritory("Akita")} />
        </Col>
        <Col>
          <TerritoryView key="Brittany" territory={getTerritory("Brittany")} />
        </Col>
      </Row>
    </Container>
  );
};

export default Map2Players;
