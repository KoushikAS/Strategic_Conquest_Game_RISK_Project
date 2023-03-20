package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.server.enums.HandlerMapping;
import edu.duke.ece651.team13.server.handler.HandlerFactory;
import edu.duke.ece651.team13.server.handler.InitialiseGameHandler;
import edu.duke.ece651.team13.shared.player.Player;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import static edu.duke.ece651.team13.server.enums.HandlerMapping.PLAYER_STATUS;
import static edu.duke.ece651.team13.server.enums.HandlerMapping.ROUND_HANDLER;


public class Server {

    private final ServerSocket serverSocket;
    private final HandlerFactory handlerFactory;
    private final Game game;

    public Server(int portNum, Game gameObj) throws IOException {
        this.game = gameObj;
        this.serverSocket = new ServerSocket(portNum);
        this.handlerFactory = new HandlerFactory();
    }

    private void connectAllPlayers(HandlerMapping handlerMapping) throws InterruptedException {
        Iterator<Player> it = this.game.getPlayersIterator();
        ArrayList<Thread> clientThreads = new ArrayList<>();
        while (it.hasNext()) {
            Player player = it.next();
            Thread clientThread = new Thread(handlerFactory.getHandler(handlerMapping, player.getSocket(), this.game, player.getName()));
            clientThread.start();
            clientThreads.add(clientThread);
        }

        //Wait for all Players to Respond.
        for (Thread thread : clientThreads) {
            thread.join();
        }
    }

    private void initalisePlayers() throws IOException, InterruptedException {
        Iterator<Player> it = this.game.getPlayersIterator();
        ArrayList<Thread> clientThreads = new ArrayList<>();
        while (it.hasNext()) {
            Socket clientSocket = this.serverSocket.accept();
            String playerName = it.next().getName();
            game.initPlayer(playerName, clientSocket);
            Thread clientThread = new Thread(new InitialiseGameHandler(clientSocket, this.game, playerName));
            clientThread.start();
            clientThreads.add(clientThread);
        }

        //Wait for all Players to Respond.
        for (Thread thread : clientThreads) {
            thread.join();
        }
    }


    /**
     * Accept connection from multiple clients
     * Init Players and Init Game
     */
    public void start() throws InterruptedException, IOException {

        initalisePlayers();

        //TODO: Currently running forever Should end it when the game is done.
        while (true) {
            //Sending the MapRO information to each player
            connectAllPlayers(ROUND_HANDLER);
            game.playOneTurn();
            //Sending the Status of each player (i.e. Loose or playing)
            connectAllPlayers(PLAYER_STATUS);
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