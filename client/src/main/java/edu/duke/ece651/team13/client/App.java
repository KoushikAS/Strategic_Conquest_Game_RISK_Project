package edu.duke.ece651.team13.client;

import edu.duke.ece651.team13.shared.Ack;

import java.io.IOException;
import java.net.Socket;

import static edu.duke.ece651.team13.shared.enums.AckStatusEnum.SUCCESS;
import static edu.duke.ece651.team13.shared.util.networkUtil.recvMessage;
import static edu.duke.ece651.team13.shared.util.networkUtil.sendMessage;


public class App {

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        App a = new App();

        Socket socket = new Socket("localhost", 12345);
        String playerName = (String) recvMessage(socket);
        Ack ack = new Ack(SUCCESS, "Successfully received the player name");
        sendMessage(socket, ack);


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
