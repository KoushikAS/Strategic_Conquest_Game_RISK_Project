package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.shared.Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    private final int portNum;
    private ServerSocket serverSocket;

    private Game game;

    public Server(int portNum, Game gameObj){
        this.portNum = portNum;
        this.game = gameObj;
        buildServer();
    }

    /**
     * Accept connection from multiple clients
     */
    public void connectToClients(){
        for(int i=0; i<this.game.getMaxPlayers(); i++){
            try{
                Socket clientSocket = this.serverSocket.accept();
                Thread clientThread = new Thread(new PlayerHandler(clientSocket, this.game));
                clientThread.start();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Initialize serverSocket using port number to build server
     */
    private void buildServer(){
        try {
            this.serverSocket = new ServerSocket(portNum);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Close serverSocket to end server
     */
    public void closeServer(){
        try {
            if(this.serverSocket!=null) {
                this.serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}