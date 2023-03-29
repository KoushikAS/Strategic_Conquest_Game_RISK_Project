import React, { useState } from "react";
import TerritoryResouceView from "./TerritoryResouceView";
import TerritoryBasicView from "./TerritoryBasicView";
import { Container } from "react-bootstrap";

const TerritoryView = (props) => {
  const [hover, setHover] = useState(false);
  const styles = {
    backgroundColor: getTerritoryColor(props.territory.owner, false),
  };
  const territory = props.territory;

  return (
    <div
      key={territory.name}
      className="territory"
      onMouseEnter={() => setHover(true)}
      onMouseLeave={() => setHover(false)}
      style={styles}
    >
      <Container >
        {hover ? (
          <TerritoryResouceView territory={territory} />
        ) : (
          <TerritoryBasicView territory={territory} />
        )}
      </Container>
    </div>
  );
};

const getTerritoryColor = (owner, highlight) => {
  switch (owner) {
    case "Red":
      return highlight ? "#D33431" : "#FFCCCB";
    case "Blue":
      return highlight ? "#379EBF" : "#ADD8E6";
    case "Green":
      return highlight ? "#26BC26" : "#90EE90";
    case "Yellow":
      return highlight ? "#CCCC48" : "#FFFFE0";
    default:
      return highlight ? "#D3D3D3" : "#F5F5F5";
  }
};

export default TerritoryView;
