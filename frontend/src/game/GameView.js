import React, { useEffect } from "react";
import Map from "../maps/Map";
import GameBanner from "./components/GameBanner";
import PlayerInfoCard from "./components/PlayerInfoCard";
import PlayerOrderButtons from "./components/PlayerOrderButtons";
import { Container, Row, Col } from "react-bootstrap";
import axios from "axios";
import { API_URL } from "../config/config";

const GameView = () => {
  const [game, setGame] = React.useState();
  const [isLoading, setIsLoading] = React.useState(true);

  useEffect(() => {
    const fetchGame = async () => {
      try {
        let response = await axios.get(`${API_URL}/createGame/3`);
        const gameId = response.data.id;
        console.log(`Game ID: ${gameId}`);
        response = await axios.get(`${API_URL}/getGame/${gameId}`);
        setGame(response.data);
        setIsLoading(false);
      } catch (error) {
        console.log(error);
      }
    }
    fetchGame();
  }, []);

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
          <PlayerInfoCard />
          <br />
          <PlayerOrderButtons />
        </Col>
      </Row>
    </Container>
  );
};

export default GameView;
