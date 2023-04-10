import React, { useEffect, useState } from "react";
import Map from "../maps/Map";
import GameBanner from "./components/GameBanner";
import PlayerInfoCard from "./components/PlayerInfoCard";
import UnitSelectModel from "./components/UnitSelectModel";
import { Container, Row, Col } from "react-bootstrap";
import axios from "axios";
import { API_URL } from "../config/config";
import AttackToInfoCard from "./components/AttackToInfoCard";

const MoveView = () => {
    const [game, setGame] = React.useState();
    const [isLoading, setIsLoading] = React.useState(true);
    const [sourceTerritory, setSourceTerritory] = React.useState();
    const [targetTerritory, setTargetTerritory] = React.useState();

    useEffect(() => {
        const fetchGame = async () => {
            try {
                //TODO: no need to create game
                let response = await axios.post(`${API_URL}/createGame/3`);
                const gameId = response.data.id;
                console.log(`Game ID: ${gameId}`);
                response = await axios.get(`${API_URL}/getGame/${gameId}`);
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

    const currentView = sourceTerritory ? "move-to" : "move-from";
    const setSourceOrTarget = sourceTerritory ? setTargetTerritory: setSourceTerritory;


    return (
        <>
        <Container>
            <Row>
                <Col md={9}>
                    <GameBanner view={currentView} />
                    <Map game={game} handleSourceOrTarget={setSourceOrTarget} />
                </Col>
                <Col md={3}>
                    <PlayerInfoCard />
                    <br />
                    <div>Move from: {sourceTerritory}</div>
                    <br />
                    {/*<AttackToInfoCard source={sourceTerritory} territories={game.map.territories}/>*/}
                    {/*<br />*/}
                    <div>To: {targetTerritory}</div>
                </Col>
            </Row>
        </Container>
            < UnitSelectModel source = {sourceTerritory} target = {targetTerritory} territories = {game.map.territories}/>
        </>
    );
};

export default MoveView;