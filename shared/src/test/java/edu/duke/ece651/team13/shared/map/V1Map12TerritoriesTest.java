package edu.duke.ece651.team13.shared.map;

import edu.duke.ece651.team13.shared.territory.Territory;
import edu.duke.ece651.team13.shared.territory.TerritoryRO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class V1Map12TerritoriesTest {

    @Test
    void test_initMap() {
        V1Map m1 = new V1Map12Territories(12);

        Iterator<Territory> it1 = m1.getTerritoriesIterator();

        Territory rottweiler = it1.next();
        Territory dachshund = it1.next();
        Territory beagle = it1.next();
        Territory labrador = it1.next();
        Territory poodle = it1.next();
        Territory bulldog = it1.next();
        Territory boxer = it1.next();
        Territory havanese = it1.next();
        Territory spaniel = it1.next();
        Territory sheepdog = it1.next();
        Territory akita = it1.next();
        Territory brittany = it1.next();

        assertEquals(rottweiler.getName(), "Rottweiler");
        Iterator<TerritoryRO> it = rottweiler.getNeighbourIterartor();
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

    @Test
    void test_getTerritoriesIterator(){
        MapRO m1 = new V1Map12Territories(12);
        ArrayList<Iterator<Territory>> groupsIteratorList = m1.getInitialGroups();
        Iterator<Territory> iterator1 = groupsIteratorList.get(0);
        Iterator<Territory> iterator2 = groupsIteratorList.get(1);

        Territory rottweiler = iterator1.next();
        Territory dachshund = iterator1.next();
        Territory beagle = iterator1.next();
        Territory labrador = iterator1.next();
        Territory poodle = iterator1.next();
        Territory bulldog = iterator1.next();

        Territory boxer = iterator2.next();
        Territory havanese = iterator2.next();
        Territory spaniel = iterator2.next();
        Territory sheepdog = iterator2.next();
        Territory akita = iterator2.next();
        Territory brittany = iterator2.next();

        assertEquals(rottweiler.getName(), "Rottweiler");
        assertEquals(dachshund.getName(), "Dachshund");
        assertEquals(beagle.getName(), "Beagle");
        assertEquals(labrador.getName(), "Labrador");
        assertEquals(poodle.getName(), "Poodle");
        assertEquals(bulldog.getName(), "Bulldog");

        assertEquals(boxer.getName(), "Boxer");
        assertEquals(havanese.getName(), "Havanese");
        assertEquals(spaniel.getName(), "Spaniel");
        assertEquals(sheepdog.getName(), "Sheepdog");
        assertEquals(akita.getName(), "Akita");
        assertEquals(brittany.getName(), "Brittany");
    }
}