package edu.duke.ece651.team13.client;

import edu.duke.ece651.team13.shared.Player;
import edu.duke.ece651.team13.shared.map.V1Map;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class represents a Client in the RISC game
 */
public class Client {
    private final String host;
    private final int portNum;
    private Socket clientSocket;
    private Player player;

    /**
     * Constructs a Client instance
     * @param host the host IP address
     * @param portNum the port number to connect to
     */
    public Client(String host, int portNum, Player player){
        this.portNum = portNum;
        this.host = host;
        this.player = player;
        buildClient();
    }

    /**
     * This is a helper method used by the constructor to create a Client instance
     */
    private void buildClient(){
        try {
            this.clientSocket = new Socket(host, portNum);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This method receives some message from the server
     *
     * @param serverSocket the server socket to receive from
     * @return the received object
     */
    public Object recvMsgFrom(ServerSocket serverSocket){
        try {
            clientSocket = serverSocket.accept();
            BufferedInputStream readBufferForClient = new BufferedInputStream(clientSocket.getInputStream());
            ObjectInputStream objectStreamFromClient = new ObjectInputStream(readBufferForClient);
            return objectStreamFromClient.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method specifically receives a map from the server
     *
     * @param serverSocket the server socket to receive from
     * @return a V1Map if received successfully and null otherwise
     */
    public V1Map recvMapFromServer(ServerSocket serverSocket) {
        try{
            V1Map map = (V1Map) recvMsgFrom(serverSocket);
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the client socket
     *
     * @return the client socket
     */
    public Socket getClientSocket() {
        return clientSocket;
    }
}
