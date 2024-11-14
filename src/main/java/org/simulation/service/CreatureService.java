package main.java.org.simulation.service;



import main.java.org.simulation.Coordinate;
import main.java.org.simulation.MapBoard;
import main.java.org.simulation.entity.Creature;
import main.java.org.simulation.entity.Grass;
import main.java.org.simulation.entity.Hare;
import main.java.org.simulation.entity.Place;
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
                String typeNextMove = navigator.getTypeNextStep(creature);
                triggerActiveCreature(creature, typeNextMove);
            }
            creatures.remove(index);
        }
    }

    public void triggerActiveCreature(Creature creature, String typeNextMove) {
        EnumReaction reaction = creature.makeMove(typeNextMove);
        switch (reaction) {
            case GO -> moveCreature(creature);
            case ATTACK -> attackHerbivore(creature);
            case EAT -> eatGrass();
            case GO_GRASS -> goGrass(creature);
            default -> System.out.print("");
        }
    }


    public void moveCreature(Creature creature) {
        Coordinate currentCoordinate = mapBoard.getCreatureCoordinate(creature);
        Coordinate nextCoordinate = navigator.getNextCoordinateCreature();
        mapBoard.addEntityMap(nextCoordinate, creature);
        mapBoard.addEntityMap(currentCoordinate, new Place());
    }


    public void attackHerbivore(Creature creature) {
        Coordinate nextCoordinate = navigator.getNextCoordinateCreature();
        Hare hare = mapBoard.getHare(nextCoordinate);
        if (hare != null) {
            creature.attack(hare);
            if (isHPHareLow(hare)) {
                entityService.deleteEntityMap(hare);
            }
        }
    }

    public void eatGrass() {
        Coordinate nextCoordinate = navigator.getNextCoordinateCreature();
        Grass grass = mapBoard.getGrass(nextCoordinate);
        entityService.deleteEntityMap(grass);
        if (entityService.isValueGrassLowOnMapBoard()) {
            entityService.addGrassMapBoard();
        }
    }

    public void goGrass(Creature creature) {
        Coordinate nextCoordinate = navigator.getNextCoordinateCreature();
        Coordinate currentCoordinate = mapBoard.getCreatureCoordinate(creature);
        entityService.saveGrassEntry(nextCoordinate);
        mapBoard.addEntityMap(nextCoordinate, creature);
        mapBoard.addEntityMap(currentCoordinate, new Place());
    }

    private boolean isHPHareLow(Hare hare) {
        return hare.getHP() <= 0;
    }
}
