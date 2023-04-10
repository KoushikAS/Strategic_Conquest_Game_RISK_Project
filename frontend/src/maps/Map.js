import React from "react";
import "../styles/Map.css";
import Map2Players from "./components/Map2Players";
import Map4Players from "./components/Map4Players";
import Map3Players from "./components/Map3Players";
import map2PlayersBg from "./images/Map2Players.png";
import map3PlayersBg from "./images/Map3Players.png";
import map4PlayersBg from "./images/Map4Players.png";

const Map = (props) => {
  const territories = props.game.map.territories;
  const playerNum = props.game.players.length;

  const getBgImg = (playerNum) => {
    switch (playerNum) {
      case 2:
        return `url(${map2PlayersBg})`;
      case 3:
        return `url(${map3PlayersBg})`;
      default:
        return `url(${map4PlayersBg})`;
    }
  };

  const backgroundStyles = {
    backgroundImage: getBgImg(playerNum),
    backgroundRepeat: "no-repeat",
    backgroundSize: "cover",
    //backgroundSize: "120%",
    backgroundPosition: "50% 50%",
  };

  if (playerNum === 2) {
    return (
      <div style={backgroundStyles}>
        <Map2Players
          territories={territories}
          handleSourceOrTarget={props.handleSourceOrTarget}
        />
      </div>
    );
  } else if (playerNum === 3) {
    return (
      <div style={backgroundStyles}>
        <Map3Players
          territories={territories}
          handleSourceOrTarget={props.handleSourceOrTarget}
        />
      </div>
    );
  }
  return (
    <div style={backgroundStyles}>
      <Map4Players
        territories={territories}
        handleSourceOrTarget={props.handleSourceOrTarget}
      />
    </div>
  );
};

export default Map;
