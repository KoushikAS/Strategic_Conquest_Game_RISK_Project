import React from "react";
import { Row, Col, Card } from "react-bootstrap";
import { GiFruitBowl } from "react-icons/gi";
import { HiOutlineDesktopComputer } from "react-icons/hi";

const PlayerInfoCard = () => (
  <Card>
    <Card.Body style={playerInfoCardStyles}>
      <Card.Title>Round 0</Card.Title>
      <Card.Text>You are the RED player</Card.Text>
      <Row>
        <Col md={6}>
          <GiFruitBowl size={30} />
          <p>18</p>
        </Col>
        <Col md={6}>
          <HiOutlineDesktopComputer size={30} />
          <p>3</p>
        </Col>
      </Row>
    </Card.Body>
  </Card>
);

const playerInfoCardStyles = {
  backgroundColor: "#FFCCCB",
  textAlign: "center",
  boxShadow: "0px 0px 10px rgba(0, 0, 0, 0.3)",
};

export default PlayerInfoCard;
