import React, { useContext } from "react";
import { Row, Col, Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { PlayerContext } from "../context/PlayerProvider";

const PlayerOrderButtons = (props) => {
  const navigate = useNavigate();
  const { hasResearched } = useContext(PlayerContext);

  const handleAttack = () => {
    navigate("/attack", { state: { gameId: props.gameId } });
  };

  const handleMove = () => {
    navigate("/move", { state: { gameId: props.gameId } });
  }

  const handleResearch = () => {
    navigate("/research", { state: { gameId: props.gameId } });
  }

  return (
    <>
      <Row className="text-center">
        <Col md={6}>
          <Button
            onClick={handleMove}
            className="rounded-circle"
            style={moveButtonStyles}
            size="lg"
          >
            Move
          </Button>
        </Col>
        <Col md={6}>
          <Button
            onClick={handleAttack}
            className="rounded-circle"
            style={attackButtonStyles}
            size="lg"
          >
            Attack
          </Button>
        </Col>
      </Row>
      <br />
      <Row className="text-center">
        <Col md={6}>
          {!hasResearched && <Button
            onClick={handleResearch}
            className="rounded-circle"
            style={researchButtonStyles}
            size="lg"
          >
            Research
          </Button>}
        </Col>
        <Col md={6}>
          <Button
            // onClick={handleUpgrade}
            className="rounded-circle"
            style={upgradeButtonStyles}
            size="lg"
          >
            Upgrade
          </Button>
        </Col>
      </Row>
      <Row className="text-center" style={{ marginTop: "80%" }}>
        <Button
          variant="success"
          size="lg"
          style={{ fontWeight: "bold" }}
          block="true"
        >
          Done
        </Button>
      </Row>
    </>
  );
};

const orderButtonStyles = {
  height: "4rem",
  width: "8rem",
  color: "white",
  fontWeight: "bold",
  outline: "none",
  border: "none",
  boxShadow: "0px 0px 10px rgba(0, 0, 0, 0.3)",
};

const moveButtonStyles = {
  ...orderButtonStyles,
  backgroundColor: "#17A2B8",
};

const attackButtonStyles = {
  ...orderButtonStyles,
  backgroundColor: "#DC3545",
};

const researchButtonStyles = {
  ...orderButtonStyles,
  backgroundColor: "#FFC107",
};

const upgradeButtonStyles = {
  ...orderButtonStyles,
  backgroundColor: "#A020F0",
};

export default PlayerOrderButtons;
