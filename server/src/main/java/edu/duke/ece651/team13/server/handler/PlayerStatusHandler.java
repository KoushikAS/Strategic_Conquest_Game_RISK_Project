package edu.duke.ece651.team13.server.handler;

import edu.duke.ece651.team13.server.Game;
import edu.duke.ece651.team13.shared.Ack;

import java.io.IOException;
import java.net.Socket;

import static edu.duke.ece651.team13.shared.enums.AckStatusEnum.SUCCESS;
import static edu.duke.ece651.team13.shared.util.networkUtil.recvMessage;
import static edu.duke.ece651.team13.shared.util.networkUtil.sendMessage;

public class PlayerStatusHandler extends Handler {

    public PlayerStatusHandler(Socket clientSocket, Game game, String playerName) {
        this.socket = clientSocket;
        this.game = game;
        this.playerName = playerName;
    }

    @Override
    public void run() {
        try {
            while (true) {
                //Send the boolean flag to indicate is Game over.
                sendMessage(this.socket, this.game.isGameOver());
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
