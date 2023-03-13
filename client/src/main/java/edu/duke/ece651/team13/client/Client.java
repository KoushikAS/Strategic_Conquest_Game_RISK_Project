package edu.duke.ece651.team13.client;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * This class represents a Client in the RISC game
 */
public class Client {
    private final Socket clientSocket;

    /**
     * Constructs a Client instance by building the socket required.
     *
     * @param socket the socket of the to be communicating
     */
    public Client(Socket socket)  {
        this.clientSocket = socket;
    }

    /**
     * This method receives some message from the server
     *
     * @return the received object
     */
    public Object recvMsg() throws IOException, ClassNotFoundException {
        BufferedInputStream readBufferForClient = new BufferedInputStream(clientSocket.getInputStream());
        ObjectInputStream objectStreamFromClient = new ObjectInputStream(readBufferForClient);
        return objectStreamFromClient.readObject();

    }

}
