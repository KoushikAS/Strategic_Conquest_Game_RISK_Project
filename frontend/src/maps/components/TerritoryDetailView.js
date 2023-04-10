import React from "react";
import { GiSwordman, GiSwordwoman, GiBowman, GiCaveman, GiPikeman, GiStrongMan, GiPyromaniac } from "react-icons/gi";
import { Col, Row } from "react-bootstrap";

const TerritoryDetailView = (props) => {
  const units = props.territory.units;
  const iconSize = 35;
  const rowStyles = {
    height: "3rem",
  };
  const getUnitIcon = (unitType) => {
    switch (unitType) {
      case "LEVEL0":
        return <GiSwordman size={iconSize} />;
      case "LEVEL1":
        return <GiSwordwoman size={iconSize} />;
      case "LEVEL2":
        return <GiBowman size={iconSize} />;
      case "LEVEL3":
        return <GiPikeman size={iconSize} />;
      case "LEVEL4":
        return <GiCaveman size={iconSize} />;
      case "LEVEL5":
        return <GiStrongMan size={iconSize} />;
      case "LEVEL6":
        return <GiPyromaniac size={iconSize} />;
      default:
        return <GiSwordman size={iconSize} />;
    }
  };
  return (
    <div>
      {units.map((unit) => {
        return (
          <Row key={unit.unitType} className="text-center" style={rowStyles}>
            <Col md={4}>
              {getUnitIcon(unit.unitType)}
            </Col>
            <Col md={4}>{unit.unitType} Units</Col>
            <Col md={4}>{unit.unitNum}</Col>
          </Row>
        )
      })}
    </div>
  );
};

export default TerritoryDetailView;
