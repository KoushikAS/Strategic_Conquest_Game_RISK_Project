import React, { createContext, useState, useMemo } from "react";

export const PlayerContext = createContext();

export const PlayerProvider = ({ children }) => {
  const [hasResearched, setHasResearched] = useState(false);
  const [hasDone, setHasDone] = useState(false);
  const [hasCloakResearched, setHasCloakResearched] = useState(false);

  const value = useMemo(
    () => ({
      hasResearched,
      hasDone,
      hasCloakResearched,
      setHasResearched,
      setHasDone,
      setHasCloakResearched,
    }),
    [
      hasResearched,
      hasDone,
      hasCloakResearched,
      setHasResearched,
      setHasDone,
      setHasCloakResearched,
    ]
  );

  return (
    <PlayerContext.Provider value={value}>{children}</PlayerContext.Provider>
  );
};
