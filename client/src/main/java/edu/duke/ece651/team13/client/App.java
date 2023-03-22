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

import static edu.duke.ece651.team13.client.enums.RoundMapping.*;
import static edu.duke.ece651.team13.shared.enums.AckStatusEnum.SUCCESS;
import static edu.duke.ece651.team13.shared.util.mapUtil.isPlayerLost;
import static edu.duke.ece651.team13.shared.util.networkUtil.recvMessage;
import static edu.duke.ece651.team13.shared.util.networkUtil.sendMessage;


public class App {

    public static Boolean serverHandShakeToSendOrders(Socket socket, RoundFactory roundFactory, RoundMapping mapping, PrintStream out, MapRO mapRO) throws IOException, ClassNotFoundException {
        while (true) {
            GameRound gameRound = roundFactory.getRound(mapping);
            ArrayList<PlayerOrderInput> orderInputs = gameRound.executeRound(mapRO);
            sendMessage(socket, orderInputs);
            Ack ack = (Ack) recvMessage(socket);
            if (ack.getStatus().equals(SUCCESS)) {
                out.println("Sucessfull placed Order");
                sendMessage(socket, new Ack(SUCCESS, "Successfully Received Ack"));
                break;
            } else {
                out.println("FAILED with the error message " + ack.getMessage());
                //Receive the map again.
                mapRO = (MapRO) recvMessage(socket);
            }
        }

        Boolean gameOverFlag = (Boolean) recvMessage(socket);
        sendMessage(socket, new Ack(SUCCESS, "Successfully Game Status"));

        return gameOverFlag;
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        App a = new App();

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        BoardTextView boardTextView = new BoardTextView();
        Socket socket = new Socket("localhost", 12345);
        Boolean gameOverFlag;

        String playerName = (String) recvMessage(socket);
        System.out.println(playerName + " assigned by the Server. Waiting for other players to join.");
        sendMessage(socket, new Ack(SUCCESS, "Successfully received the player name"));
        RoundFactory roundFactory = new RoundFactory(playerName, boardTextView, input, System.out);

        //Initial Round.
        MapRO mapRO = (MapRO) recvMessage(socket);
        gameOverFlag = serverHandShakeToSendOrders(socket, roundFactory, INITIAL_ROUND, System.out, mapRO);

        //Executing Normal or Spectate Round until Game ends.
        do {
            mapRO = (MapRO) recvMessage(socket);
            RoundMapping roundMapping = isPlayerLost(mapRO, playerName) ? SPECTATE_ROUND : NORMAL_ROUND;
            gameOverFlag = serverHandShakeToSendOrders(socket, roundFactory, roundMapping, System.out, mapRO);
        } while (!gameOverFlag);
    }
}
