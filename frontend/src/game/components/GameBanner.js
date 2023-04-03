import React from "react";
import { Card } from "react-bootstrap";

const GameBanner = () => (
  <Card>
    <Card.Body style={bannerStyles}>
      <Card.Title>What would you like to do?</Card.Title>
    </Card.Body>
  </Card>
);

const bannerStyles = {
    textAlign: "center",
    backgroundColor: "#D9D9D9",
    boxShadow: "0px 0px 10px rgba(0, 0, 0, 0.3)",
    height: "50px",
}

export default GameBanner;
