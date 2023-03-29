import React from "react";
import { Row, Col, Card } from "react-bootstrap";
import { GiFruitBowl } from "react-icons/gi";
import { RiComputerFill } from "react-icons/ri";

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
          <RiComputerFill size={30} />
          <p>3</p>
        </Col>
      </Row>
    </Card.Body>
  </Card>
);

const playerInfoCardStyles = {
  backgroundColor: "#FFCCCB",
  textAlign: "center",
};

export default PlayerInfoCard;
