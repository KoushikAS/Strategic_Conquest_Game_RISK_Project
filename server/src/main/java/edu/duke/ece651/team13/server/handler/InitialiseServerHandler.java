package edu.duke.ece651.team13.server.handler;

import edu.duke.ece651.team13.server.Game;
import edu.duke.ece651.team13.shared.Ack;
import edu.duke.ece651.team13.shared.order.Order;
import edu.duke.ece651.team13.shared.order.PlayerOrderInput;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import static edu.duke.ece651.team13.shared.enums.AckStatusEnum.FAIL;
import static edu.duke.ece651.team13.shared.enums.AckStatusEnum.SUCCESS;
import static edu.duke.ece651.team13.shared.util.networkUtil.recvMessage;
import static edu.duke.ece651.team13.shared.util.networkUtil.sendMessage;

public class InitialiseServerHandler extends Thread {

    private final Game game;
    private final String playerName;
    private final Socket socket;

    public InitialiseServerHandler(Socket clientSocket, Game game, String playerName) {
        this.socket = clientSocket;
        this.game = game;
        this.playerName = playerName;
    }

    @Override
    public void run() {
        try {
            //Send the Player Name

            while (true) {
                sendMessage(this.socket, this.playerName);
                Ack ack = (Ack) recvMessage(this.socket);
                if (ack.getStatus().equals(SUCCESS)) {
                    break;
                }
            }

            //Send the Map
            while (true) {
                sendMessage(this.socket, this.game.getMapRO());
                ArrayList<PlayerOrderInput> orders = (ArrayList<PlayerOrderInput>) recvMessage(this.socket);
                String errorMessage = this.game.validateOrdersAndAddToList(orders, this.game.getPlayerByName(this.playerName));
                if (errorMessage == null) {
                    Ack ack = new Ack(SUCCESS, "Successfully added orders");
                    sendMessage(this.socket, ack);
                    break;
                } else {
                    Ack ack = new Ack(FAIL, errorMessage);
                    sendMessage(this.socket, ack);
                }

            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
