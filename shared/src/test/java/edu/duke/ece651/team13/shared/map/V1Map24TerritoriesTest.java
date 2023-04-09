package edu.duke.ece651.team13.shared.map;

import edu.duke.ece651.team13.shared.territory.Territory;
import edu.duke.ece651.team13.shared.territory.TerritoryRO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class V1Map24TerritoriesTest {

    @Test
    void initMap() {
        V1Map m1 = new V1Map24Territories(24);

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
        Territory pug = it1.next();
        Territory vizsla = it1.next();
        Territory chihuahua = it1.next();
        Territory maltese = it1.next();
        Territory mastiff = it1.next();
        Territory collie = it1.next();
        Territory dalmatian = it1.next();
        Territory papillon = it1.next();
        Territory setter = it1.next();
        Territory samoyed = it1.next();
        Territory bullmastiff = it1.next();
        Territory whippet = it1.next();

        assertEquals(rottweiler.getName(), "Rottweiler");
        Iterator<TerritoryRO> it = rottweiler.getNeighbourIterartor();
        assertEquals(dachshund, it.next());
        assertEquals(bulldog, it.next());
        assertEquals(labrador, it.next());
        assertEquals(akita, it.next());
        assertEquals(boxer, it.next());

        assertEquals(dachshund.getName(), "Dachshund");
        it = dachshund.getNeighbourIterartor();
        assertEquals(beagle, it.next());
        assertEquals(rottweiler, it.next());
        assertEquals(bulldog, it.next());

        assertEquals(beagle.getName(), "Beagle");
        it = beagle.getNeighbourIterartor();
        assertEquals(dachshund, it.next());
        assertEquals(bulldog, it.next());
        assertEquals(poodle, it.next());

        assertEquals(labrador.getName(), "Labrador");
        it = labrador.getNeighbourIterartor();
        assertEquals(rottweiler, it.next());
        assertEquals(bulldog, it.next());
        assertEquals(poodle, it.next());
        assertEquals(mastiff, it.next());
        assertEquals(boxer, it.next());
        assertEquals(collie, it.next());
        assertEquals(dalmatian, it.next());

        assertEquals(poodle.getName(), "Poodle");
        it = poodle.getNeighbourIterartor();
        assertEquals(beagle, it.next());
        assertEquals(bulldog, it.next());
        assertEquals(labrador, it.next());
        assertEquals(mastiff, it.next());

        assertEquals(bulldog.getName(), "Bulldog");
        it = bulldog.getNeighbourIterartor();
        assertEquals(dachshund, it.next());
        assertEquals(beagle, it.next());
        assertEquals(rottweiler, it.next());
        assertEquals(poodle, it.next());
        assertEquals(labrador, it.next());

        assertEquals(boxer.getName(), "Boxer");
        it = boxer.getNeighbourIterartor();
        assertEquals(akita, it.next());
        assertEquals(havanese, it.next());
        assertEquals(spaniel, it.next());
        assertEquals(rottweiler, it.next());
        assertEquals(labrador, it.next());
        assertEquals(dalmatian, it.next());
        assertEquals(collie, it.next());

        assertEquals(havanese.getName(), "Havanese");
        it = havanese.getNeighbourIterartor();
        assertEquals(brittany, it.next());
        assertEquals(sheepdog, it.next());
        assertEquals(akita, it.next());
        assertEquals(spaniel, it.next());
        assertEquals(boxer, it.next());

        assertEquals(spaniel.getName(), "Spaniel");
        it = spaniel.getNeighbourIterartor();
        assertEquals(sheepdog, it.next());
        assertEquals(havanese, it.next());
        assertEquals(boxer, it.next());
        assertEquals(dalmatian, it.next());
        assertEquals(papillon, it.next());

        assertEquals(sheepdog.getName(), "Sheepdog");
        it = sheepdog.getNeighbourIterartor();
        assertEquals(brittany, it.next());
        assertEquals(havanese, it.next());
        assertEquals(spaniel, it.next());

        assertEquals(akita.getName(), "Akita");
        it = akita.getNeighbourIterartor();
        assertEquals(brittany, it.next());
        assertEquals(havanese, it.next());
        assertEquals(boxer, it.next());
        assertEquals(rottweiler, it.next());

        assertEquals(brittany.getName(), "Brittany");
        it = brittany.getNeighbourIterartor();
        assertEquals(sheepdog, it.next());
        assertEquals(akita, it.next());
        assertEquals(havanese, it.next());

        assertEquals(pug.getName(), "Pug");
        it = pug.getNeighbourIterartor();
        assertEquals(maltese, it.next());
        assertEquals(mastiff, it.next());
        assertEquals(vizsla, it.next());

        assertEquals(vizsla.getName(), "Vizsla");
        it = vizsla.getNeighbourIterartor();
        assertEquals(pug, it.next());
        assertEquals(maltese, it.next());
        assertEquals(mastiff, it.next());
        assertEquals(chihuahua, it.next());
        assertEquals(collie, it.next());


        assertEquals(chihuahua.getName(), "Chihuahua");
        it = chihuahua.getNeighbourIterartor();
        assertEquals(maltese, it.next());
        assertEquals(vizsla, it.next());
        assertEquals(collie, it.next());
        assertEquals(samoyed, it.next());

        assertEquals(maltese.getName(), "Maltese");
        it = maltese.getNeighbourIterartor();
        assertEquals(pug, it.next());
        assertEquals(vizsla, it.next());
        assertEquals(chihuahua, it.next());


        assertEquals(mastiff.getName(), "Mastiff");
        it = mastiff.getNeighbourIterartor();
        assertEquals(pug, it.next());
        assertEquals(vizsla, it.next());
        assertEquals(collie, it.next());
        assertEquals(poodle, it.next());
        assertEquals(labrador, it.next());

        assertEquals(collie.getName(), "Collie");
        it = collie.getNeighbourIterartor();
        assertEquals(mastiff, it.next());
        assertEquals(vizsla, it.next());
        assertEquals(chihuahua, it.next());
        assertEquals(samoyed, it.next());
        assertEquals(labrador, it.next());
        assertEquals(dalmatian, it.next());
        assertEquals(boxer, it.next());

        assertEquals(dalmatian.getName(), "Dalmatian");
        it = dalmatian.getNeighbourIterartor();
        assertEquals(papillon, it.next());
        assertEquals(setter, it.next());
        assertEquals(samoyed, it.next());
        assertEquals(spaniel, it.next());
        assertEquals(collie, it.next());
        assertEquals(boxer, it.next());
        assertEquals(labrador, it.next());

        assertEquals(papillon.getName(), "Papillon");
        it = papillon.getNeighbourIterartor();
        assertEquals(bullmastiff, it.next());
        assertEquals(setter, it.next());
        assertEquals(dalmatian, it.next());

        assertEquals(setter.getName(), "Setter");
        it = setter.getNeighbourIterartor();
        assertEquals(bullmastiff, it.next());
        assertEquals(whippet, it.next());
        assertEquals(papillon, it.next());
        assertEquals(samoyed, it.next());
        assertEquals(dalmatian, it.next());

        assertEquals(samoyed.getName(), "Samoyed");
        it = samoyed.getNeighbourIterartor();
        assertEquals(whippet, it.next());
        assertEquals(setter, it.next());
        assertEquals(dalmatian, it.next());
        assertEquals(collie, it.next());
        assertEquals(chihuahua, it.next());

        assertEquals(bullmastiff.getName(), "Bullmastiff");
        it = bullmastiff.getNeighbourIterartor();
        assertEquals(whippet, it.next());
        assertEquals(papillon, it.next());
        assertEquals(setter, it.next());

        assertEquals(whippet.getName(), "Whippet");
        it = whippet.getNeighbourIterartor();
        assertEquals(bullmastiff, it.next());
        assertEquals(setter, it.next());
        assertEquals(samoyed, it.next());
    }

    @Test
    void test_getTerritoriesIterator(){
        MapRO m1 = new V1Map24Territories(24);
        ArrayList<Iterator<Territory>> groupsIteratorList = m1.getInitialGroups();
        Iterator<Territory> iterator1 = groupsIteratorList.get(0);
        Iterator<Territory> iterator2 = groupsIteratorList.get(1);
        Iterator<Territory> iterator3 = groupsIteratorList.get(2);
        Iterator<Territory> iterator4 = groupsIteratorList.get(3);

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

        Territory dalmatian = iterator4.next();
        Territory papillon = iterator4.next();
        Territory setter = iterator4.next();
        Territory samoyed = iterator4.next();
        Territory bullmastiff = iterator4.next();
        Territory whippet = iterator4.next();

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

        assertEquals(dalmatian.getName(), "Dalmatian");
        assertEquals(papillon.getName(), "Papillon");
        assertEquals(setter.getName(), "Setter");
        assertEquals(samoyed.getName(), "Samoyed");
        assertEquals(bullmastiff.getName(), "Bullmastiff");
        assertEquals(whippet.getName(), "Whippet");
    }
}