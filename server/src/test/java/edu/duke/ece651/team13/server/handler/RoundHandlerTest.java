package edu.duke.ece651.team13.server.handler;

import edu.duke.ece651.team13.server.Game;
import edu.duke.ece651.team13.shared.Ack;
import edu.duke.ece651.team13.shared.enums.AckStatusEnum;
import edu.duke.ece651.team13.shared.order.PlayerOrderInput;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import static edu.duke.ece651.team13.server.MockDataUtil.*;
import static edu.duke.ece651.team13.shared.enums.OrderMappingEnum.MOVE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RoundHandlerTest {

    @Test
    public void test_Run() throws IOException, InterruptedException {
        Game game = getMockGame(2);
        ArrayList<PlayerOrderInput> input1 = new ArrayList<>();
        input1.add(new PlayerOrderInput(MOVE, "Rottweiler", "Dachshund", 0));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Socket mockTestSocket = mock(Socket.class);
        when(mockTestSocket.getOutputStream()).thenReturn(byteArrayOutputStream);
        when(mockTestSocket.getInputStream()).thenReturn(mockInputStream(input1));

        RoundHandler mockClass = new RoundHandler(mockTestSocket, game, "Red");
        mockClass.start();

        mockClass.join();
        byte [] expected = getByteArray(game.getMapRO());
        byte [] actual = byteArrayOutputStream.toByteArray();
        for(int i =0; i < expected.length; i++ ) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void test_RunWithBadOrder() throws IOException, InterruptedException {
        Game game = getMockGame(2);
        ArrayList<PlayerOrderInput> input1 = new ArrayList<>();
        input1.add(new PlayerOrderInput(MOVE, "Rottweiler", "Dachshund", 5));
        ArrayList<PlayerOrderInput> input2 = new ArrayList<>();
        input2.add(new PlayerOrderInput(MOVE, "Rottweiler", "Dachshund", 0));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Socket mockTestSocket = mock(Socket.class);
        when(mockTestSocket.getOutputStream()).thenReturn(byteArrayOutputStream);
        when(mockTestSocket.getInputStream())
                .thenReturn(
                        mockInputStream(new Ack(AckStatusEnum.SUCCESS, "Sucess"))
                        ,mockInputStream(input1)
                        ,mockInputStream(input2));

        RoundHandler mockClass = new RoundHandler(mockTestSocket, game,  "Red");
        mockClass.start();

        mockClass.join();
        byte [] expected = getByteArray(game.getMapRO());
        byte [] actual = byteArrayOutputStream.toByteArray();
        for(int i =0; i < expected.length; i++ ) {
            assertEquals(expected[i], actual[i]);
        }
    }
}
