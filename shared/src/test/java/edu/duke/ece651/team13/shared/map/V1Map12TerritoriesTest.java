package edu.duke.ece651.team13.shared.map;

import edu.duke.ece651.team13.shared.Territory;
import edu.duke.ece651.team13.shared.map.Map;
import edu.duke.ece651.team13.shared.map.V1Map12Territories;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class V1Map12TerritoriesTest {

    @Test
    void test_initMap() {
        Map m1 = new V1Map12Territories(12);

        Iterator<Territory> it = m1.getTerritoriesIterator();

        Territory rottweiler = it.next();
        Territory dachshund = it.next();
        Territory beagle = it.next();
        Territory labrador = it.next();
        Territory poodle = it.next();
        Territory bulldog = it.next();
        Territory boxer = it.next();
        Territory havanese = it.next();
        Territory spaniel = it.next();
        Territory sheepdog = it.next();
        Territory akita = it.next();
        Territory brittany = it.next();

        assertEquals(rottweiler.getName(), "Rottweiler");
        it = rottweiler.getNeighbourIterartor();
        assertEquals(dachshund, it.next());
        assertEquals(labrador, it.next());
        assertEquals(poodle, it.next());

        assertEquals(dachshund.getName(), "Dachshund");
        it = dachshund.getNeighbourIterartor();
        assertEquals(rottweiler, it.next());
        assertEquals(beagle, it.next());
        assertEquals(poodle, it.next());
        assertEquals(bulldog, it.next());

        assertEquals(beagle.getName(), "Beagle");
        it = beagle.getNeighbourIterartor();
        assertEquals(dachshund, it.next());
        assertEquals(bulldog, it.next());

        assertEquals(labrador.getName(), "Labrador");
        it = labrador.getNeighbourIterartor();
        assertEquals(rottweiler, it.next());
        assertEquals(poodle, it.next());
        assertEquals(boxer, it.next());
        assertEquals(havanese, it.next());

        assertEquals(poodle.getName(), "Poodle");
        it = poodle.getNeighbourIterartor();
        assertEquals(rottweiler, it.next());
        assertEquals(dachshund, it.next());
        assertEquals(labrador, it.next());
        assertEquals(bulldog, it.next());
        assertEquals(havanese, it.next());
        assertEquals(spaniel, it.next());

        assertEquals(bulldog.getName(), "Bulldog");
        it = bulldog.getNeighbourIterartor();
        assertEquals(dachshund, it.next());
        assertEquals(beagle, it.next());
        assertEquals(poodle, it.next());
        assertEquals(spaniel, it.next());

        assertEquals(boxer.getName(), "Boxer");
        it = boxer.getNeighbourIterartor();
        assertEquals(labrador, it.next());
        assertEquals(havanese, it.next());
        assertEquals(sheepdog, it.next());

        assertEquals(havanese.getName(), "Havanese");
        it = havanese.getNeighbourIterartor();
        assertEquals(labrador, it.next());
        assertEquals(poodle, it.next());
        assertEquals(boxer, it.next());
        assertEquals(spaniel, it.next());
        assertEquals(sheepdog, it.next());
        assertEquals(akita, it.next());

        assertEquals(spaniel.getName(), "Spaniel");
        it = spaniel.getNeighbourIterartor();
        assertEquals(poodle, it.next());
        assertEquals(bulldog, it.next());
        assertEquals(havanese, it.next());
        assertEquals(akita, it.next());
        assertEquals(brittany, it.next());

        assertEquals(sheepdog.getName(), "Sheepdog");
        it = sheepdog.getNeighbourIterartor();
        assertEquals(boxer, it.next());
        assertEquals(havanese, it.next());
        assertEquals(akita, it.next());

        assertEquals(akita.getName(), "Akita");
        it = akita.getNeighbourIterartor();
        assertEquals(havanese, it.next());
        assertEquals(spaniel, it.next());
        assertEquals(sheepdog, it.next());
        assertEquals(brittany, it.next());

        assertEquals(brittany.getName(), "Brittany");
        it = brittany.getNeighbourIterartor();
        assertEquals(spaniel, it.next());
        assertEquals(akita, it.next());

    }
}