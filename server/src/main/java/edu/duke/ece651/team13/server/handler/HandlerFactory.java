package edu.duke.ece651.team13.server.handler;

import edu.duke.ece651.team13.server.Game;
import edu.duke.ece651.team13.server.enums.HandlerMapping;

import java.net.Socket;

public class HandlerFactory {

    public Handler getHandler(HandlerMapping handlerMapping, Socket clientSocket, Game game, String playerName) {
        switch (handlerMapping) {
            case ROUND_HANDLER:
                return new RoundHandler(clientSocket, game, playerName);
            case INITIALISE_GAME:
                return new InitialiseGameHandler(clientSocket, game, playerName);
            default:
                return new PlayerStatusHandler(clientSocket, game, playerName);
        }
    }
}
