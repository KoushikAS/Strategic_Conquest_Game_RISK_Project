import React from "react";
import { Card } from "react-bootstrap";

const MoveToInfoCard = (props) => {

    const source = props.source;
    const target = props.target;

    if (!source) return;

    return (
        <Card>
            <Card.Body style={cardStyles}>
                <Card.Text>You are moving units from: <span style={territoryNameStyles}>{source}</span></Card.Text>
                <Card.Text>To: <span style={territoryNameStyles}>{target}</span></Card.Text>
            </Card.Body>
        </Card>
    );
};

const cardStyles = {
    backgroundColor: "#FFCCCB",
    textAlign: "left",
    boxShadow: "0px 0px 10px rgba(0, 0, 0, 0.3)",
};

const territoryNameStyles = {
    fontWeight: "bold",
}

export default MoveToInfoCard;