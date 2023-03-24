package edu.duke.ece651.team13.server.handler;

import edu.duke.ece651.team13.server.Game;
import edu.duke.ece651.team13.shared.Ack;
import edu.duke.ece651.team13.shared.order.PlayerOrderInput;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import static edu.duke.ece651.team13.shared.enums.AckStatusEnum.FAIL;
import static edu.duke.ece651.team13.shared.enums.AckStatusEnum.SUCCESS;
import static edu.duke.ece651.team13.shared.util.networkUtil.recvMessage;
import static edu.duke.ece651.team13.shared.util.networkUtil.sendMessage;

public class RoundHandler extends Handler {

    /**
     * This handler is used to send the map to the player and validate the orders sent by the player back.
     * @param clientSocket
     * @param game
     * @param playerName
     */
    public RoundHandler(Socket clientSocket, Game game, String playerName) {
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
                ArrayList<PlayerOrderInput> orders = (ArrayList<PlayerOrderInput>) recvMessage(this.socket);
                String errorMessage = this.game.validateOrdersAndAddToList(orders, this.game.getPlayerByName(this.playerName));
                if (errorMessage == null) {
                    sendMessage(this.socket, new Ack(SUCCESS, "Successfully added orders"));
                    recvMessage(this.socket);
                    break;
                } else {
                    Ack ack = new Ack(FAIL, errorMessage);
                    sendMessage(this.socket, ack);
                    recvMessage(this.socket);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
