package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.shared.Player;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.map.V1Map12Territories;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;

import static edu.duke.ece651.team13.server.App.getMap;

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
     * Init Players and Init Game
     */
    public void start(){
        Iterator<Player> it = this.game.getPlayersIterator();

        while(it.hasNext()){
            try{

                Socket clientSocket = this.serverSocket.accept();
                //TODO Should initlaize player with the socket
               game.initPlayer(it.next().getName(), clientSocket);
                //TODO Temporary change will come and fix it

                Thread clientThread = new Thread(new PlayerHandler(clientSocket, this.game, this.game.getMap()));
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