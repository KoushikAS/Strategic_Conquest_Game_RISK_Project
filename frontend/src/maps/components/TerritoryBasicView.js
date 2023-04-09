import React from "react";
import { GiFruitBowl } from "react-icons/gi";
import { HiOutlineDesktopComputer } from "react-icons/hi";
import { GiSwordman } from "react-icons/gi";
import { Col, Row } from "react-bootstrap";

const TerritoryBasicView = (props) => {
  const territory = props.territory;
  const getTotalUnits = () => {
    let totalUnits = 0;
    for (let key in territory.units) {
      totalUnits += territory.units[key];
    }
    return totalUnits;
  };

  const handleClick = (e) => {
    // stop propagation if attacking
    if (props.handleSource) {
      e.stopPropagation();
    } else {
      return;
    }
    console.log(e.target.innerText);
    props.handleSource(e.target.innerText);
  };

  return (
    <div style={{ fontSize: "12px" }}>
      <div
        className="text-center"
        onClick={handleClick}
        style={{ margin: "0.8rem", fontSize: "18px" }}
      >
        {territory.name}
      </div>

      <Row className="text-center">
        <Col md={4}>
          <GiSwordman size={20} />
        </Col>
        <Col md={4}>
          <GiFruitBowl size={20} />
        </Col>
        <Col md={4}>
          <HiOutlineDesktopComputer size={20} />
        </Col>
      </Row>
      <Row className="text-center">
        <Col md={4}>{territory.unitNum}</Col>
        <Col md={4}>+{5}</Col>
        <Col md={4}>+{6}</Col>
        {/* <Col md={4}>+{territory.foodGenNum}</Col>
        <Col md={4}>+{territory.techGenNum}</Col> */}
      </Row>
    </div>
  );
};
export default TerritoryBasicView;
