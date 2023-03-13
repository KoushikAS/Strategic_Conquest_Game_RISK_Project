package edu.duke.ece651.team13.client;

import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.map.V1Map9Territories;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;

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



}
