import React from "react";
import "../styles/Map.css";
import TerritoryView from "./components/TerritoryView";

const dummyTerritories = [
  {
    name: "Rottweiler",
    owner: "Green",
    techGenNum: 4,
    foodGenNum: 2,
    units: { basic: 3, infantry: 6, cavalry: 12, artillery: 9 },
  },
  {
    name: "Dachshund",
    owner: "Green",
    techGenNum: 3,
    foodGenNum: 1,
    units: { basic: 6, infantry: 5, cavalry: 2, artillery: 14 },
  },
  {
    name: "Beagle",
    owner: "Green",
    techGenNum: 2,
    foodGenNum: 5,
    units: { basic: 10, infantry: 9, cavalry: 7, artillery: 8 },
  },
  {
    name: "Labrador",
    owner: "Green",
    techGenNum: 1,
    foodGenNum: 3,
    units: { basic: 2, infantry: 3, cavalry: 11, artillery: 7 },
  },
  {
    name: "Poodle",
    owner: "Green",
    techGenNum: 5,
    foodGenNum: 4,
    units: { basic: 12, infantry: 8, cavalry: 6, artillery: 5 },
  },
  {
    name: "Bulldog",
    owner: "Green",
    techGenNum: 3,
    foodGenNum: 5,
    units: { basic: 8, infantry: 3, cavalry: 15, artillery: 2 },
  },
  {
    name: "Boxer",
    owner: "Red",
    techGenNum: 1,
    foodGenNum: 4,
    units: { basic: 7, infantry: 14, cavalry: 1, artillery: 12 },
  },
  {
    name: "Havanese",
    owner: "Red",
    techGenNum: 2,
    foodGenNum: 2,
    units: { basic: 1, infantry: 5, cavalry: 3, artillery: 8 },
  },
  {
    name: "Spaniel",
    owner: "Red",
    techGenNum: 4,
    foodGenNum: 5,
    units: { basic: 9, infantry: 7, cavalry: 4, artillery: 6 },
  },
  {
    name: "Sheepdog",
    owner: "Red",
    techGenNum: 5,
    foodGenNum: 3,
    units: { basic: 14, infantry: 12, cavalry: 10, artillery: 3 },
  },
  {
    name: "Akita",
    owner: "Red",
    techGenNum: 1,
    foodGenNum: 1,
    units: { basic: 4, infantry: 11, cavalry: 13, artillery: 1 },
  },
  {
    name: "Brittany",
    owner: "Red",
    techGenNum: 3,
    foodGenNum: 4,
    units: { basic: 15, infantry: 2, cavalry: 5, artillery: 13 },
  },
  {
    name: "Vizsla",
    owner: "Blue",
    techGenNum: 5,
    foodGenNum: 1,
    units: { basic: 5, infantry: 6, cavalry: 14, artillery: 4 },
  },
  {
    name: "Maltese",
    owner: "Blue",
    techGenNum: 3,
    foodGenNum: 5,
    units: { basic: 7, infantry: 1, cavalry: 8, artillery: 10 },
  },
  {
    name: "Chihuahua",
    owner: "Blue",
    techGenNum: 3,
    foodGenNum: 5,
    units: { basic: 7, infantry: 1, cavalry: 8, artillery: 10 },
  },
  {
    name: "Pug",
    owner: "Blue",
    techGenNum: 2,
    foodGenNum: 5,
    units: { basic: 6, infantry: 5, cavalry: 14, artillery: 8 },
  },
  {
    name: "Mastiff",
    owner: "Blue",
    techGenNum: 1,
    foodGenNum: 8,
    units: { basic: 3, infantry: 13, cavalry: 7, artillery: 12 },
  },
  {
    name: "Collie",
    owner: "Blue",
    techGenNum: 5,
    foodGenNum: 1,
    units: { basic: 14, infantry: 9, cavalry: 2, artillery: 8 },
  },
  {
    name: "Dalmatian",
    owner: "Yellow",
    techGenNum: 9,
    foodGenNum: 10,
    units: { basic: 13, infantry: 7, cavalry: 5, artillery: 12 },
  },
  {
    name: "Papillon",
    owner: "Yellow",
    techGenNum: 4,
    foodGenNum: 8,
    units: { basic: 5, infantry: 6, cavalry: 11, artillery: 1 },
  },
  {
    name: "Setter",
    owner: "Yellow",
    techGenNum: 8,
    foodGenNum: 3,
    units: { basic: 8, infantry: 15, cavalry: 14, artillery: 1 },
  },
  {
    name: "Samoyed",
    owner: "Yellow",
    techGenNum: 6,
    foodGenNum: 9,
    units: { basic: 2, infantry: 5, cavalry: 13, artillery: 3 },
  },
  {
    name: "Bullmastiff",
    owner: "Yellow",
    techGenNum: 7,
    foodGenNum: 2,
    units: { basic: 10, infantry: 12, cavalry: 1, artillery: 6 },
  },
  {
    name: "Whippet",
    owner: "Yellow",
    techGenNum: 3,
    foodGenNum: 4,
    units: { basic: 4, infantry: 9, cavalry: 8, artillery: 3 },
  },
];

const Map = (props) => {
  const territories = props.game.map.territories;
  return (
    <div style={{ display: "flex", flexWrap: "wrap" }}>
      {territories.map((territory) => (
        <TerritoryView key={territory.name} territory={territory} />
      ))}
    </div>
  );
};

export default Map;
