import React from "react";
import { Card } from "react-bootstrap";

const GameBanner = (props) => {
  const getBannerText = () => {
    switch (props.view) {
      case "game":
        return "What would you like to do?";
      case "attack-from":
        return "Select one of your territories to attack from.";
      case "attack-to":
        return "Select an adjacent territory to attack.";
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