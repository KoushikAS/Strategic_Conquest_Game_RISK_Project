package edu.duke.ece651.team13.server;

import java.io.*;
import java.net.Socket;

public class PlayerHandler extends Thread{
    private Game game;

    private OutputStream clientOutputStream;

    private Object sendMesg;

    public PlayerHandler(Socket clientSocket, Game game, Object sendMesg){
        this.game = game;
        this.sendMesg = sendMesg;
        try{
            this.clientOutputStream = clientSocket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send message object to the client
     * @param mesg message object that server will send to client
     */
    public void sendMesgTo(Object mesg){
        try {
            BufferedOutputStream clientBufferedStream = new BufferedOutputStream(this.clientOutputStream);
            ObjectOutputStream clientObjectStream = new ObjectOutputStream(clientBufferedStream);
            clientObjectStream.writeObject(mesg);
            clientObjectStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send a map to client (temporary function)
     */
//    public void sendMapToClient(){
//        V1Map map = new V1Map9Territories(1);
//        sendMesgTo(map);
//    }

    @Override
    public void run() {
        //temporarily send the map to client first
        sendMesgTo(this.sendMesg);
    }
}
