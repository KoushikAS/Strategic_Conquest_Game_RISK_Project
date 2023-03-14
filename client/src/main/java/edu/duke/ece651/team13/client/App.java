package edu.duke.ece651.team13.client;

import edu.duke.ece651.team13.shared.map.MapRO;

import java.io.IOException;
import java.net.Socket;


public class App {

    public static void receiveMap(Client client) throws IOException, ClassNotFoundException {
        MapRO map = (MapRO) client.recvMsg();
        BoardTextView view = new BoardTextView(map);
        System.out.println(view.display());
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        App a = new App();

        Socket socket = new Socket("localhost", 12345);
        Client client = new Client(socket);
        receiveMap(client);

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
