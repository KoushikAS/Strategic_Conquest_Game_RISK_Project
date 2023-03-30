import React from "react";
import { GiFruitBowl } from "react-icons/gi";
import { HiOutlineDesktopComputer } from "react-icons/hi";
import { GiSwordman } from "react-icons/gi";
import { Col, Row } from "react-bootstrap";

const TerritoryBasicView = (props) => {
  const getTotalUnits = () => {
    let totalUnits = 0;
    for (let key in props.territory.units) {
      totalUnits += props.territory.units[key];
    }
    return totalUnits;
  };

  return (
    <div>
      <div
        className="text-center"
        style={{ margin: "0.8rem", fontSize: "18px" }}
      >
        {props.territory.name}
      </div>

      <Row className="text-center">
        <Col md={4}>
          <GiSwordman size={25} />
        </Col>
        <Col md={4}>
          <GiFruitBowl size={25} />
        </Col>
        <Col md={4}>
          <HiOutlineDesktopComputer size={25} />
        </Col>
      </Row>
      <Row className="text-center">
        <Col md={4}>{getTotalUnits()}</Col>
        <Col md={4}>+{props.territory.foodGenNum}</Col>
        <Col md={4}>+{props.territory.techGenNum}</Col>
      </Row>
    </div>
  );
};
export default TerritoryBasicView;
