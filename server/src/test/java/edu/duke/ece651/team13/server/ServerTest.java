package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.shared.Ack;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.order.PlayerOrderInput;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import static edu.duke.ece651.team13.server.MockDataUtil.getMockGame;
import static edu.duke.ece651.team13.shared.enums.AckStatusEnum.SUCCESS;
import static edu.duke.ece651.team13.shared.enums.OrderMappingEnum.MOVE;
import static edu.duke.ece651.team13.shared.util.networkUtil.recvMessage;
import static edu.duke.ece651.team13.shared.util.networkUtil.sendMessage;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ServerTest {

    private final int testPortNum = 12345;

    @Test
    public void test_start() throws IOException, ClassNotFoundException {
        Game game = getMockGame(2);

        Server server = new Server(testPortNum, getMockGame(2));
        Socket clientSocket1 = new Socket("", testPortNum);
        Socket clientSocket2 = new Socket("", testPortNum);
        server.start();

        MapRO expectedMap = game.getMapRO();


        assertEquals("Red", recvMessage(clientSocket1));
        sendMessage(clientSocket1, new Ack(SUCCESS, "Success"));
        ArrayList<PlayerOrderInput> input1 = new ArrayList<>();
        input1.add(new PlayerOrderInput(MOVE, "Rottweiler", "Dachshund", 0));
        sendMessage(clientSocket1, input1);
        assertEquals(expectedMap, recvMessage(clientSocket1));


        assertEquals("Blue", recvMessage(clientSocket2));
        sendMessage(clientSocket2, new Ack(SUCCESS, "Success"));
        ArrayList<PlayerOrderInput> input2 = new ArrayList<>();
        sendMessage(clientSocket2, input2);
        assertEquals(expectedMap, recvMessage(clientSocket2));

       // server.closeServer();
    }

}