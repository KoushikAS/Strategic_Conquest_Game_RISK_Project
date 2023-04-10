import React from "react";
import { Row, Col, Card } from "react-bootstrap";
import { GiFruitBowl } from "react-icons/gi";
import { HiOutlineDesktopComputer } from "react-icons/hi";

const PlayerInfoCard = (props) => {
  const { game } = props;
  // TODO: Remove this dummy data
  const player = {
    "name": "Red",
    "status": "PLAYING",
    "foodResource": 650,
    "techResource": 650,
    "id": 1
  }
  return (
    <Card>
      <Card.Body style={playerInfoCardStyles}>
        <Card.Title>Round {game.roundNo}</Card.Title>
        <Card.Text>You are the {player.name} player</Card.Text>
        <Row>
          <Col md={6}>
            <GiFruitBowl size={30} />
            <p>{player.foodResource}</p>
          </Col>
          <Col md={6}>
            <HiOutlineDesktopComputer size={30} />
            <p>{player.techResource}</p>
          </Col>
        </Row>
      </Card.Body>
    </Card>
  )
};

const playerInfoCardStyles = {
  backgroundColor: "#FFCCCB",
  textAlign: "center",
  boxShadow: "0px 0px 10px rgba(0, 0, 0, 0.3)",
};

export default PlayerInfoCard;
