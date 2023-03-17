package edu.duke.ece651.team13.server;


import edu.duke.ece651.team13.shared.AttackerInfo;
import edu.duke.ece651.team13.shared.Player;
import edu.duke.ece651.team13.shared.enums.PlayerStatusEnum;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.map.V1Map;
import edu.duke.ece651.team13.shared.order.AttackOrder;
import edu.duke.ece651.team13.shared.order.MoveOrder;
import edu.duke.ece651.team13.shared.order.Order;
import edu.duke.ece651.team13.shared.territory.Territory;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;

public class RiscGame implements Game {

    private ArrayList<Player> players;
    private V1Map map;
    private Dice dice;

    public RiscGame(V1Map map, ArrayList<Player> players) {
        this.map = map;
        this.players = players;
        assignInitialGroups();
        this.dice = new Dice(1, 20);

    }

    /**
     * Construct the game with a specified dice
     */
    public RiscGame(V1Map map, ArrayList<Player> players, Dice dice) {
        this.map = map;
        this.players = players;
        assignInitialGroups();
        this.dice = dice;
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
        assert (groupsIterator.size() == this.players.size());
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
        for (Order order : orders) {
            if (order.getClass().equals(AttackOrder.class)) {
                String checkResult = order.validateOnMap(tempMap);
                if (checkResult != null) return checkResult;
                order.actOnMap(tempMap);
            }
        }
        // The orders are valid, return null
        return null;
    }

    /**
     * Get the list of defender and attackers in the territory for the current round
     *
     * @return an ArrayList<AttackerInfo>, the first element is the information of the defender
     */
    public ArrayList<AttackerInfo> getWarParties(Territory territory) {
        ArrayList<AttackerInfo> warPartiesList = new ArrayList<>();
        warPartiesList.add(new AttackerInfo(territory.getOwner(), territory.getUnitNum()));
        HashMap<Player, Integer> attackerMap = territory.getAttackers();
        if (!attackerMap.isEmpty()) {
            for (Player attacker : attackerMap.keySet()) {
                warPartiesList.add(new AttackerInfo(attacker, attackerMap.get(attacker)));
            }
        }
        return warPartiesList;
    }

    public void resolveCombatInOneTerritory(Territory territory) {
        ArrayList<AttackerInfo> warParties = getWarParties(territory);
        while (warParties.size() > 1) {
            for (int currIndex = 0; currIndex < warParties.size(); currIndex++) {
                int nextIndex = currIndex == warParties.size() - 1 ? 0 : currIndex + 1;
                int loser = getLoser(territory, warParties, currIndex, nextIndex);
                loseOneRoll(loser, warParties);
                if (warParties.get(loser).getUnitNum() == 0) {
                    warParties.remove(loser);
                    if (warParties.size() == 1) break;
                    if (loser == currIndex) currIndex--;
                }
            }
        }
        AttackerInfo winnerInfo = warParties.get(0);
        territory.setOwner(winnerInfo.getAttacker());
        territory.setUnitNum(winnerInfo.getUnitNum());
        territory.clearAttackers();
    }

    private int getLoser(Territory territory, ArrayList<AttackerInfo> warParties, int currIndex, int nextIndex) {
        int currScore = rollDice();
        int nextScore = rollDice();
        int loser;
        if (currScore > nextScore) {
            loser = nextIndex;
        } else if (currScore < nextScore) {
            loser = currIndex;
        } else {
            if (warParties.get(currIndex).getAttacker() == territory.getOwner()) loser = nextIndex;
            else if (warParties.get(nextIndex).getAttacker() == territory.getOwner()) loser = currIndex;
            else {
                // Tie between two attackers, roll again
                while (currScore == nextScore) {
                    currScore = rollDice();
                    nextScore = rollDice();
                }
                loser = currScore > nextScore ? nextIndex : currIndex;
            }
        }
        return loser;
    }

    /**
     * Roll the dice -> Extract as method for stubbing convenience
     *
     * @return the result of dice
     */
    int rollDice() {
        return dice.roll();
    }

    /**
     * Helper function to handle the lose of one roll
     * Decrease unit number by 1
     */
    private void loseOneRoll(int playerIndex,
                             ArrayList<AttackerInfo> warParties) {
        AttackerInfo loserInfo = warParties.get(playerIndex);
        int newUnitNum = loserInfo.getUnitNum() - 1;
        loserInfo.setUnitNum(newUnitNum);
    }

    @Override
    public void resolveAllCombats() {
        for (Iterator<Territory> it = map.getTerritoriesIterator(); it.hasNext(); ) {
            Territory territory = it.next();
            resolveCombatInOneTerritory(territory);
        }
    }

    @Override
    public Player getPlayerByName(String name) {
        Optional<Player> player = players.stream().filter(t -> t.getName().equals(name)).findAny();
        if (!player.isPresent()) {
            throw new IllegalArgumentException("There is no Player in this game with the name " + name);
        }
        return player.get();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void checkLostPlayer() {
        Iterator<Player> playerIterator = getPlayersIterator();
        while (playerIterator.hasNext()) {
            Player player = playerIterator.next();
            if (playerHasLost(player)) {
                player.setStatus(PlayerStatusEnum.LOSE);
            }
        }
    }

    /**
     * This helper method checks if a player has lost
     *
     * @param player is the player to be checked
     * @return true if the player has already lost and false otherwise
     */
    private boolean playerHasLost(Player player) {
        Iterator<Territory> territoryIterator = map.getTerritoriesIterator();
        while (territoryIterator.hasNext()) {
            Territory territory = territoryIterator.next();
            if (territory.getOwner().equals(player)) {
                return false;
            }
        }
        return true;
    }

}
