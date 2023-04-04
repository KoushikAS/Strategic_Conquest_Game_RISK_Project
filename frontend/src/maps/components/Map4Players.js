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
          />
        </Col>
        <Col></Col>
        <Col>
          <TerritoryView key="Beagle" territory={getTerritory("Beagle")} />
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
          <TerritoryView key="Bulldog" territory={getTerritory("Bulldog")} />
        </Col>
        <Col>
          <TerritoryView key="Poodle" territory={getTerritory("Poodle")} />
        </Col>
        <Col></Col>
        <Col></Col>
      </Row>

      <Row>
        <Col>
          <TerritoryView key="Brittany" territory={getTerritory("Brittany")} />
        </Col>
        <Col>
          <TerritoryView key="Akita" territory={getTerritory("Akita")} />
        </Col>
        <Col></Col>
        <Col>
          <TerritoryView key="Labrador" territory={getTerritory("Labrador")} />
        </Col>
        <Col></Col>
        <Col>
          <TerritoryView key="Mastiff" territory={getTerritory("Mastiff")} />
        </Col>
        <Col>
          <TerritoryView key="Pug" territory={getTerritory("Pug")} />
        </Col>
      </Row>

      <Row>
        <Col></Col>
        <Col>
          <TerritoryView key="Havanese" territory={getTerritory("Havanese")} />
        </Col>
        <Col>
          <TerritoryView key="Boxer" territory={getTerritory("Boxer")} />
        </Col>
        <Col></Col>
        <Col>
          <TerritoryView key="Collie" territory={getTerritory("Collie")} />
        </Col>
        <Col>
          <TerritoryView key="Vizsla" territory={getTerritory("Vizsla")} />
        </Col>
        <Col></Col>
      </Row>

      <Row>
        <Col>
          <TerritoryView key="Sheepdog" territory={getTerritory("Sheepdog")} />
        </Col>
        <Col>
          <TerritoryView key="Spaniel" territory={getTerritory("Spaniel")} />
        </Col>
        <Col></Col>
        <Col>
          <TerritoryView
            key="Dalmatian"
            territory={getTerritory("Dalmatian")}
          />
        </Col>
        <Col></Col>
        <Col>
          <TerritoryView
            key="Chihuahua"
            territory={getTerritory("Chihuahua")}
          />
        </Col>
        <Col>
          <TerritoryView key="Maltese" territory={getTerritory("Maltese")} />
        </Col>
      </Row>

      <Row>
        <Col></Col>
        <Col></Col>
        <Col>
          <TerritoryView key="Papillon" territory={getTerritory("Papillon")} />
        </Col>
        <Col>
          <TerritoryView key="Setter" territory={getTerritory("Setter")} />
        </Col>
        <Col>
          <TerritoryView key="Samoyed" territory={getTerritory("Samoyed")} />
        </Col>
        <Col></Col>
        <Col></Col>
      </Row>

      <Row>
        <Col></Col>
        <Col></Col>
        <Col>
          <TerritoryView
            key="Bullmastiff"
            territory={getTerritory("Bullmastiff")}
          />
        </Col>
        <Col></Col>
        <Col>
          <TerritoryView key="Whippet" territory={getTerritory("Whippet")} />
        </Col>
        <Col></Col>
        <Col></Col>
      </Row>
    </Container>
  );
};

export default Map4Players;
