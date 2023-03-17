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
        Ack ack = new Ack(SUCCESS, "Successfully received the player name");
        sendMessage(socket, ack);
        while (true) {
            MapRO mapRO = (MapRO) recvMessage(socket);
            ArrayList<PlayerOrderInput> orderInputs = gameRound.initialRound(mapRO);
            sendMessage(socket, orderInputs);
            Ack ack2 = (Ack) recvMessage(socket);
            if (ack2.getStatus().equals(SUCCESS)) {
                System.out.println("Sucessfull placed Order");
                break;
            } else {
                System.out.println("FAILED with the error message " + ack2.getMessage());
            }
        }
/**
 // identifies which player and ask for action
 Player player = new HumanPlayer("Green", new Socket());
 //    Client client = new Client("", 12345, player);
 try {
 player.placeUnits();  // send the placement orders to server
 player.playOneTurn();
 } catch (IOException e) {
 e.printStackTrace();
 }
 **/
    }
}
