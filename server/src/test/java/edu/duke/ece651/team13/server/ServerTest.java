package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.map.V1Map24Territories;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest{

    private int testPortNum = 12345;

    @Test
    public void test_start(){
        try{
            Server server = new Server(testPortNum, new RiscGame(4));
            Socket clientSocket1 = new Socket("", testPortNum);
            Socket clientSocket2 = new Socket("", testPortNum);
            Socket clientSocket3 = new Socket("", testPortNum);
            Socket clientSocket4 = new Socket("", testPortNum);
            server.start();
            MapRO expectedMap = new V1Map24Territories(24);
            assertEquals(expectedMap, recvMsgFrom(clientSocket1));
            assertEquals(expectedMap, recvMsgFrom(clientSocket2));
            assertEquals(expectedMap, recvMsgFrom(clientSocket3));
            assertEquals(expectedMap, recvMsgFrom(clientSocket4));
            server.closeServer();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Helper function to receive message object from server
     * @param clientSocket client socket used to communicate with server
     */
    public Object recvMsgFrom(Socket clientSocket){
        try {
            BufferedInputStream read_buffer_for_server = new BufferedInputStream(clientSocket.getInputStream());
            ObjectInputStream object_stream_from_server = new ObjectInputStream(read_buffer_for_server);
            return object_stream_from_server.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}