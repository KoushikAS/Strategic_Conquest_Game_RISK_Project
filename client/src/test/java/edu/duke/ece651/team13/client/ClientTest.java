package edu.duke.ece651.team13.client;

import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.map.V1Map9Territories;
import edu.duke.ece651.team13.shared.order.PlayerOrderInput;
import edu.duke.ece651.team13.shared.order.PlayerOrderInputRO;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static edu.duke.ece651.team13.client.MockDataUtil.mockInputStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientTest {


    @Test
    public void testRecvMapFromServer() throws IOException, ClassNotFoundException {
        MapRO expectedMap = new V1Map9Territories(1);
        Socket mockTestClientSocket = mock(Socket.class);
        when(mockTestClientSocket.getInputStream()).thenReturn(mockInputStream(expectedMap));

        Client client = new Client(mockTestClientSocket);
        MapRO actualMap = (MapRO) client.recvMsg();
        assertEquals(expectedMap, actualMap);
    }


    /**
     * Helper function to receive message object from client
     *
     * @param recvSocket socket used to communicate with client
     */
    public Object recvMsgFrom(Socket recvSocket) {
        try {
            BufferedInputStream read_buffer_for_server = new BufferedInputStream(recvSocket.getInputStream());
            ObjectInputStream object_stream_from_server = new ObjectInputStream(read_buffer_for_server);
            return object_stream_from_server.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    void test_sendOrders() throws IOException {
        String input = "M\nRottweiler\nPoodle\n10\nM\nRottweiler\nDachshund\n20\nA\nRottweiler\nBoxer\n30\nD\n";
        BufferedReader inputReader = new BufferedReader(new StringReader(input));
        GameRound round = new GameRound("round1", inputReader);
        ArrayList<PlayerOrderInput> orders = round.playOneRound();

        ServerSocket serverSocket = new ServerSocket(12345);
        Client client = new Client(new Socket("localhost", 12345));
        Socket clientSocket = serverSocket.accept();
        client.sendOrdersToServer(orders);
        ArrayList<PlayerOrderInputRO> received = (ArrayList<PlayerOrderInputRO>) recvMsgFrom(clientSocket);
        for (int i = 0; i < received.size(); i++) {
            assertEquals(orders.get(i), received.get(i));
        }
    }

}
