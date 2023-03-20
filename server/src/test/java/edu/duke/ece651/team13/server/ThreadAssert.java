package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.shared.Ack;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.order.PlayerOrderInput;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import static edu.duke.ece651.team13.shared.enums.AckStatusEnum.SUCCESS;
import static edu.duke.ece651.team13.shared.enums.OrderMappingEnum.MOVE;
import static edu.duke.ece651.team13.shared.util.networkUtil.recvMessage;
import static edu.duke.ece651.team13.shared.util.networkUtil.sendMessage;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThreadAssert extends Thread {

    Socket socket;
    MapRO expectedMap;

    public ThreadAssert(Socket socket, MapRO expectedMap) {
        this.socket = socket;
        this.expectedMap = expectedMap;
    }

    @Override
    public void run() {
        try {
            sleep(1000);
            assertEquals("Red", recvMessage(socket));
            sendMessage(socket, new Ack(SUCCESS, "Success"));
            ArrayList<PlayerOrderInput> input1 = new ArrayList<>();
            sendMessage(socket, input1);
            assertEquals(expectedMap, recvMessage(socket));
            sendMessage(socket, new Ack(SUCCESS, "Receieved Ack"));
            assertEquals(false, recvMessage(socket));
            sendMessage(socket, new Ack(SUCCESS, "Success"));
        } catch (ClassNotFoundException | IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
