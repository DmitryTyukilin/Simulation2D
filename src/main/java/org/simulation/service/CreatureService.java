package main.java.org.simulation.service;


import main.java.org.simulation.Coordinate;
import main.java.org.simulation.MapBoard;
import main.java.org.simulation.entity.*;
import main.java.org.simulation.enums.EnumReaction;
import main.java.org.simulation.utils.RandomIntValue;

import java.util.List;

public class CreatureService {
    private final MapBoard mapBoard;
    private final Navigator navigator;
    private final EntityService entityService;


    public CreatureService(MapBoard mapBoard, Navigator navigator, EntityService entityService) {
        this.mapBoard = mapBoard;
        this.navigator = navigator;
        this.entityService = entityService;

    }

    public void makeMoveAllCreature() {
        List<Creature> creatures = mapBoard.getCreature();
        while (creatures.size() > 0) {
            int index = RandomIntValue.randomIndex(0, creatures.size() - 1);
            Creature creature = creatures.get(index);
                String typeNextMove = navigator.getTypeEntityNextStep(creature);
                triggerActiveCreature(creature, typeNextMove);
                creatures.remove(creature);

        }
    }

    private void triggerActiveCreature(Creature creature, String typeNextMove) {
        EnumReaction reaction = creature.makeMove(typeNextMove);
        if (creature instanceof Predator) {
            Predator predator = (Predator) creature;
            if (reaction.equals(EnumReaction.ATTACK)) {
                attackHerbivore(predator);
            } else if (reaction.equals(EnumReaction.GO_GRASS)) {
                goGrass(predator);
            } else moveCreature(creature);
        }
        if (creature instanceof Herbivore) {
            if (reaction.equals(EnumReaction.EAT)) {
                eatGrass();
            } else moveCreature(creature);
        }
    }


    private void moveCreature(Creature creature) {
        Coordinate currentCoordinate = mapBoard.getCoordinateCreature(creature);
        Coordinate nextCoordinate = navigator.getNextCoordinateEntity();
        if (entityService.hasGrassMapGrass(currentCoordinate)) {
            Grass grass = entityService.getGrassMapGrass(currentCoordinate);
            mapBoard.addEntityMap(currentCoordinate, grass);
            mapBoard.addEntityMap(nextCoordinate, creature);
            return;
        }
        mapBoard.addEntityMap(nextCoordinate, creature);
        mapBoard.addEntityMap(currentCoordinate, null);
    }


    private void attackHerbivore(Predator predator) {
        Coordinate nextCoordinate = navigator.getNextCoordinateEntity();
        Hare hare = mapBoard.getHare(nextCoordinate);
        if (hare != null) {
            predator.attack(hare);
            if (isHerbivoreDead(hare)) {
                mapBoard.deleteEntityMap(hare);
            }
        }
    }

    private void eatGrass() {
        Coordinate nextCoordinate = navigator.getNextCoordinateEntity();
        Grass grass = mapBoard.getGrass(nextCoordinate);
        mapBoard.deleteEntityMap(grass);
        if (entityService.isValueGrassLowOnMapBoard()) {
            mapBoard.addGrassMapBoard();
        }
    }

    private void goGrass(Creature creature) {
        Coordinate nextCoordinate = navigator.getNextCoordinateEntity();
        Coordinate currentCoordinate = mapBoard.getCoordinateCreature(creature);
        if (entityService.hasGrassMapGrass(currentCoordinate)) {
            Grass grass = entityService.getGrassMapGrass(currentCoordinate);
            mapBoard.addEntityMap(currentCoordinate, grass);
            mapBoard.addEntityMap(nextCoordinate, creature);
            return;
        }
        entityService.saveGrassEntry(nextCoordinate);
        mapBoard.addEntityMap(nextCoordinate, creature);
        mapBoard.addEntityMap(currentCoordinate, null);
    }

    private boolean isHerbivoreDead(Herbivore hare) {
        return hare.getHP() <= 0;
    }
}
