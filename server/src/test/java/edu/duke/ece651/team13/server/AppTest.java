package edu.duke.ece651.team13.server;


import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.map.V1Map12Territories;
import edu.duke.ece651.team13.shared.map.V1Map18Territories;
import edu.duke.ece651.team13.shared.map.V1Map24Territories;
import edu.duke.ece651.team13.shared.player.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static edu.duke.ece651.team13.server.App.getMap;
import static edu.duke.ece651.team13.server.App.getPlayers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class AppTest {
    @Test
    void test_GetMessage() {
        App a = new App();
    }

    @Test
    void test_getMap() {
        assertThrows(AssertionError.class, () -> getMap(5));

        MapRO map2player = getMap(2);
        assertEquals(map2player.getClass(), V1Map12Territories.class);

        MapRO map3player = getMap(3);
        assertEquals(map3player.getClass(), V1Map18Territories.class);

        MapRO map4player = getMap(4);
        assertEquals(map4player.getClass(), V1Map24Territories.class);

    }

    @Test
    void test_getPlayers() {
        assertThrows(AssertionError.class, () -> getPlayers(5));

        ArrayList<Player> player2 = getPlayers(2);
        assertEquals(2, player2.size());

        ArrayList<Player> player3 = getPlayers(3);
        assertEquals(3, player3.size());

        ArrayList<Player> player4 = getPlayers(4);
        assertEquals(4, player4.size());
    }

    //End to End integration testing.
//    @Test
//    public void test_start() throws IOException, ClassNotFoundException, InterruptedException {
//        Game game = getMockGame(2);
//
//        Server server = new Server(testPortNum, getMockGame(2));
//        Socket clientSocket1 = new Socket("", testPortNum);
//        Socket clientSocket2 = new Socket("", testPortNum);
//        server.start();
//
//        MapRO expectedMap = game.getMapRO();
//
//
//        assertEquals("Red", recvMessage(clientSocket1));
//        sendMessage(clientSocket1, new Ack(SUCCESS, "Success"));
//        ArrayList<PlayerOrderInput> input1 = new ArrayList<>();
//        input1.add(new PlayerOrderInput(MOVE, "Rottweiler", "Dachshund", 0));
//        sendMessage(clientSocket1, input1);
//        assertEquals(expectedMap, recvMessage(clientSocket1));
//        sendMessage(clientSocket1, new Ack(SUCCESS,"Receieved Ack"));
//
//
//        assertEquals("Blue", recvMessage(clientSocket2));
//        sendMessage(clientSocket2, new Ack(SUCCESS, "Success"));
//        ArrayList<PlayerOrderInput> input2 = new ArrayList<>();
//        sendMessage(clientSocket2, input2);
//        assertEquals(expectedMap, recvMessage(clientSocket2));
//        sendMessage(clientSocket1, new Ack(SUCCESS,"Receieved Ack"));
//
//        // server.closeServer();
//    }

}

