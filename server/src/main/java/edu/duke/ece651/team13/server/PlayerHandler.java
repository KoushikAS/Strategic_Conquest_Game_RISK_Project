package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.shared.Game;
import edu.duke.ece651.team13.shared.V1Map;

import java.io.*;
import java.net.Socket;

public class PlayerHandler extends Thread{
    private Game game;

    private OutputStream clientOutputStream;
    private InputStream clientInputStream;

    public PlayerHandler(Socket clientSocket, Game game){
        this.game = game;
        try{
            this.clientOutputStream = clientSocket.getOutputStream();
            this.clientInputStream = clientSocket.getInputStream();
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
    public void sendMapToClient(){
        V1Map map = new V1Map(1);
        sendMesgTo(map);
    }

    @Override
    public void run() {
        //temporarily send the map to client first
        sendMapToClient();

        while(true){

        }
    }
}
