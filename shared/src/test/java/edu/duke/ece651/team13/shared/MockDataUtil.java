package edu.duke.ece651.team13.shared;

import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.map.V1Map;
import edu.duke.ece651.team13.shared.map.V1Map24Territories;
import edu.duke.ece651.team13.shared.player.HumanPlayer;
import edu.duke.ece651.team13.shared.player.PlayerRO;
import edu.duke.ece651.team13.shared.territory.Territory;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class MockDataUtil {
    private MockDataUtil() {
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

    public static MapRO getInitalisedV1Map24MapRO(){
        V1Map mapRO =new V1Map24Territories(10);
        ArrayList<PlayerRO> players = new ArrayList<>();
        players.add(new HumanPlayer("Red"));
        players.add(new HumanPlayer("Blue"));
        players.add(new HumanPlayer("Green"));
        players.add(new HumanPlayer("Yellow"));
        ArrayList<Iterator<Territory>> groupsIterator = mapRO.getInitialGroups();
        for (int i = 0; i < players.size(); i++) {
            while (groupsIterator.get(i).hasNext()) {
                Territory t = groupsIterator.get(i).next();
                t.setOwner(players.get(i));
            }
        }
        return mapRO;
    }


}
