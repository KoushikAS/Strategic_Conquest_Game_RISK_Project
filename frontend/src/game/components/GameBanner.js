import React from "react";
import { Card } from "react-bootstrap";

const GameBanner = (props) => {
  const getBannerText = () => {
    switch (props.view) {
      case "game":
        return "What would you like to do?";
      case "attack-from":
        return "Click the name of one of your territories to attack from.";
      case "attack-to":
        return "Click the name of an adjacent territory to attack.";
      case "move-from":
        return "Click the name of one of your territories to move units from.";
      case "move-to":
        return "Clink the name of one of your territories to move units to.";
      default:
        return "Welcome to Really Interesting Strategic Conquest (RISC)!";
    }
  };

  return (
    <Card>
      <Card.Body style={bannerStyles}>
        <Card.Title>{getBannerText()}</Card.Title>
      </Card.Body>
    </Card>
  );
};

const bannerStyles = {
  textAlign: "center",
  backgroundColor: "#D9D9D9",
  boxShadow: "0px 0px 10px rgba(0, 0, 0, 0.3)",
  height: "50px",
};

export default GameBanner;
