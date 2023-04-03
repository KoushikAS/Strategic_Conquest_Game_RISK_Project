import React, { useEffect } from "react";
import Map from "../maps/Map";
import GameBanner from "./components/GameBanner";
import PlayerInfoCard from "./components/PlayerInfoCard";
import PlayerOrderButtons from "./components/PlayerOrderButtons";
import { Container, Row, Col } from "react-bootstrap";
import axios from "axios";

const AttackView = () => {
  const [game, setGame] = React.useState();
  const [isLoading, setIsLoading] = React.useState(true);
  const [sourceTerritory, setSourceTerritory] = React.useState();
  const [targetTerritory, setTargetTerritory] = React.useState();

  useEffect(() => {
    const fetchGame = async () => {
      try {
        let response = await axios.get("http://localhost:12345/createGame");
        const gameId = response.data.id;
        console.log(`Game ID: ${gameId}`);
        response = await axios.get(`http://localhost:12345/getGame/${gameId}`);
        setGame(response.data);
        setIsLoading(false);
      } catch (error) {
        console.log(error);
      }
    };
    fetchGame();
  }, []);

  if (isLoading) {
    return <div>Loading...</div>;
  }

  const currentView = sourceTerritory ? "attack-to" : "attack-from";

  return (
    <Container>
      <Row>
        <Col md={9}>
          <GameBanner view={currentView} />
          <Map game={game} handleSource={setSourceTerritory} />
        </Col>
        <Col md={3}>
          <PlayerInfoCard />
          <br />
        </Col>
      </Row>
    </Container>
  );
};

export default AttackView;
