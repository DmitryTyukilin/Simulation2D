package main.java.org.simulation.service;


import main.java.org.simulation.Coordinate;
import main.java.org.simulation.WordMap;
import main.java.org.simulation.actions.init.GrassCreater;
import main.java.org.simulation.entity.*;

public class CreatureService {
    private final WordMap wordMap;
    private final Navigator navigator;
    private final EntityService entityService;
    private final GrassCreater grassCreater;


    public CreatureService(WordMap wordMap, Navigator navigator, EntityService entityService, GrassCreater grassCreater) {
        this.wordMap = wordMap;
        this.navigator = navigator;
        this.entityService = entityService;
        this.grassCreater = grassCreater;
    }

    public void makeMoveAllCreature() {
//        List<Creature> creatures = wordMap.getCreature();
//        for (int i = creatures.size() - 1; i >= 0; i--) {
//            Creature creature = creatures.get(i);
//
//            triggerActiveCreature(creature, typeNextMove);
//            if (creature.getClass().getName().equals(Hare.class.getName()) && isHerbivoreDead((Herbivore) creature)) {
//                wordMap.deleteEntityMap(creature);
//            }
//            creatures.remove(creature);
//        }
    }

//    private void triggerActiveCreature(Creature creature, String typeNextMove) {
//        EnumReaction reaction = creature.makeMove(typeNextMove);
//        if (creature instanceof Predator) {
//            Predator predator = (Predator) creature;
//            if (reaction.equals(EnumReaction.ATTACK)) {
//                attackHerbivore(predator);
//            } else if (reaction.equals(EnumReaction.GO_GRASS)) {
//                goGrass(predator);
//            } else moveCreature(creature);
//        }
//        if (creature instanceof Herbivore) {
//            if (reaction.equals(EnumReaction.EAT)) {
//                eatGrass();
//            } else if (reaction.equals(EnumReaction.STOP)) {
//                stayPut();
//            } else {
//                moveCreature(creature);
//            }
//        }
//    }


    private void moveCreature(Creature creature) {
        Coordinate currentCoordinate = wordMap.getCoordinateCreature(creature);
        Coordinate nextCoordinate = navigator.getNextCoordinateEntity(creature);
        if (grassCreater.hasGrassMapGrass(currentCoordinate)) {
            Grass grass = grassCreater.getGrassMapGrass(currentCoordinate);
            wordMap.addEntityMap(currentCoordinate, grass);
            wordMap.addEntityMap(nextCoordinate, creature);
            return;
        }
        wordMap.addEntityMap(nextCoordinate, creature);
        wordMap.addEntityMap(currentCoordinate, null);
    }


    private void attackHerbivore(Predator predator) {
        Coordinate nextCoordinate = navigator.getNextCoordinateEntity(predator);
        Hare hare = wordMap.getHare(nextCoordinate);
        if (hare != null) {
            predator.attack(hare);
            if (isHerbivoreDead(hare)) {
                wordMap.deleteEntityMap(hare);
            }
        }
    }

//    private void eatGrass() {
//        Coordinate nextCoordinate = navigator.getNextCoordinateEntity();
//        Grass grass = wordMap.getGrass(nextCoordinate);
//        wordMap.deleteEntityMap(grass);
//        if (entityService.isValueGrassLowOnMapBoard()) {
//            wordMap.addGrassMapBoard();
//        }
//    }

    private void stayPut() {
    }


    private void goGrass(Creature creature) {
        Coordinate nextCoordinate = navigator.getNextCoordinateEntity(creature);
        Coordinate currentCoordinate = wordMap.getCoordinateCreature(creature);
        if (grassCreater.hasGrassMapGrass(currentCoordinate)) {
            Grass grass = grassCreater.getGrassMapGrass(currentCoordinate);
            wordMap.addEntityMap(currentCoordinate, grass);
            wordMap.addEntityMap(nextCoordinate, creature);
            return;
        }
        grassCreater.saveGrassEntry(nextCoordinate);
        wordMap.addEntityMap(nextCoordinate, creature);
        wordMap.addEntityMap(currentCoordinate, null);
    }

    private boolean isHerbivoreDead(Herbivore hare) {
        return hare.getHP() <= 0;
    }
}
