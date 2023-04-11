import React, { useState, useEffect, useCallback } from "react";
import Map from "../maps/Map";
import GameBanner from "./components/GameBanner";
import PlayerInfoCard from "./components/info_cards/PlayerInfoCard";
import PlayerOrderButtons from "./components/PlayerOrderButtons";
import { Container, Row, Col, Button, Card } from "react-bootstrap";
import axios from "axios";
import { useLocation } from 'react-router-dom';
import { useContext } from "react";
import { AuthContext } from "../auth/AuthProvider";
import { OrderContext } from "./context/OrderProvider";
import { PlayerContext } from "./context/PlayerProvider";

const GameView = () => {
  const { user } = useContext(AuthContext);
  const { orders, removeAllOrders } = useContext(OrderContext);
  const { hasDone, setHasDone } = useContext(PlayerContext);
  console.log("orders in GameView: ", orders);
  const [game, setGame] = useState();
  const [player, setPlayer] = useState();
  const [isLoading, setIsLoading] = useState(true);
  const location = useLocation();
  const gameId = location.state.gameId;


  const fetchGame = useCallback(async () => {
    try {
      const config = {
        headers: { Authorization: `Bearer ${user.accessToken}` }
      }
      const response = await axios.get(`getGameForUser/${gameId}?userId=${user.userId}`, config);
      console.log(`Current game: ${response.data.game}`);
      console.log(`Current player: ${response.data.player}`);
      setGame(response.data.game);
      setPlayer(response.data.player)
      setIsLoading(false);
    } catch (error) {
      console.log(error);
    }
  }, [gameId, user.accessToken, user.userId])

  useEffect(() => {
    fetchGame();
  }, [fetchGame]);

  if (isLoading) {
    return <div>Loading...</div>;
  }

  const handleRefresh = async () => {
    try {
      const config = {
        headers: { Authorization: `Bearer ${user.accessToken}` }
      }
      const response = await axios.get(`getGameForUser/${gameId}?userId=${user.userId}`, config);
      console.log(response.data)
      if (!response.data.playerDone) {
        setHasDone(false);
        removeAllOrders();
        await fetchGame();
      }
    } catch (error) {
      console.log(error);
    }
  }

  if (hasDone) {
    return (
      <Container className="vh-100 d-flex align-items-center justify-content-center">
        <Card style={cardStyles}>
          <Card.Body>
            <Card.Title style={titleStyles}>Waiting for other players to complete their round...</Card.Title>
            <Button
              onClick={handleRefresh}
              variant="success"
              size="lg"
              style={buttonStyles}
            >
              Refresh
            </Button>
          </Card.Body>
        </Card>
      </Container>
    )
  }

  return (
    <Container>
      <Row>
        <Col md={9}>
          <GameBanner view="home" />
          <Map game={game} />
        </Col>
        <Col md={3}>
          <PlayerInfoCard player={player} game={game} />
          <br />
          <PlayerOrderButtons player={player} gameId={gameId} />
        </Col>
      </Row>
    </Container>
  );
};

const cardStyles = {
  width: "30rem",
  height: "25rem",
  borderRadius: "2rem",
  boxShadow: "0 0 10px rgba(0, 0, 0, 0.6)",
};
const titleStyles = {
  fontSize: "2rem",
  color: "#77A6F7",
};
const buttonStyles = {
  backgroundColor: "#77A6F7",
  fontWeight: "bold",
  outline: "none",
  border: "none",
  borderRadius: "40px",
  width: "8rem"
}

export default GameView;
