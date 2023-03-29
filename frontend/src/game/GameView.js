import React from "react";
import DummyMap from "../maps/Map";
import GameBanner from "./components/GameBanner";
import PlayerInfoCard from "./components/PlayerInfoCard";
import PlayerOrderButtons from "./components/PlayerOrderButtons";
import { Container, Row, Col } from "react-bootstrap";

const GameView = () => (
  <Container>
    <Row>
      <Col md={9}>
        <GameBanner />
        <br />
        <DummyMap />
      </Col>
      <Col md={3}>
        <PlayerInfoCard />
        <br />
        <PlayerOrderButtons />
        
      </Col>
    </Row>
  </Container>
);



export default GameView;
