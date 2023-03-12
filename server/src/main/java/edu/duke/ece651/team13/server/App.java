package edu.duke.ece651.team13.server;


import edu.duke.ece651.team13.shared.map.V1Map;
import edu.duke.ece651.team13.shared.map.V1Map12Territories;
import edu.duke.ece651.team13.shared.map.V1Map18Territories;
import edu.duke.ece651.team13.shared.map.V1Map24Territories;

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

    public static void main(String[] args) {
        App a = new App();

        //TODO currently hardcoded  player number. Should be dynamic.
        int playerNumber = 2;
        //Setup a board



    }
}
