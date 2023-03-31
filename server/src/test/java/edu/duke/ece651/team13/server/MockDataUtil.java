package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.server.entity.AttackerEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.shared.player.Player;
import edu.duke.ece651.team13.shared.map.V1Map;
import org.checkerframework.checker.units.qual.A;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static edu.duke.ece651.team13.server.App.getMap;
import static edu.duke.ece651.team13.server.App.getPlayers;

public class MockDataUtil {
    private MockDataUtil() {
    }

    public static PlayerEntity getPlayerEntity(){
        return  new PlayerEntity("Red");
    }

    public static TerritoryEntity getTerritoryEntity(){
        return  new TerritoryEntity("Labrador");
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

