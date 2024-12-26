package main.java.org.simulation.entity;

import main.java.org.simulation.Coordinate;
import main.java.org.simulation.WordMap;
import main.java.org.simulation.intarfaces.Edible;
import main.java.org.simulation.intarfaces.GrassWalker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class Wolf extends Predator {



    private final static int DAMAGE = 3;


    @Override
    public void attack(Edible edible) {

        edible.takeDamage(DAMAGE);
    }


    @Override
    protected void eat(Coordinate coordinateEat, WordMap wordMap) {
        Entity entity = wordMap.getEntityByCoordinate(coordinateEat);
        if (entity instanceof Herbivore) {
            attack((Herbivore) entity);
        }
    }




//    private void goGrass(Creature creature) {
//        Coordinate nextCoordinate = navigator.getNextCoordinateEntity(creature);
//        Coordinate currentCoordinate = wordMap.getCoordinateCreature(creature);
//        if (grassCreater.hasGrassMapGrass(currentCoordinate)) {
//            Grass grass = grassCreater.getGrassMapGrass(currentCoordinate);
//            wordMap.addEntityMap(currentCoordinate, grass);
//            wordMap.addEntityMap(nextCoordinate, creature);
//            return;
//        }
//        grassCreater.saveGrassEntry(nextCoordinate);
//        wordMap.addEntityMap(nextCoordinate, creature);
//        wordMap.addEntityMap(currentCoordinate, null);
//    }
}




