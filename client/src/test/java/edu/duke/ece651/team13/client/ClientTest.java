package edu.duke.ece651.team13.client;

import edu.duke.ece651.team13.shared.Player;
import edu.duke.ece651.team13.shared.map.V1Map;
import edu.duke.ece651.team13.shared.map.V1Map9Territories;
import edu.duke.ece651.team13.shared.order.PlayerOrderInput;
import edu.duke.ece651.team13.shared.order.PlayerOrderInputRO;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class ClientTest {

    private final Player mockedPlayer = mock(Player.class);

    @Test
    public void testRecvMapFromServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            Client client = new Client("", 12345, mockedPlayer);
            V1Map map = new V1Map9Territories(1);
            sendMesgTo(map, client.getClientSocket());
            V1Map expectedMap = client.recvMapFromServer(serverSocket);
            assertEquals(expectedMap, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Temporary helper method for sending message from a server socket to the client socket
     *
     * @param mesg message to be sent
     * @param clientSocket the client socket to send the message to
     */
    private void sendMesgTo(Object mesg, Socket clientSocket) {
        try {
            BufferedOutputStream clientBufferedStream = new BufferedOutputStream(clientSocket.getOutputStream());
            ObjectOutputStream clientObjectStream = new ObjectOutputStream(clientBufferedStream);
            clientObjectStream.writeObject(mesg);
            clientObjectStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper function to receive message object from client
     * @param recvSocket socket used to communicate with client
     */
    public Object recvMsgFrom(Socket recvSocket){
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
        Client client = new Client("", 12345, mockedPlayer);
        Socket clientSocket = serverSocket.accept();
        client.sendOrdersToServer(orders);
        ArrayList<PlayerOrderInputRO> received = (ArrayList<PlayerOrderInputRO>)recvMsgFrom(clientSocket);
        for(int i = 0; i < received.size(); i++){
            assertEquals(orders.get(i), received.get(i));
        }
    }

}
