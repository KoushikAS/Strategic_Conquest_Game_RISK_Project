package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.shared.HumanPlayer;
import edu.duke.ece651.team13.shared.Player;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.order.AttackOrder;
import edu.duke.ece651.team13.shared.order.MoveOrder;
import edu.duke.ece651.team13.shared.order.PlayerOrder;
import edu.duke.ece651.team13.shared.territory.Territory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class RiscGame implements Game{

    private ArrayList<Player> players;
    private int maxPlayers;
    private Board gameBoard;

    public RiscGame(int maxNumPlayers){
        this.maxPlayers = maxNumPlayers;
        this.gameBoard = new RiscGameBoard(maxNumPlayers);
        this.players = new ArrayList<>();
    }

    /**
     * Init game: groups assignment (after init Players) and initial placement
     */
    @Override
    public void initGame(){
        //init game after init players
        assert(players.size()==maxPlayers);
        //assign groups (after init Players)
        assignGroups();
        //TODO: initial placement
    }

    /**
     * Create one player and add him to players list
     * @param name name of one player
     * @param clientSocket socket of one player used to communicate with client
     */
    @Override
    public void initPlayer(String name, Socket clientSocket){
        try{
            Player player = new HumanPlayer(name, new BufferedReader(new InputStreamReader(clientSocket.getInputStream())));
            this.players.add(player);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Assign groups to each player after init all players
     */
    private void assignGroups(){
        MapRO map = this.gameBoard.getMap();
        ArrayList<Iterator<Territory>> groupsIterator = map.getGroupsIterator();
        for(int i=0; i<this.players.size(); i++){
            while(groupsIterator.get(i).hasNext()){
                Territory t = groupsIterator.get(i).next();
                t.setOwner(this.players.get(i));
            }
        }
    }

    /**
     * Get the number of players
     * @return int number
     */
    @Override
    public int getMaxPlayers(){ return this.maxPlayers;}

    /**
     * Get the board of one game
     * @return Board game board
     */
    @Override
    public Board getBoard(){ return this.gameBoard;}

    @Override
    public void playOneTurn(){}

    @Override
    public String validateOrders(ArrayList<PlayerOrder> orders) {
        // Deep copy the map
        MapRO tempMap = gameBoard.getMap().replicate();
        // Move orders first
        for(PlayerOrder order: orders){
           if(order.getClass().equals(MoveOrder.class)){
               String checkResult = order.validateOnMap(tempMap);
               if(checkResult!=null) return checkResult;
               order.actOnMap(tempMap);
           }
        }
        // Attack orders
        for(PlayerOrder order: orders){
            if(order.getClass().equals(AttackOrder.class)){
                String checkResult = order.validateOnMap(tempMap);
                if(checkResult!=null) return checkResult;
                order.actOnMap(tempMap);
            }
        }

        // The orders are valid, return null
        return null;
    }

    @Override
    public Player getPlayerByName(String name) {
        for(Player p: players){
            if(p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }

}
