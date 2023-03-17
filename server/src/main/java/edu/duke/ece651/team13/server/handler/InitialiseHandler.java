package edu.duke.ece651.team13.server.handler;

import edu.duke.ece651.team13.server.Game;
import edu.duke.ece651.team13.shared.Ack;
import edu.duke.ece651.team13.shared.enums.AckStatusEnum;

import java.io.IOException;
import java.net.Socket;

public class InitialiseHandler extends PlayerHandler{

    private Game game;
    private String playerName;

    public InitialiseHandler(Socket clientSocket, Game game, String playerName) {
        super(clientSocket);
        this.game = game;
        this.playerName = playerName;
    }

    @Override
    public void run() {
        try {
            //Send the Player Name
            while (true) {
                sendMessage(this.playerName);
                Ack ack = (Ack) recvMessage();
                if (ack.getStatus().equals(AckStatusEnum.SUCCESS)) {
                    break;
                }
            }

            //Send the Map
            while (true) {
                sendMessage(this.game.getMapRO());

            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
