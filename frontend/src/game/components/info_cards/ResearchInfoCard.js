import React from "react";
import { Card, Row, Button } from "react-bootstrap";

const ResearchInfoCard = (props) => {
    return (
        <>
            <Card>
                <Card.Body style={cardStyles}>
                    <Card.Text>You can only upgrade your maximum technology level once each turn.</Card.Text>
                </Card.Body>
            </Card>
            <Row className="text-center" style={{ marginTop: "80%" }}>
                <Button
                    onClick={props.handleConfirm}
                    variant="success"
                    size="lg"
                    style={{ fontWeight: "bold" }}
                    block="true"
                >
                    Confirm
                </Button>
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