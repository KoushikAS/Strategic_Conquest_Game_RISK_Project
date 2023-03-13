package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.shared.AttackerInfo;
import edu.duke.ece651.team13.shared.HumanPlayer;
import edu.duke.ece651.team13.shared.Player;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.order.AttackOrder;
import edu.duke.ece651.team13.shared.order.MoveOrder;
import edu.duke.ece651.team13.shared.order.Order;
import edu.duke.ece651.team13.shared.territory.Territory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class RiscGame implements Game {

    private ArrayList<Player> players;
    private int maxPlayers;
    private Board gameBoard;
    private Dice dice;

    public RiscGame(int maxNumPlayers) {
        this.maxPlayers = maxNumPlayers;
        this.gameBoard = new RiscGameBoard(maxNumPlayers);
        this.players = new ArrayList<>();
        this.dice = new Dice(1, 20);
    }

    /**
     * Construct the game with a specified dice
     */
    public RiscGame(int maxNumPlayers, Dice dice) {
        this.maxPlayers = maxNumPlayers;
        this.gameBoard = new RiscGameBoard(maxNumPlayers);
        this.players = new ArrayList<>();
        this.dice = dice;
    }

    /**
     * Init game: groups assignment (after init Players) and initial placement
     */
    @Override
    public void initGame() {
        //init game after init players
        assert (players.size() == maxPlayers);
        //assign groups (after init Players)
        assignGroups();
        //TODO: initial placement
    }

    /**
     * Create one player and add him to players list
     *
     * @param name         name of one player
     * @param clientSocket socket of one player used to communicate with client
     */
    @Override
    public void initPlayer(String name, Socket clientSocket) {
        try {
            Player player = new HumanPlayer(name, new BufferedReader(new InputStreamReader(clientSocket.getInputStream())));
            this.players.add(player);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Assign groups to each player after init all players
     */
    private void assignGroups() {
        MapRO map = this.gameBoard.getMap();
        ArrayList<Iterator<Territory>> groupsIterator = map.getGroupsIterator();
        for (int i = 0; i < this.players.size(); i++) {
            while (groupsIterator.get(i).hasNext()) {
                Territory t = groupsIterator.get(i).next();
                t.setOwner(this.players.get(i));
            }
        }
    }

    /**
     * Get the number of players
     *
     * @return int number
     */
    @Override
    public int getMaxPlayers() {
        return this.maxPlayers;
    }

    /**
     * Get the board of one game
     *
     * @return Board game board
     */
    @Override
    public Board getBoard() {
        return this.gameBoard;
    }

    @Override
    public void playOneTurn() {
    }

    @Override
    public String validateOrders(ArrayList<Order> orders) {
        // Deep copy the map
        MapRO tempMap = gameBoard.getMap().replicate();
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
        while(warParties.size() > 1){
            for (int currIndex = 0; currIndex < warParties.size(); currIndex++) {
                int nextIndex = currIndex == warParties.size() - 1 ? 0 : currIndex + 1;
                int loser = getLoser(territory, warParties, currIndex, nextIndex);
                loseOneRoll(loser, warParties);
                if(warParties.get(loser).getUnitNum() == 0){
                    warParties.remove(loser);
                    if(warParties.size() == 1) break;
                    if(loser==currIndex) currIndex--;
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
    int rollDice(){
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
        for (Iterator<Territory> it = gameBoard.getMap().getTerritoriesIterator(); it.hasNext(); ) {
            Territory territory = it.next();
            resolveCombatInOneTerritory(territory);
        }
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
