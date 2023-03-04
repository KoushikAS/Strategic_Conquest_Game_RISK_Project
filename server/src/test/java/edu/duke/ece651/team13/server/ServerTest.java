package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.shared.V1Map;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest{

    @Test
    public void test_send_map_to_client(){
        try{
            Server server = new Server(12345);
            Socket clientSocket = new Socket("", 12345);
            server.sendMapToClient();
            Object recvMsg = recvMsgFrom(clientSocket);
            V1Map expectedMap = new V1Map(1);
            assertEquals(expectedMap, recvMsg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

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