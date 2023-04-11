import React, { createContext, useState, useMemo, useCallback } from 'react';

export const PlayerContext = createContext();

export const PlayerProvider = ({ children }) => {
    const [hasResearched, setHasResearched] = useState(false);

    const value = useMemo(
        () => ({
            hasResearched,
            setHasResearched,
        }),
        [hasResearched, setHasResearched]
    );

    return (
        <PlayerContext.Provider value={value}>
            {children}
        </PlayerContext.Provider>
    );
};