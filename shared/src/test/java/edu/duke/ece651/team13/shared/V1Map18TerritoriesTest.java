package edu.duke.ece651.team13.shared;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class V1Map18TerritoriesTest {

    @Test
    void initMap() {
        Map m1 = new V1Map18Territories(18);

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
        Territory vizsla = it.next();
        Territory pug = it.next();
        Territory chihuahua = it.next();
        Territory maltese = it.next();
        Territory mastiff = it.next();
        Territory collie = it.next();

        assertEquals(rottweiler.getName(), "Rottweiler");
        it = rottweiler.getNeighbourIterartor();
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
}