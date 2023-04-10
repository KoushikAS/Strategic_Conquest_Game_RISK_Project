import React from "react";
import { Card } from "react-bootstrap";

const AttackToInfoCard = (props) => {

    const source = props.source;
    const territories = props.territories;

    if(!source) return;

    const getConnections = (name) => {
        return territories.find((territory) => territory.name === name).connections;
    };

    const getTerritory = (id) => {
        return territories.find((territory) => territory.id === id);
    };

    const listTerritories = getConnections(source).map((connection) =>
        <li key={connection.destinationTerritoryId}>
            {getTerritory(connection.destinationTerritoryId).name}
        </li>
    );


return(
    <Card>
        <Card.Body style={MoveToInfoCardStyles}>
            <Card.Text>Territories you can move from {source}:</Card.Text>
            <ul>
                {listTerritories}
            </ul>
        </Card.Body>
    </Card>
);
};

const MoveToInfoCardStyles = {
    backgroundColor: "#FFCCCB",
    textAlign: "left",
    boxShadow: "0px 0px 10px rgba(0, 0, 0, 0.3)",
};

export default AttackToInfoCard;