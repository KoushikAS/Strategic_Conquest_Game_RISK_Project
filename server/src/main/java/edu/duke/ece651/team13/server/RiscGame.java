package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.shared.HumanPlayer;
import edu.duke.ece651.team13.shared.Player;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.map.V1Map;
import edu.duke.ece651.team13.shared.order.MoveOrder;
import edu.duke.ece651.team13.shared.order.Order;
import edu.duke.ece651.team13.shared.territory.Territory;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class RiscGame implements Game {

    private ArrayList<Player> players;
    private V1Map map;

    public RiscGame(V1Map map, ArrayList<Player> players) {
        this.map = map;
        this.players = players;
        assignInitialGroups();
    }


    @Override
    public Iterator<Player> getPlayersIterator() {
        return players.iterator();
    }

    /**
     * Create one player and add him to players list
     *
     * @param name         name of one player
     * @param clientSocket socket of one player used to communicate with client
     */
    @Override
    public void initPlayer(String name, Socket clientSocket) {
       // Player player = new HumanPlayer(name);
       // this.players.add(player);
    }

    /**
     * Assign groups to each player after init all players
     */
    private void assignInitialGroups() {
        ArrayList<Iterator<Territory>> groupsIterator = this.map.getInitialGroups();
        assert(groupsIterator.size() == this.players.size());
        for (int i = 0; i < this.players.size(); i++) {
            while (groupsIterator.get(i).hasNext()) {
                Territory t = groupsIterator.get(i).next();
                t.setOwner(this.players.get(i));
            }
        }
    }


    /**
     * Get the board of one game
     *
     * @return Board game board
     */
    @Override
    public MapRO getMap() {
        return this.map;
    }

    @Override
    public void playOneTurn() {
    }

    @Override
    public String validateOrders(ArrayList<Order> orders) {
        // Deep copy the map
        MapRO tempMap = map.replicate();
        // Move orders first
        for (Order order : orders) {
            if (order.getClass().equals(MoveOrder.class)) {
                String checkResult = order.validateOnMap(tempMap);
                if (checkResult != null) return checkResult;
                order.actOnMap(tempMap);
            }
        }
        // Attack orders
        // The orders are valid, return null
        return null;
    }

    @Override
    public Player getPlayerByName(String name) {
        for (Player p : players) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

}
