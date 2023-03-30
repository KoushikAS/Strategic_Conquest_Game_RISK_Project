import React from "react";
import { GiSwordman, GiSwordwoman, GiBowman, GiCaveman } from "react-icons/gi";
import { Col, Row } from "react-bootstrap";

const TerritoryDetailView = (props) => {
  const iconSize = 35;
  const rowStyles = {
    height: "3rem",
  };
  return (
    <div>
      <Row className="text-center" style={rowStyles}>
        <Col md={4}>
          <GiSwordman size={iconSize} />
        </Col>
        <Col md={4}>Basic Units</Col>
        <Col md={4}>{props.territory.units.basic}</Col>
      </Row>

      <Row className="text-center" style={rowStyles}>
        <Col md={4}>
          <GiSwordwoman size={iconSize} />
        </Col>
        <Col md={4}>Infantry Units</Col>
        <Col md={4}>{props.territory.units.infantry}</Col>
      </Row>

      <Row className="text-center" style={rowStyles}>
        <Col md={4}>
          <GiBowman size={iconSize} />
        </Col>
        <Col md={4}>Cavalry Units</Col>
        <Col md={4}>{props.territory.units.cavalry}</Col>
      </Row>

      <Row className="text-center" style={rowStyles}>
        <Col md={4}>
          <GiCaveman size={iconSize} />
        </Col>
        <Col md={4}>Artillery Units</Col>
        <Col md={4}>{props.territory.units.artillery}</Col>
      </Row>
    </div>
  );
};

export default TerritoryDetailView;
