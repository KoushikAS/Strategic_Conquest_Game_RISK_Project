import React, { useContext, useCallback, useState } from "react";
import { Row, Col, Button, Modal } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { PlayerContext } from "../context/PlayerProvider";
import { OrderContext } from "../context/OrderProvider";
import { AuthContext } from "../../auth/AuthProvider";
import axios from "axios";

const PlayerOrderButtons = (props) => {
  const navigate = useNavigate();
  const { hasResearched, setHasDone } = useContext(PlayerContext);
  const { orders, removeAllOrders } = useContext(OrderContext);
  const { user } = useContext(AuthContext);

  // for modals
  const [showSuccess, setShowSuccess] = useState(false);
  const handleCloseSuccess = () => setShowSuccess(false);
  const handleShowSuccess = () => setShowSuccess(true);
  const [showFailure, setShowFailure] = useState(false);
  const handleCloseFailure = () => setShowFailure(false);
  const handleShowFailure = () => setShowFailure(true);

  const handleAttack = () => {
    navigate("/attack", { state: { gameId: props.gameId } });
  };

  const handleMove = () => {
    navigate("/move", { state: { gameId: props.gameId } });
  }

  const handleResearch = () => {
    navigate("/research", { state: { gameId: props.gameId } });
  }

  const handleBack = () => {
    navigate("/gameList");
  }

  const handleDone = useCallback(async () => {
    try {
      const config = {
        headers: { Authorization: `Bearer ${user.accessToken}` }
      }
      const response = await axios.post(`submitOrder/?playerId=${props.player.id}`, { orders }, config);
      console.log(`Done response: ${response.data}`);
      setHasDone(true);
      handleShowSuccess();
    } catch (error) {
      console.log(error);
      handleShowFailure();
      removeAllOrders();
    }
  }, [props.player.id, user.accessToken, orders, setHasDone, removeAllOrders])

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
            style={moveButtonStyles}
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
        <Col>
          <Button
            onClick={handleBack}
            variant="danger"
            size="lg"
            style={{ fontWeight: "bold" }}
          >
            Game List
          </Button>
        </Col>
        <Col>
          <Button
            onClick={handleDone}
            variant="success"
            size="lg"
            style={{ fontWeight: "bold" }}
          >
            Done
          </Button>
        </Col>
      </Row>

      <Modal show={showSuccess} onHide={handleCloseSuccess}>
        <Modal.Header closeButton>
          <Modal.Title style={{ color: "green" }}>
            Success!
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
          Your orders have been placed. Please wait for others to complete their orders...
        </Modal.Body>
      </Modal>

      <Modal show={showFailure} onHide={handleCloseFailure}>
        <Modal.Header closeButton>
          <Modal.Title style={{ color: "red" }}>
            Oops!
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
          Please check your orders to make sure they comply with the game rules.
        </Modal.Body>
      </Modal>
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

const researchButtonStyles = {
  ...orderButtonStyles,
  backgroundColor: "#FFC107",
};

const upgradeButtonStyles = {
  ...orderButtonStyles,
  backgroundColor: "#A020F0",
};

export default PlayerOrderButtons;
