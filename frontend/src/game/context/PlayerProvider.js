import React, { createContext, useState, useMemo, useCallback } from 'react';

export const PlayerContext = createContext();

export const PlayerProvider = ({ children }) => {
    const [hasResearched, setHasResearched] = useState(false);
    const [hasDone, setHasDone] = useState(false);

    const value = useMemo(
        () => ({
            hasResearched,
            hasDone,
            setHasResearched,
            setHasDone
        }),
        [hasResearched, hasDone, setHasResearched, setHasDone]
    );

    return (
        <PlayerContext.Provider value={value}>
            {children}
        </PlayerContext.Provider>
    );
};