import React, { useState } from "react";
import TerritoryDetailView from "./TerritoryDetailView";
import TerritoryBasicView from "./TerritoryBasicView";
import Container from "react-bootstrap/Container";
import Modal from "react-bootstrap/Modal";

const TerritoryView = (props) => {
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const territory = props.territory;
  const styles = {
    backgroundColor: getTerritoryColor(territory.owner.name, false),
    cursor: "pointer",
  };

  const handleClick = (e) => {
    console.log(e.target);
    props.handleSource(territory);
  };

  return (
    <>
      <div
        key={territory.name}
        onClick={handleShow}
        className="territory"
        style={styles}
      >
        <Container>
          <TerritoryBasicView
            handleSource={props.handleSource}
            handleClose={handleClose}
            territory={territory}
          />
        </Container>
      </div>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>
            {territory.name} owned by {territory.owner.name}
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <TerritoryDetailView territory={territory} />
        </Modal.Body>
      </Modal>
    </>
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