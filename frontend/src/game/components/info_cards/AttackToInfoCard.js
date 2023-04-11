import React from "react";
import { Card } from "react-bootstrap";

const AttackToInfoCard = (props) => {

    const source = props.source;
    const territories = props.territories;

    if (!source) return;

    const getConnections = (name) => {
        return territories.find((territory) => territory.name === name).connections;
    };

    const getTerritory = (id) => {
        return territories.find((territory) => territory.id === id);
    };

    const listTerritories = getConnections(source).map((connection) =>
        <li key={connection.destinationTerritoryId}>
            <p style={territoryNameStyles}>
                {getTerritory(connection.destinationTerritoryId).name}
            </p>
        </li>
    );


    return (
        <Card>
            <Card.Body style={cardStyles}>
                <Card.Text>Territories you can move from <span style={territoryNameStyles}>{source}</span>:</Card.Text>
                <ul>
                    {listTerritories}
                </ul>
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

export default AttackToInfoCard;