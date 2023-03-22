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

import static edu.duke.ece651.team13.server.enums.HandlerMapping.*;


public class Server {

    private final ServerSocket serverSocket;
    private final HandlerFactory handlerFactory;
    private final Game game;

    public Server(int portNum, Game gameObj, HandlerFactory handlerFactory) throws IOException {
        this.game = gameObj;
        this.serverSocket = new ServerSocket(portNum);
        this.handlerFactory = handlerFactory;
    }

    //Connecting to all players to handle different handlers.
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

    //First connect initialising name
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
     * This method announces the winning player to all other players
     */
    private void announceWinningPlayer() throws InterruptedException {
        Iterator<Player> it = this.game.getPlayersIterator();
        ArrayList<Thread> clientThreads = new ArrayList<>();
        while (it.hasNext()) {
            String winningPlayerName = game.getWinningPlayer().getName();
            Thread clientThread = new Thread(new InitialiseGameHandler(it.next().getSocket(), this.game, winningPlayerName));
            clientThread.start();
            clientThreads.add(clientThread);
        }

        // Wait for all Players to Respond.
        for (Thread thread : clientThreads) {
            thread.join();
        }
    }


    /**
     * Accept connection from multiple clients
     * and having multiple rounds of game.
     */
    public void start() throws InterruptedException, IOException {
        try {
            initalisePlayers();
            do {
                //Sending the MapRO information to each player
                connectAllPlayers(ROUND_HANDLER);
                game.playOneTurn();
                //Sending the Status of each player (i.e. Lose or playing)
                connectAllPlayers(PLAYER_STATUS);
                // TODO: for testing only
                // game.fastForward();
            } while (!game.isGameOver());
            connectAllPlayers(END_GAME);
            announceWinningPlayer();
        } finally {
            // Closing the socket of Player and Server.
            Iterator<Player> it = this.game.getPlayersIterator();
            while (it.hasNext()) {
                Player player = it.next();
                player.getSocket().close();
            }
            this.serverSocket.close();
        }
    }

}