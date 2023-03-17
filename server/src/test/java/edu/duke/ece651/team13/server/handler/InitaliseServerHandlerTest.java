package edu.duke.ece651.team13.server.handler;

import edu.duke.ece651.team13.shared.Ack;
import edu.duke.ece651.team13.shared.enums.AckStatusEnum;
import edu.duke.ece651.team13.shared.order.PlayerOrderInput;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import static edu.duke.ece651.team13.server.MockDataUtil.getMockGame;
import static edu.duke.ece651.team13.server.MockDataUtil.mockInputStream;
import static edu.duke.ece651.team13.shared.enums.OrderMappingEnum.MOVE;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InitaliseServerHandlerTest {

    @Test
    public void test_Run() throws IOException {
        ArrayList<PlayerOrderInput> input1 = new ArrayList<>();
        input1.add(new PlayerOrderInput(MOVE, "Rottweiler", "Dachshund", 0));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Socket mockTestSocket = mock(Socket.class);
        when(mockTestSocket.getOutputStream()).thenReturn(byteArrayOutputStream);
        when(mockTestSocket.getInputStream())
                .thenReturn(mockInputStream(new Ack(AckStatusEnum.SUCCESS, "Sucess")),mockInputStream(input1));

        InitialiseServerHandler mockClass = new InitialiseServerHandler(mockTestSocket, getMockGame(2), "Red");
        mockClass.start();;

        //TODO how to verify this properly
        System.out.println(byteArrayOutputStream.toByteArray());
    }

    @Test
    public void test_RunWithBadACK() throws IOException {
        ArrayList<PlayerOrderInput> input1 = new ArrayList<>();
        input1.add(new PlayerOrderInput(MOVE, "Rottweiler", "Dachshund", 0));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Socket mockTestSocket = mock(Socket.class);
        when(mockTestSocket.getOutputStream()).thenReturn(byteArrayOutputStream);
        when(mockTestSocket.getInputStream())
                .thenReturn(mockInputStream(new Ack(AckStatusEnum.FAIL, "Fail")),
                        mockInputStream(new Ack(AckStatusEnum.SUCCESS, "Sucess"))
                        ,mockInputStream(input1));

        InitialiseServerHandler mockClass = new InitialiseServerHandler(mockTestSocket, getMockGame(2), "Red");
        mockClass.start();;

        //TODO how to verify this properly
        System.out.println(byteArrayOutputStream.toByteArray());
    }

    @Test
    public void test_RunWithBadOrder() throws IOException {
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

        InitialiseServerHandler mockClass = new InitialiseServerHandler(mockTestSocket, getMockGame(2), "Red");
        mockClass.start();;

        //TODO how to verify this properly
        System.out.println(byteArrayOutputStream.toByteArray());
    }
}
