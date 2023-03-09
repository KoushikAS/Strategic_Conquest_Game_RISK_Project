package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.shared.RiscGame;
import edu.duke.ece651.team13.shared.V1Map;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest{

    private int testPortNum = 12345;

    @Test
    public void test_acceptClients(){
        try{
            Server server = new Server(testPortNum, new RiscGame(2));
            Socket clientSocket1 = new Socket("", testPortNum);
            Socket clientSocket2 = new Socket("", testPortNum);
            server.connectToClients();
            V1Map expectedMap = new V1Map(1);
            assertEquals(expectedMap, recvMsgFrom(clientSocket1));
            assertEquals(expectedMap, recvMsgFrom(clientSocket2));
            server.closeServer();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test_sendMapToClient(){
        try{
            Server server = new Server(testPortNum, new RiscGame(1));
            Socket clientSocket = new Socket("", testPortNum);
            server.connectToClients();
            V1Map expectedMap = new V1Map(1);
            assertEquals(expectedMap, recvMsgFrom(clientSocket));
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