package edu.duke.ece651.team13.shared;

import edu.duke.ece651.team13.shared.map.V1Map9Territories;
import edu.duke.ece651.team13.shared.territory.GameTerritory;
import edu.duke.ece651.team13.shared.territory.Territory;
import edu.duke.ece651.team13.shared.territory.TerritoryRO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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


    public static ArrayList<TerritoryRO> getUnconnectedTerritories(){
        ArrayList<TerritoryRO> territories = new ArrayList<>();

        Territory narnia = new GameTerritory("Narnia");
        Territory midkemia = new GameTerritory("Midkemia");
        Territory oz = new GameTerritory("Oz");

        midkemia.addNeighbours(oz);
        oz.addNeighbours(midkemia);

        territories.add(narnia);
        territories.add(midkemia);
        territories.add(oz);

        return territories;
    }
    /**
     * Helper class that initializes an unconnected map for testing
     */
    public static class UnconnectedV1Map9Territories extends V1Map9Territories {
        public UnconnectedV1Map9Territories(int initialUnit) {
            super(initialUnit);
        }

        @Override
        protected void initMap(int initialUnit) {
            // Creating Terrritores
            Territory narnia = new GameTerritory("Narnia");
            Territory midkemia = new GameTerritory("Midkemia");
            Territory oz = new GameTerritory("Oz");

            // Narnia has no neighbors
            addTerritoriesNeighbours(midkemia, oz);

            territories.add(narnia);
            territories.add(midkemia);
            territories.add(oz);
        }
    }


}
