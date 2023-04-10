import React, { useEffect, useState, useContext, useCallback } from "react";
import Map from "../maps/Map";
import GameBanner from "./components/GameBanner";
import PlayerInfoCard from "./components/PlayerInfoCard";
import UnitSelectModal from "./components/UnitSelectModal";
import { Container, Row, Col } from "react-bootstrap";
import axios from "axios";
import MoveToInfoCard from "./components/MoveToInfoCard";
import { AuthContext } from "../auth/AuthProvider";
import { useLocation } from 'react-router-dom';
import LoadingView from "./components/LoadingView";

const MoveView = () => {
    const { user } = useContext(AuthContext);
    const location = useLocation();
    const gameId = location.state.gameId;
    const [game, setGame] = useState();
    const [isLoading, setIsLoading] = useState(true);
    const [sourceTerritory, setSourceTerritory] = useState();
    const [targetTerritory, setTargetTerritory] = useState();


    const fetchGame = useCallback(async () => {
        try {
            const config = {
                headers: { Authorization: `Bearer ${user.accessToken}` }
            }
            let response = await axios.get(`getGame/${gameId}`, config);
            console.log(`Current game: ${response.data}`);
            setGame(response.data);
            setIsLoading(false);
        } catch (error) {
            console.log(error);
        }
    }, [gameId, user.accessToken])

    useEffect(() => {
        fetchGame();
    }, [fetchGame]);

    if (isLoading) {
        return <LoadingView />;
    }

    const currentView = sourceTerritory ? "move-to" : "move-from";
    const setSourceOrTarget = sourceTerritory ? setTargetTerritory : setSourceTerritory;

    return (
        <>
            <Container>
                <Row>
                    <Col md={9}>
                        <GameBanner view={currentView} />
                        <Map game={game} handleSourceOrTarget={setSourceOrTarget} />
                    </Col>
                    <Col md={3}>
                        <PlayerInfoCard game={game} />
                        <br />
                        <MoveToInfoCard source={sourceTerritory} target={targetTerritory} />
                    </Col>
                </Row>
            </Container>
            <UnitSelectModal gameId={gameId} source={sourceTerritory} target={targetTerritory} territories={game.map.territories} orderType="MOVE" />
        </>
    );
};

export default MoveView;