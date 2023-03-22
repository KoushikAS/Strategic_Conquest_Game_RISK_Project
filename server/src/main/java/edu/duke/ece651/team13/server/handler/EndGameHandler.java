package edu.duke.ece651.team13.server.handler;

import edu.duke.ece651.team13.server.Game;
import edu.duke.ece651.team13.shared.Ack;

import java.io.IOException;
import java.net.Socket;

import static edu.duke.ece651.team13.shared.enums.AckStatusEnum.SUCCESS;
import static edu.duke.ece651.team13.shared.util.networkUtil.recvMessage;
import static edu.duke.ece651.team13.shared.util.networkUtil.sendMessage;

public class EndGameHandler extends Handler {

    /**
     * This handler is used to send the map to the player and validate the orders sent by the player back.
     *
     * @param clientSocket is the client socket
     * @param game         is the current game
     * @param playerName   is the player's name
     */
    public EndGameHandler(Socket clientSocket, Game game, String playerName) {
        this.socket = clientSocket;
        this.game = game;
        this.playerName = playerName;
    }

    @Override
    public void run() {
        try {
            //Send the Map & Receive order
            while (true) {
                sendMessage(this.socket, this.game.getMapRO());
                recvMessage(this.socket);
                sendMessage(this.socket, new Ack(SUCCESS, "Successfully received ack"));
                recvMessage(this.socket);
                // send the game over flag
                boolean isGameOver = true;
                sendMessage(this.socket, isGameOver);
                Ack ack = (Ack) recvMessage(this.socket);
                if (ack.getStatus().equals(SUCCESS)) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
