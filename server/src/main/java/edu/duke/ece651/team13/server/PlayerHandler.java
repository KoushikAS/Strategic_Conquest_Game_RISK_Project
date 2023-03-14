package edu.duke.ece651.team13.server;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class PlayerHandler extends Thread {

    private Socket clientSocket;

    private Object sendMesg;

    public PlayerHandler(Socket clientSocket, Object sendMesg) {
        this.clientSocket = clientSocket;
        this.sendMesg = sendMesg;
    }

    /**
     * Send message object to the client
     *
     * @param mesg message object that server will send to client
     */
    public void sendMesgTo(Object mesg) throws IOException {

        try (BufferedOutputStream clientBufferedStream = new BufferedOutputStream(this.clientSocket.getOutputStream())) {
            ObjectOutputStream clientObjectStream = new ObjectOutputStream(clientBufferedStream);
            clientObjectStream.writeObject(mesg);
            clientObjectStream.flush();
        } finally {
            clientSocket.close();
            System.out.println("close");
        }
    }

    @Override
    public void run() {
        try {
            sendMesgTo(this.sendMesg);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
