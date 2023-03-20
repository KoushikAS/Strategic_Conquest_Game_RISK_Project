package edu.duke.ece651.team13.server.handler;

import edu.duke.ece651.team13.server.Game;

import java.net.Socket;

public abstract class Handler extends Thread {
    protected Game game;
    protected String playerName;
    protected Socket socket;
}
