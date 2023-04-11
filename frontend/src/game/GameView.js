import React, { useState, useEffect, useCallback } from "react";
import Map from "../maps/Map";
import GameBanner from "./components/GameBanner";
import PlayerInfoCard from "./components/info_cards/PlayerInfoCard";
import PlayerOrderButtons from "./components/PlayerOrderButtons";
import { Container, Row, Col } from "react-bootstrap";
import axios from "axios";
import { useLocation } from 'react-router-dom';
import { useContext } from "react";
import { AuthContext } from "../auth/AuthProvider";
import { OrderContext } from "./context/OrderProvider";

const GameView = () => {
  const { user } = useContext(AuthContext);
  const { orders } = useContext(OrderContext);
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

export default GameView;
