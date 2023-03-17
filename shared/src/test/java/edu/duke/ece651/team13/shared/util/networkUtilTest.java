package edu.duke.ece651.team13.shared.util;

import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.map.V1Map9Territories;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static edu.duke.ece651.team13.shared.MockDataUtil.mockInputStream;
import static edu.duke.ece651.team13.shared.util.networkUtil.recvMessage;
import static edu.duke.ece651.team13.shared.util.networkUtil.sendMessage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class networkUtilTest {

    @Test
    public void testRecvMessage() throws IOException, ClassNotFoundException {
        MapRO expectedMap = new V1Map9Territories(1);
        Socket mockTestSocket = mock(Socket.class);
        when(mockTestSocket.getInputStream()).thenReturn(mockInputStream(expectedMap));

        MapRO actualMap = (MapRO) recvMessage(mockTestSocket);

        assertEquals(expectedMap, actualMap);
    }

    @Test
    void test_sendMessage() throws IOException, ClassNotFoundException {
        String expected = "M\nRottweiler\nPoodle\n10\nM\nRottweiler\nDachshund\n20\nA\nRottweiler\nBoxer\n30\nD\n";

        //TODO: not  a good way to test as port might already be binded
        //Client Server setup
        ServerSocket serverSocket = new ServerSocket(10000);
        Socket client = new Socket("localhost", 10000);
        Socket acceptSocket = serverSocket.accept();

        //Sending message from client
        sendMessage(client, expected);

        //Receving message from server
        String actual = (String) recvMessage(acceptSocket);

        assertEquals(actual, expected);

    }
}
