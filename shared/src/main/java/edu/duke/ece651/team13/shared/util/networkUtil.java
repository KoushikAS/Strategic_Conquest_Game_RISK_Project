package edu.duke.ece651.team13.shared.util;

import java.io.*;
import java.net.Socket;

public class networkUtil {

    /**
     * Send message object to the socket
     *
     * @param message message object that server will send to client
     */
    public static void sendMessage(Socket socket, Object message) throws IOException {
        try (BufferedOutputStream clientBufferedStream = new BufferedOutputStream(socket.getOutputStream())) {
            ObjectOutputStream clientObjectStream = new ObjectOutputStream(clientBufferedStream);
            clientObjectStream.writeObject(message);
            clientObjectStream.flush();
        }
    }

    /**
     * This method receives some message from socket
     *
     * @return the received object
     */
    public static Object recvMessage(Socket socket) throws IOException, ClassNotFoundException {
        try(BufferedInputStream readBufferForClient = new BufferedInputStream(socket.getInputStream())) {
            ObjectInputStream objectStreamFromClient = new ObjectInputStream(readBufferForClient);
            return objectStreamFromClient.readObject();
        }
    }
}
