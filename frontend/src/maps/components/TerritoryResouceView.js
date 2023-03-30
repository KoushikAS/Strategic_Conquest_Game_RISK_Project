import React from "react";
import { GiFruitBowl } from "react-icons/gi";
import { GiSwordman } from "react-icons/gi";
import { RiComputerFill } from "react-icons/ri";
import { Col, Row } from "react-bootstrap";

const TerritoryResouceView = (props) => {
  return (
    <div>
      <Row>
        <Col md={6}>
          <GiFruitBowl size={30} />
        </Col>
        <Col md={6}>
          <p>+5</p>
        </Col>
      </Row>
      <Row>
        <Col md={6}>
          <RiComputerFill size={30} />
        </Col>
        <Col md={6}>
          <p>+12</p>
        </Col>
      </Row>
    </div>
  );
};

export default TerritoryResouceView;
