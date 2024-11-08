package dimant.simulation.service;

import dimant.simulation.Coordinate;
import dimant.simulation.MapBoard;
import dimant.simulation.entity.Creature;
import dimant.simulation.entity.Grass;
import dimant.simulation.entity.Hare;
import dimant.simulation.entity.Place;
import dimant.simulation.enums.EnumReaction;
import dimant.simulation.utils.RandomIntValue;

import java.util.List;

public class CreatureService {
    private MapBoard mapBoard;
    private Navigator navigator;
    private EntityService entityService;

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
                creatures.remove(index);
            } else {
                creatures.remove(index);
            }
        }
    }

    public void triggerActiveCreature(Creature creature, String typeNextMove) {
        EnumReaction reaction = creature.makeMove(typeNextMove);
        System.out.println("сходил " + creature.toString());
        switch (reaction) {
            case GO -> moveCreature(creature);
            case ATTACK -> attackHerbivore(creature); // для wolf когда makeMove вернул Hare
            case EAT -> eatGrass(); // для hare когда makeMove вернул Grass
            case GO_GRASS -> goGrass(creature);
            default -> System.out.println("Действие не определено" + creature.toString()); // xз чё тут сделать, может остаться на месте
        }
        ;
    }

//    public void moveCreature(Creature creature) {
//        Coordinate currentCoordinate = mapBoard.getCreatureCoordinate(creature);
//        Coordinate nextCoordinate = navigator.getNextCoordinateCreature();
//        if (entityService.hasGrassByCoordinateInMapGrass(currentCoordinate)) {
//            Grass grass = entityService.getGrassMapGrass(currentCoordinate);
//            mapBoard.addEntityMap(currentCoordinate, grass);
//            mapBoard.addEntityMap(nextCoordinate, creature);
//            return;
//        }
//        mapBoard.addEntityMap(nextCoordinate, creature);
//        mapBoard.addEntityMap(currentCoordinate, new Place());
//    }

    public void moveCreature(Creature creature) {
        Coordinate currentCoordinate = mapBoard.getCreatureCoordinate(creature);
        Coordinate nextCoordinate = navigator.getNextCoordinateCreature();
        mapBoard.addEntityMap(nextCoordinate, creature);
        mapBoard.addEntityMap(currentCoordinate, new Place());
    }


    public void attackHerbivore(Creature creature) {
        Coordinate nextCoordinate = navigator.getNextCoordinateCreature();
        Hare hare = mapBoard.getHare(nextCoordinate);
        creature.attack(hare);
        if (isHPHareLow(hare)) {
            entityService.deleteEntityMap(hare);
        }
    }

    // TODO: 06.11.2024 Не совсем корректно сюда добавлять проверку на уровень hp и удаления hare с карты. Скорее всего после каждого хода entityService должен производить свою работу по удалению и добавлению сущностей


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
