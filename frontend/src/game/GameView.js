import React from "react";
import DummyMap from "../maps/DummyMap";
import { Container, Row, Col, Card, Button } from 'react-bootstrap';

const GameView = () => (
    <Container>
        <Row>
            <Col md={8}>
                <DummyMap />
            </Col>
            <Col md={4}>
                <Card>
                    <Card.Body>
                        {/* Card content here */}
                    </Card.Body>
                </Card>
                <Row>
                    <Col md={6}>
                        <Button variant="primary" block>Primary</Button>
                    </Col>
                    <Col md={6}>
                        <Button variant="secondary" block>Secondary</Button>
                    </Col>
                    <Col md={6}>
                        <Button variant="success" block>Success</Button>
                    </Col>
                    <Col md={6}>
                        <Button variant="danger" block>Danger</Button>
                    </Col>
                </Row>
                <Button variant="outline-dark" block>Rectangular Button</Button>
            </Col>
        </Row>
    </Container>
);

export default GameView;