import React from "react";
import { GiFruitBowl } from "react-icons/gi";
import { HiOutlineDesktopComputer } from "react-icons/hi";
import { Col, Row } from "react-bootstrap";

const TerritoryBasicView = (props) => {
  return (
    <div>
      <Row>{props.territory.name}</Row>
      <Row>
        <Col md={6}>
          <GiFruitBowl size={20} />
        </Col>
        <Col md={6}>
          <p>+5</p>
        </Col>
      </Row>
      <Row>
        <Col md={6}>
          <HiOutlineDesktopComputer size={20} />
        </Col>
        <Col md={6}>
          <p>+12</p>
        </Col>
      </Row>
    </div>
  );
};
export default TerritoryBasicView;
