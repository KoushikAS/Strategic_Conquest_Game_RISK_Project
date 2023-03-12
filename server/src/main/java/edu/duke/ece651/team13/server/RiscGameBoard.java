package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.shared.map.*;

public class RiscGameBoard <T> implements Board<T>{
    private final MapRO map;

    @Override
    public MapRO getMap() {
        return map;
    }

    public RiscGameBoard(int playerNumber){
        // TODO: How do we keep the initial unit settings (stored in board class or?)
        //map = new V1Map9Territories(10);
        assert(playerNumber==2||playerNumber==3||playerNumber==4);
        if(playerNumber==2){
            this.map = new V1Map12Territories(12);
        }
        else if(playerNumber==3){
            this.map = new V1Map18Territories(18);
        }
        else{ //PlayerNumbers == 4
            this.map = new V1Map24Territories(24);
        }
    }
}
