package edu.duke.ece651.team13.client;

import edu.duke.ece651.team13.shared.order.PlayerOrderInput;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

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
    public Client(Socket socket) {
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


    /**
     * Send message to server
     *
     * @param mesg is the message to send
     */
    public void sendMesgTo(Object mesg) {
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(clientSocket.getOutputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream((bufferedOutputStream));
            objectOutputStream.writeObject(mesg);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send an ArrayList of orders to the server
     *
     * @param orders is the ArrayList of orders to send
     */
    public void sendOrdersToServer(ArrayList<PlayerOrderInput> orders) {
        sendMesgTo(orders);
    }


}
