package edu.duke.ece651.team13.server;


import edu.duke.ece651.team13.shared.HumanPlayer;
import edu.duke.ece651.team13.shared.Player;
import edu.duke.ece651.team13.shared.map.V1Map;
import edu.duke.ece651.team13.shared.map.V1Map12Territories;
import edu.duke.ece651.team13.shared.map.V1Map18Territories;
import edu.duke.ece651.team13.shared.map.V1Map24Territories;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class App {

    public static V1Map getMap(int playerNumber) {
        assert (playerNumber == 2 || playerNumber == 3 || playerNumber == 4);
        if (playerNumber == 2) {
            return new V1Map12Territories(12);
        } else if (playerNumber == 3) {
            return new V1Map18Territories(18);
        } else { //PlayerNumbers == 4
            return new V1Map24Territories(24);
        }
    }

    public static ArrayList<Player> getPlayers(int playerNumber) {
        assert (playerNumber == 2 || playerNumber == 3 || playerNumber == 4);
        ArrayList<String> names = new ArrayList<String>(Arrays.asList("Red", "Blue", "Green", "Yellow"));
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < playerNumber; i++) {
            players.add(new HumanPlayer(names.get(i)));
        }

        return players;
    }

    public static void main(String[] args) throws IOException {
        App a = new App();

        //TODO currently hardcoded  player number. Should be dynamic.
        int playerNumber = 2;
        V1Map map = getMap(playerNumber);
        ArrayList<Player> players = getPlayers(playerNumber);
        Game game = new RiscGame(map, players);
        Server server = new Server(12345, game);
        System.out.println("Starting server");
        server.start();
        //server.closeServer();
    }
}
