import React from "react";
import { Card } from "react-bootstrap";

const MoveToInfoCard = (props) => {

    const { source, target, player } = props;

    if (!source) return;

    const getCardColor = (owner) => {
        switch (owner) {
            case "Red":
                return "#FFCCCB";
            case "Blue":
                return "#ADD8E6";
            case "Green":
                return "#90EE90";
            case "Yellow":
                return "#FFFFE0";
            default:
                return "#F5F5F5";
        }
    };

    const cardStyles = {
        backgroundColor: getCardColor(player.name),
        textAlign: "left",
        boxShadow: "0px 0px 10px rgba(0, 0, 0, 0.3)",
    };

    return (
        <Card>
            <Card.Body style={cardStyles}>
                <Card.Text>You are moving units from: <span style={territoryNameStyles}>{source}</span></Card.Text>
                <Card.Text>To: <span style={territoryNameStyles}>{target}</span></Card.Text>
            </Card.Body>
        </Card>
    );
};

const territoryNameStyles = {
    fontWeight: "bold",
}

export default MoveToInfoCard;