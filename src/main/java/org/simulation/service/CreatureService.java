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
            int index = RandomIntValue.randomIndex(creatures.size() - 1);
            Creature creature = creatures.get(index);
            if (mapBoard.hasEntityOnMapBoard(creature)) {
                String typeNextMove = navigator.getTypeEntityNextStep(creature);
                creature.makeMove(typeNextMove);
                triggerActiveCreature(creature, typeNextMove);
            }
            creatures.remove(creature);
        }
    }

    public void triggerActiveCreature(Creature creature, String typeNextMove) {
        EnumReaction reaction = creature.makeMove(typeNextMove);
        switch (reaction) {
            case GO -> moveCreature(creature);
            case ATTACK -> attackHerbivore((Wolf) creature); // TODO: 25.11.2024 костыль
            case EAT -> eatGrass();
            case GO_GRASS -> goGrass(creature);
            default -> System.out.print("");
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
        mapBoard.addEntityMap(currentCoordinate, new Place());
    }


    private void attackHerbivore(Predator predator) {
        Coordinate nextCoordinate = navigator.getNextCoordinateEntity();
        Hare hare = mapBoard.getHare(nextCoordinate);
        if (hare != null) {
            predator.attack(hare);
            if (isHPHareLow(hare)) {
                entityService.deleteEntityMap(hare);
            }
        }
    }

    public void eatGrass() {
        Coordinate nextCoordinate = navigator.getNextCoordinateEntity();
        Grass grass = mapBoard.getGrass(nextCoordinate);
        entityService.deleteEntityMap(grass);
        if (entityService.isValueGrassLowOnMapBoard()) {
            entityService.addGrassMapBoard();
        }
    }

    public void goGrass(Creature creature) {
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
        mapBoard.addEntityMap(currentCoordinate, new Place());
    }

    private boolean isHPHareLow(Hare hare) {
        return hare.getHP() <= 0;
    }
}
