package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.shared.map.Map;

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
     * Init Players and Init Game
     */
    public void start(){
        assert(this.game.getMaxPlayers()==2||this.game.getMaxPlayers()==3||this.game.getMaxPlayers()==4);
        for(int i=0; i<this.game.getMaxPlayers(); i++){
            try{
                Socket clientSocket = this.serverSocket.accept();
                String name = getPlayerName(i);
                game.initPlayer(name, clientSocket);
                //send map to each client
                //Map map = this.game.getBoard().getMap(); (BufferedReader not serializable)
                Map map = new RiscGameBoard<>(this.game.getMaxPlayers()).getMap();
                Thread clientThread = new Thread(new PlayerHandler(clientSocket, this.game, map));
                clientThread.start();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        //after all players connect to server, init game
        game.initGame();
    }

    /**
     * Used in start() method to get each player's name (hardcode)
     * @param index The order in which players connect to the server
     * @return name
     */
    private String getPlayerName(int index){
        if(index==0) return "Green";
        else if(index==1) return "Blue";
        else if(index==2) return "Red";
        return "Yellow";
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