package edu.duke.ece651.team13.server.handler;

import java.io.*;
import java.net.Socket;

public abstract class PlayerHandler extends Thread {

    private Socket socket;


    public PlayerHandler(Socket socket) {
        this.socket = socket;
    }

    /**
     * Send message object to the socket
     *
     * @param message message object that server will send to client
     */
    public void sendMessage(Object message) throws IOException {

        try (BufferedOutputStream clientBufferedStream = new BufferedOutputStream(this.socket.getOutputStream())) {
            ObjectOutputStream clientObjectStream = new ObjectOutputStream(clientBufferedStream);
            clientObjectStream.writeObject(message);
            clientObjectStream.flush();
        } finally {
            socket.close();
            System.out.println("close");
        }
    }

    /**
     * This method receives some message from socket
     *
     * @return the received object
     */
    public Object recvMessage() throws IOException, ClassNotFoundException {
        BufferedInputStream readBufferForClient = new BufferedInputStream(socket.getInputStream());
        ObjectInputStream objectStreamFromClient = new ObjectInputStream(readBufferForClient);
        return objectStreamFromClient.readObject();

    }

    @Override
    public abstract void run();

}
