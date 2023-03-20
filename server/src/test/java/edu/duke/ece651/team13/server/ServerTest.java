package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.server.handler.Handler;
import edu.duke.ece651.team13.server.handler.HandlerFactory;
import edu.duke.ece651.team13.shared.Ack;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.order.PlayerOrderInput;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import static edu.duke.ece651.team13.server.MockDataUtil.getMockGame;
import static edu.duke.ece651.team13.shared.enums.AckStatusEnum.SUCCESS;
import static edu.duke.ece651.team13.shared.enums.OrderMappingEnum.MOVE;
import static edu.duke.ece651.team13.shared.util.networkUtil.recvMessage;
import static edu.duke.ece651.team13.shared.util.networkUtil.sendMessage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ServerTest {

    private final int testPortNum = 12345;


    public void test_start() throws IOException, ClassNotFoundException, InterruptedException {
        Game game = getMockGame(2);
        Game mockGame = mock(Game.class);
        HandlerFactory mockHandlerFactory = mock(HandlerFactory.class);
        Handler mockHandler = mock(Handler.class);

        when(mockHandlerFactory.getHandler(any(), any(), any(), any())).thenReturn(mockHandler);
        when(mockGame.isGameOver()).thenReturn(true);
        when(mockGame.getPlayersIterator()).thenReturn(game.getPlayersIterator());

        Server server = new Server(testPortNum, mockGame, mockHandlerFactory);
        Socket clientSocket1 = new Socket("", testPortNum);
        Socket clientSocket2 = new Socket("", testPortNum);
        Thread t1 = new Thread(new ThreadAssert(clientSocket1, game.getMapRO()));
        Thread t2 = new Thread(new ThreadAssert(clientSocket2, game.getMapRO()));
        server.start();



        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }

}