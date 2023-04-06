package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.server.entity.*;
import edu.duke.ece651.team13.shared.player.Player;
import edu.duke.ece651.team13.shared.map.V1Map;
import edu.duke.ece651.team13.shared.territory.Territory;
import org.checkerframework.checker.units.qual.A;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import static edu.duke.ece651.team13.server.App.getMap;
import static edu.duke.ece651.team13.server.App.getPlayers;

public class MockDataUtil {
    private MockDataUtil() {
    }

    public static GameEntity getGameEntity(){
        GameEntity game =  new GameEntity();
        game.setMap(getMapEntity());
        return game;
    }

    public static PlayerEntity getPlayerEntity(){
        return  new PlayerEntity("Red");
    }

    public static MapEntity getMapEntity(){
        MapEntity map = new MapEntity();
        List<TerritoryEntity> territoryEntityList = new ArrayList<>();
        for(long i =0; i <5;i ++) {
            TerritoryEntity territory = getTerritoryEntity();
            territory.setId(i);
            territoryEntityList.add(territory);
        }
        map.setTerritories(territoryEntityList);
        return map;
    }

    public static TerritoryEntity getTerritoryEntity(){
        TerritoryEntity territory = new TerritoryEntity();
        territory.setUnitNum(10);
        return territory;
    }

    public static AttackerEntity getAttackerEntity(TerritoryEntity territory){
        return new AttackerEntity(territory, getPlayerEntity(), 5);
    }

    public static RiscGame getMockGame(V1Map map, int noPlayers) {
        ArrayList<Player> players = getPlayers(noPlayers);
        return new RiscGame(map, players);
    }

    public static RiscGame getMockGame(int noPlayers) {
        V1Map map = getMap(noPlayers);
        ArrayList<Player> players = getPlayers(noPlayers);
        return new RiscGame(map, players);
    }

    public static RiscGame getMockGame(int noPlayers, Dice dice) {
        V1Map map = getMap(noPlayers);
        ArrayList<Player> players = getPlayers(noPlayers);
        return new RiscGame(map, players, dice);
    }

    /**
     * mocks input stream for testing sockets
     *
     * @param object to be mocked
     * @return
     */
    public static ByteArrayInputStream mockInputStream(Object object) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectStream = new ObjectOutputStream(outputStream);
        objectStream.writeObject(object);
        objectStream.flush();
        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    public static byte[] getByteArray(Object object) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(object);
        oos.flush();
        return bos.toByteArray();
    }


}

