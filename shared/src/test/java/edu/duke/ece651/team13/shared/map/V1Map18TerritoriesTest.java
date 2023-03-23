package edu.duke.ece651.team13.shared.map;

import edu.duke.ece651.team13.shared.territory.Territory;
import edu.duke.ece651.team13.shared.territory.TerritoryRO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class V1Map18TerritoriesTest {

    @Test
    void initMap() {
        V1Map m1 = new V1Map18Territories(18);

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
        Territory vizsla = it1.next();
        Territory pug = it1.next();
        Territory chihuahua = it1.next();
        Territory maltese = it1.next();
        Territory mastiff = it1.next();
        Territory collie = it1.next();

        assertEquals(rottweiler.getName(), "Rottweiler");
        Iterator<TerritoryRO> it = rottweiler.getNeighbourIterartor();
        assertEquals(labrador, it.next());
        assertEquals(dachshund, it.next());
        assertEquals(spaniel, it.next());
        assertEquals(havanese, it.next());
        assertEquals(poodle, it.next());

        assertEquals(dachshund.getName(), "Dachshund");
        it = dachshund.getNeighbourIterartor();
        assertEquals(labrador, it.next());
        assertEquals(bulldog, it.next());
        assertEquals(rottweiler, it.next());
        assertEquals(beagle, it.next());
        assertEquals(poodle, it.next());

        assertEquals(beagle.getName(), "Beagle");
        it = beagle.getNeighbourIterartor();
        assertEquals(bulldog, it.next());
        assertEquals(dachshund, it.next());
        assertEquals(poodle, it.next());
        assertEquals(vizsla, it.next());
        assertEquals(pug, it.next());

        assertEquals(labrador.getName(), "Labrador");
        it = labrador.getNeighbourIterartor();
        assertEquals(bulldog, it.next());
        assertEquals(rottweiler, it.next());
        assertEquals(dachshund, it.next());

        assertEquals(poodle.getName(), "Poodle");
        it = poodle.getNeighbourIterartor();
        assertEquals(rottweiler, it.next());
        assertEquals(dachshund, it.next());
        assertEquals(beagle, it.next());
        assertEquals(spaniel, it.next());
        assertEquals(havanese, it.next());
        assertEquals(vizsla, it.next());
        assertEquals(pug, it.next());

        assertEquals(bulldog.getName(), "Bulldog");
        it = bulldog.getNeighbourIterartor();
        assertEquals(labrador, it.next());
        assertEquals(dachshund, it.next());
        assertEquals(beagle, it.next());

        assertEquals(boxer.getName(), "Boxer");
        it = boxer.getNeighbourIterartor();
        assertEquals(spaniel, it.next());
        assertEquals(brittany, it.next());
        assertEquals(akita, it.next());

        assertEquals(havanese.getName(), "Havanese");
        it = havanese.getNeighbourIterartor();
        assertEquals(rottweiler, it.next());
        assertEquals(poodle, it.next());
        assertEquals(vizsla, it.next());
        assertEquals(spaniel, it.next());
        assertEquals(brittany, it.next());
        assertEquals(sheepdog, it.next());
        assertEquals(maltese, it.next());

        assertEquals(spaniel.getName(), "Spaniel");
        it = spaniel.getNeighbourIterartor();
        assertEquals(rottweiler, it.next());
        assertEquals(poodle, it.next());
        assertEquals(havanese, it.next());
        assertEquals(brittany, it.next());
        assertEquals(boxer, it.next());

        assertEquals(sheepdog.getName(), "Sheepdog");
        it = sheepdog.getNeighbourIterartor();
        assertEquals(havanese, it.next());
        assertEquals(brittany, it.next());
        assertEquals(akita, it.next());
        assertEquals(vizsla, it.next());
        assertEquals(maltese, it.next());

        assertEquals(akita.getName(), "Akita");
        it = akita.getNeighbourIterartor();
        assertEquals(brittany, it.next());
        assertEquals(sheepdog, it.next());
        assertEquals(boxer, it.next());

        assertEquals(brittany.getName(), "Brittany");
        it = brittany.getNeighbourIterartor();
        assertEquals(havanese, it.next());
        assertEquals(spaniel, it.next());
        assertEquals(sheepdog, it.next());
        assertEquals(boxer, it.next());
        assertEquals(akita, it.next());

        assertEquals(vizsla.getName(), "Vizsla");
        it = vizsla.getNeighbourIterartor();
        assertEquals(beagle, it.next());
        assertEquals(poodle, it.next());
        assertEquals(havanese, it.next());
        assertEquals(pug, it.next());
        assertEquals(chihuahua, it.next());
        assertEquals(maltese, it.next());
        assertEquals(sheepdog, it.next());

        assertEquals(pug.getName(), "Pug");
        it = pug.getNeighbourIterartor();
        assertEquals(beagle, it.next());
        assertEquals(poodle, it.next());
        assertEquals(vizsla, it.next());
        assertEquals(chihuahua, it.next());
        assertEquals(mastiff, it.next());

        assertEquals(chihuahua.getName(), "Chihuahua");
        it = chihuahua.getNeighbourIterartor();
        assertEquals(vizsla, it.next());
        assertEquals(pug, it.next());
        assertEquals(maltese, it.next());
        assertEquals(mastiff, it.next());
        assertEquals(collie, it.next());

        assertEquals(maltese.getName(), "Maltese");
        it = maltese.getNeighbourIterartor();
        assertEquals(vizsla, it.next());
        assertEquals(chihuahua, it.next());
        assertEquals(collie, it.next());
        assertEquals(havanese, it.next());
        assertEquals(sheepdog, it.next());


        assertEquals(mastiff.getName(), "Mastiff");
        it = mastiff.getNeighbourIterartor();
        assertEquals(collie, it.next());
        assertEquals(pug, it.next());
        assertEquals(chihuahua, it.next());

        assertEquals(collie.getName(), "Collie");
        it = collie.getNeighbourIterartor();
        assertEquals(mastiff, it.next());
        assertEquals(chihuahua, it.next());
        assertEquals(maltese, it.next());
    }

    @Test
    void test_getTerritoriesIterator(){
        MapRO m1 = new V1Map18Territories(18);
        ArrayList<Iterator<Territory>> groupsIteratorList = m1.getInitialGroups();
        Iterator<Territory> iterator1 = groupsIteratorList.get(0);
        Iterator<Territory> iterator2 = groupsIteratorList.get(1);
        Iterator<Territory> iterator3 = groupsIteratorList.get(2);

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

        Territory vizsla = iterator3.next();
        Territory maltese = iterator3.next();
        Territory chihuahua = iterator3.next();
        Territory pug = iterator3.next();
        Territory mastiff = iterator3.next();
        Territory collie = iterator3.next();

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

        assertEquals(vizsla.getName(), "Vizsla");
        assertEquals(maltese.getName(), "Maltese");
        assertEquals(chihuahua.getName(), "Chihuahua");
        assertEquals(pug.getName(), "Pug");
        assertEquals(mastiff.getName(), "Mastiff");
        assertEquals(collie.getName(), "Collie");
    }

    @Test
    void test_replicate() {
        V1Map map = new V1Map18Territories(10);
        Iterator<Territory> it = map.getTerritoriesIterator();
        String firstName = it.next().getName();
        String secondName = it.next().getName();
        Territory firstT = map.getTerritoryByName(firstName);
        Territory secondT = map.getTerritoryByName(secondName);
        firstT.setUnitNum(15);
        secondT.setUnitNum(18);

        MapRO cloneMap = map.replicate();
        Territory cloneFirstT = cloneMap.getTerritoryByName(firstName);
        Territory cloneSecondT = cloneMap.getTerritoryByName(secondName);
        assertEquals(15, cloneFirstT.getUnitNum());
        assertEquals(18, cloneSecondT.getUnitNum());

        firstT.setUnitNum(0);
        assertEquals(15, cloneFirstT.getUnitNum());
    }

}