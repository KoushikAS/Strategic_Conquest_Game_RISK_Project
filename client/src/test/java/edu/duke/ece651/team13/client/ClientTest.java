package edu.duke.ece651.team13.client;

import edu.duke.ece651.team13.shared.Player;
import edu.duke.ece651.team13.shared.map.V1Map;
import edu.duke.ece651.team13.shared.map.V1Map9Territories;
import org.junit.jupiter.api.Test;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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

}
