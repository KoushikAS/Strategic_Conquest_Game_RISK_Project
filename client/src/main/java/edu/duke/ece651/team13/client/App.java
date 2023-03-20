package edu.duke.ece651.team13.client;

import edu.duke.ece651.team13.client.enums.RoundMapping;
import edu.duke.ece651.team13.client.round.GameRound;
import edu.duke.ece651.team13.client.round.RoundFactory;
import edu.duke.ece651.team13.shared.Ack;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.order.PlayerOrderInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

import static edu.duke.ece651.team13.client.enums.RoundMapping.INITIAL_ROUND;
import static edu.duke.ece651.team13.client.enums.RoundMapping.NORMAL_ROUND;
import static edu.duke.ece651.team13.shared.enums.AckStatusEnum.SUCCESS;
import static edu.duke.ece651.team13.shared.util.networkUtil.recvMessage;
import static edu.duke.ece651.team13.shared.util.networkUtil.sendMessage;


public class App {

    public static Boolean serverHandshake(Socket socket, RoundFactory roundFactory, RoundMapping mapping, String playerName, BoardTextView boardTextView, BufferedReader inputReader, PrintStream out) throws IOException, ClassNotFoundException {
        while (true) {
            MapRO mapRO = (MapRO) recvMessage(socket);
            GameRound gameRound = roundFactory.getRound(mapping, playerName, boardTextView, inputReader, out);
            ArrayList<PlayerOrderInput> orderInputs = gameRound.executeRound(mapRO);
            sendMessage(socket, orderInputs);
            Ack ack = (Ack) recvMessage(socket);
            if (ack.getStatus().equals(SUCCESS)) {
                out.println("Sucessfull placed Order");
                sendMessage(socket, new Ack(SUCCESS, "Successfully Received Ack"));
                break;
            } else {
                out.println("FAILED with the error message " + ack.getMessage());
            }
        }

        Boolean gameOverFlag = (Boolean) recvMessage(socket);
        sendMessage(socket, new Ack(SUCCESS, "Successfully Game Status"));

        return gameOverFlag;
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        App a = new App();

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        RoundFactory roundFactory = new RoundFactory();
        BoardTextView boardTextView = new BoardTextView();
        Socket socket = new Socket("localhost", 12345);
        Boolean gameOverFlag;

        String playerName = (String) recvMessage(socket);
        sendMessage(socket, new Ack(SUCCESS, "Successfully received the player name"));

        gameOverFlag = serverHandshake(socket, roundFactory, INITIAL_ROUND, playerName, boardTextView, input, System.out);

        do {
            gameOverFlag = serverHandshake(socket, roundFactory, NORMAL_ROUND, playerName, boardTextView, input, System.out);
        } while (gameOverFlag);
    }
}
