import React from "react";
import { useContext } from "react";
import { AuthContext } from "../auth/AuthProvider";

const GameListView = () => {
  const { user } = useContext(AuthContext);
  console.log("auth: " + user);
  return <div>GameListView</div>;
};

export default GameListView;
