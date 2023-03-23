package edu.duke.ece651.team13.server.handler;

import edu.duke.ece651.team13.shared.Ack;
import edu.duke.ece651.team13.shared.enums.AckStatusEnum;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static edu.duke.ece651.team13.server.MockDataUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InitaliseGameHandlerTest {

    @Test
    public void test_Run() throws IOException, InterruptedException {
        String playerName = "Red";
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Socket mockTestSocket = mock(Socket.class);
        when(mockTestSocket.getOutputStream()).thenReturn(byteArrayOutputStream);
        when(mockTestSocket.getInputStream())
                .thenReturn(mockInputStream(new Ack(AckStatusEnum.SUCCESS, "Sucess")));

        InitialiseGameHandler mockClass = new InitialiseGameHandler(mockTestSocket, getMockGame(2), playerName);
        mockClass.start();

        mockClass.join();
        byte[] expected = getByteArray(playerName);
        byte[] actual = byteArrayOutputStream.toByteArray();
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void test_RunWithBadACK() throws IOException, InterruptedException {
        String playerName = "Red";
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Socket mockTestSocket = mock(Socket.class);
        when(mockTestSocket.getOutputStream()).thenReturn(byteArrayOutputStream);
        when(mockTestSocket.getInputStream())
                .thenReturn(mockInputStream(new Ack(AckStatusEnum.FAIL, "Fail")),
                        mockInputStream(new Ack(AckStatusEnum.SUCCESS, "Sucess")));

        InitialiseGameHandler mockClass = new InitialiseGameHandler(mockTestSocket, getMockGame(2), playerName);
        mockClass.start();

        mockClass.join();
        byte[] expected = getByteArray(playerName);
        byte[] actual = byteArrayOutputStream.toByteArray();
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void test_RunException() throws IOException, InterruptedException {
        String playerName = "Red";
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Socket mockTestSocket = mock(Socket.class);
        when(mockTestSocket.getOutputStream()).thenReturn(byteArrayOutputStream);
        when(mockTestSocket.getInputStream()).thenThrow(IOException.class);

        InitialiseGameHandler mockClass = new InitialiseGameHandler(mockTestSocket, getMockGame(2), playerName);
        mockClass.start();

        mockClass.join();
        byte[] expected = getByteArray(playerName);
        byte[] actual = byteArrayOutputStream.toByteArray();
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }



}