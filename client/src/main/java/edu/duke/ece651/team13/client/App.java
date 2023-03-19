package edu.duke.ece651.team13.client;

import edu.duke.ece651.team13.shared.Ack;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.order.PlayerOrderInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import static edu.duke.ece651.team13.shared.enums.AckStatusEnum.SUCCESS;
import static edu.duke.ece651.team13.shared.util.networkUtil.recvMessage;
import static edu.duke.ece651.team13.shared.util.networkUtil.sendMessage;


public class App {

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        App a = new App();

        Socket socket = new Socket("localhost", 12345);
        String playerName = (String) recvMessage(socket);
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        GameRound gameRound = new GameRound(playerName, new BoardTextView(), input, System.out);
        sendMessage(socket, new Ack(SUCCESS, "Successfully received the player name"));
        while (true) {
            MapRO mapRO = (MapRO) recvMessage(socket);
            ArrayList<PlayerOrderInput> orderInputs = gameRound.initialRound(mapRO);
            sendMessage(socket, orderInputs);
            Ack ack2 = (Ack) recvMessage(socket);
            if (ack2.getStatus().equals(SUCCESS)) {
                System.out.println("Sucessfull placed Order");
                sendMessage(socket, new Ack(SUCCESS, "Successfully Received Ack"));
                break;
            } else {
                System.out.println("FAILED with the error message " + ack2.getMessage());
            }
        }
    }
}
