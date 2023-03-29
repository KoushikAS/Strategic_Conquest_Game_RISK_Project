import React from "react";
import DummyMap from "../maps/DummyMap";
import PlayerInfoCard from "./components/PlayerInfoCard";
import PlayerOrderButtons from "./components/PlayerOrderButtons";
import { Container, Row, Col } from "react-bootstrap";

const GameView = () => (
  <Container>
    <Row>
      <Col md={8}>
        <DummyMap />
      </Col>
      <Col md={4}>
        <PlayerInfoCard />
        <br />
        <PlayerOrderButtons />
        
      </Col>
    </Row>
  </Container>
);



export default GameView;
