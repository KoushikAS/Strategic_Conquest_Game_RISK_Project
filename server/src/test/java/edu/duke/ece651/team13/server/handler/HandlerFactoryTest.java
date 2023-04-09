package edu.duke.ece651.team13.server.handler;

import edu.duke.ece651.team13.server.Game;
import org.junit.jupiter.api.Test;

import java.net.Socket;

import static edu.duke.ece651.team13.server.enums.HandlerMapping.INITIALISE_GAME;
import static edu.duke.ece651.team13.server.enums.HandlerMapping.PLAYER_STATUS;
import static edu.duke.ece651.team13.server.enums.HandlerMapping.ROUND_HANDLER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class HandlerFactoryTest {

    @Test
    public void getHandlerTest() {
        Socket socket = mock(Socket.class);
        Game game = mock(Game.class);
        String playerName = "Red";
        HandlerFactory handlerFactory = new HandlerFactory();

        assertEquals(RoundHandler.class, handlerFactory.getHandler(ROUND_HANDLER, socket, game, playerName).getClass());
        assertEquals(InitialiseGameHandler.class, handlerFactory.getHandler(INITIALISE_GAME, socket, game, playerName).getClass());
        assertEquals(PlayerStatusHandler.class, handlerFactory.getHandler(PLAYER_STATUS, socket, game, playerName).getClass());

    }
}
