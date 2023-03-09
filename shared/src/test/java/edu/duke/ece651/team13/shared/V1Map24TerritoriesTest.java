package edu.duke.ece651.team13.shared;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class V1Map24TerritoriesTest {

    @Test
    void initMap() {
        Map m1 = new V1Map24Territories(24);

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
        Territory pug = it.next();
        Territory vizsla = it.next();
        Territory chihuahua = it.next();
        Territory maltese = it.next();
        Territory mastiff = it.next();
        Territory collie = it.next();
        Territory dalmatian = it.next();
        Territory papillon = it.next();
        Territory setter = it.next();
        Territory samoyed = it.next();
        Territory bullmastiff = it.next();
        Territory whippet = it.next();

        assertEquals(rottweiler.getName(), "Rottweiler");
        it = rottweiler.getNeighbourIterartor();
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
}