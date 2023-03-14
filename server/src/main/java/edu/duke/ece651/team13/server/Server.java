package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.shared.Player;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;


public class Server {
    private final int portNum;
    private ServerSocket serverSocket;

    private Game game;

    public Server(int portNum, Game gameObj) throws IOException {
        this.portNum = portNum;
        this.game = gameObj;
        this.serverSocket = new ServerSocket(portNum);
    }

    /**
     * Accept connection from multiple clients
     * Init Players and Init Game
     */
    public void start() {
        Iterator<Player> it = this.game.getPlayersIterator();

        while (it.hasNext()) {
            try {
                System.out.println("Listening");
                Socket clientSocket = this.serverSocket.accept();
                System.out.println("Connected");
                //TODO Should initlaize player with the socket
                game.initPlayer(it.next().getName(), clientSocket);
                Thread clientThread = new Thread(new PlayerHandler(clientSocket, this.game.getMap()));
                clientThread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Close serverSocket to end server
     */
    public void closeServer() throws IOException {

            if (this.serverSocket != null) {
                this.serverSocket.close();
            }

    }
}