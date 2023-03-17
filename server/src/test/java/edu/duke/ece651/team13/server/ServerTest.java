package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.shared.map.MapRO;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import static edu.duke.ece651.team13.server.MockDataUtil.getMockGame;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ServerTest {

    private final int testPortNum = 12345;

    @Test
    public void test_start() throws IOException, ClassNotFoundException {
        Game game = getMockGame(4);

        Server server = new Server(testPortNum, getMockGame(4));
        Socket clientSocket1 = new Socket("", testPortNum);
        Socket clientSocket2 = new Socket("", testPortNum);
        Socket clientSocket3 = new Socket("", testPortNum);
        Socket clientSocket4 = new Socket("", testPortNum);
        server.start();

        MapRO expectedMap = game.getMapRO();

        assertEquals(expectedMap, recvMsgFrom(clientSocket1));
        assertEquals(expectedMap, recvMsgFrom(clientSocket2));
        assertEquals(expectedMap, recvMsgFrom(clientSocket3));
        assertEquals(expectedMap, recvMsgFrom(clientSocket4));
        server.closeServer();
    }

    /**
     * Helper function to receive message object from server
     *
     * @param clientSocket client socket used to communicate with server
     */
    public Object recvMsgFrom(Socket clientSocket) throws IOException, ClassNotFoundException {
        BufferedInputStream read_buffer_for_server = new BufferedInputStream(clientSocket.getInputStream());
        ObjectInputStream object_stream_from_server = new ObjectInputStream(read_buffer_for_server);
        return object_stream_from_server.readObject();
    }
}