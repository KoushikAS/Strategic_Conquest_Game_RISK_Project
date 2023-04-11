import React, { useEffect, useState, useContext, useCallback } from "react";
import Map from "../maps/Map";
import GameBanner from "./components/GameBanner";
import PlayerInfoCard from "./components/info_cards/PlayerInfoCard";
import { Container, Row, Col } from "react-bootstrap";
import axios from "axios";
import ResearchInfoCard from "./components/info_cards/ResearchInfoCard";
import { AuthContext } from "../auth/AuthProvider";
import { useLocation } from 'react-router-dom';
import LoadingView from "./components/LoadingView";
import { OrderContext } from "./context/OrderProvider";
import { useNavigate } from "react-router-dom";
import { PlayerContext } from "./context/PlayerProvider";

const ResearchView = () => {
    const { user } = useContext(AuthContext);
    const { setHasResearched } = useContext(PlayerContext);
    const location = useLocation();
    const gameId = location.state.gameId;
    const [game, setGame] = useState();
    const [player, setPlayer] = React.useState();
    const [isLoading, setIsLoading] = useState(true);
    const { addOneOrder } = useContext(OrderContext);
    const navigate = useNavigate();

    const fetchGame = useCallback(async () => {
        try {
            const config = {
                headers: { Authorization: `Bearer ${user.accessToken}` }
            }
            let response = await axios.get(`getGameForUser/${gameId}?userId=${user.userId}`, config);
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
        return <LoadingView />;
    }

    const handleConfirm = () => {
        const order = {
            sourceTerritoryId: null,
            destinationTerritoryId: null,
            unitNum: null,
            unitType: null,
            orderType: "UNIT_UPGRADE"
        }
        addOneOrder(order);
        setHasResearched(true);
        navigate("/", { state: { gameId } });
    }

    return (
        <>
            <Container>
                <Row>
                    <Col md={9}>
                        <GameBanner view="research" />
                        <Map game={game} />
                    </Col>
                    <Col md={3}>
                        <PlayerInfoCard player={player} game={game} />
                        <br />
                        <ResearchInfoCard handleConfirm={handleConfirm} />
                    </Col>
                </Row>
            </Container>
        </>
    );
};

export default ResearchView;