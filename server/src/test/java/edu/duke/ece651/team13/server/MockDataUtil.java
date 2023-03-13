package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.shared.Player;
import edu.duke.ece651.team13.shared.map.V1Map;

import java.util.ArrayList;

import static edu.duke.ece651.team13.server.App.getMap;
import static edu.duke.ece651.team13.server.App.getPlayers;

public class MockDataUtil {
    private MockDataUtil(){}

    public static RiscGame getMockGame(int noPlayers){
        V1Map map = getMap(noPlayers);
        ArrayList<Player> players = getPlayers(noPlayers);
        return new RiscGame(map,players);
    }

    public static RiscGame getMockGame(int noPlayers, Dice dice){
        V1Map map = getMap(noPlayers);
        ArrayList<Player> players = getPlayers(noPlayers);
        return new RiscGame(map,players,dice);
    }

}
