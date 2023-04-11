import React, { useContext } from "react";
import { Card, Row, Button, Col } from "react-bootstrap";
import { PlayerContext } from "../../context/PlayerProvider";
import { OrderContext } from "../../context/OrderProvider";
import { useNavigate } from "react-router-dom";

const ResearchInfoCard = (props) => {
    const navigate = useNavigate();
    const { setHasResearched } = useContext(PlayerContext);
    const { addOneOrder } = useContext(OrderContext);
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
        navigate("/", { state: { gameId: props.gameId } });
    }
    const handleCancel = () => {
        navigate("/", { state: { gameId: props.gameId } });
    }
    return (
        <>
            <Card>
                <Card.Body style={cardStyles}>
                    <Card.Text>You can only research and upgrade your maximum technology level once each turn.</Card.Text>
                </Card.Body>
            </Card>
            <Row className="text-center" style={{ marginTop: "80%" }}>
                <Col>
                    <Button
                        onClick={handleConfirm}
                        variant="success"
                        size="lg"
                        style={{ fontWeight: "bold" }}
                    >
                        Confirm
                    </Button>
                </Col>
                <Col>
                    <Button
                        onClick={handleCancel}
                        variant="danger"
                        size="lg"
                        style={{ fontWeight: "bold" }}
                    >
                        Cancel
                    </Button>
                </Col>
            </Row>
        </>
    );
};

const cardStyles = {
    backgroundColor: "#FFCCCB",
    textAlign: "left",
    boxShadow: "0px 0px 10px rgba(0, 0, 0, 0.3)",
};

export default ResearchInfoCard;