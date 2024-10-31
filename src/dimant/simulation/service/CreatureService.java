package dimant.simulation.service;

import dimant.simulation.Coordinate;
import dimant.simulation.MapBoard;
import dimant.simulation.entity.Creature;
import dimant.simulation.entity.Grass;
import dimant.simulation.entity.Hare;
import dimant.simulation.entity.Place;
import dimant.simulation.enums.EnumReaction;

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
        for(Creature creature : mapBoard.getCreature()) {
            String typeNextMove = navigator.getTypeNextStep(creature);
            triggerActiveCreature(creature,typeNextMove);

        }
    }
    public void triggerActiveCreature(Creature creature, String typeNextMove ) {
        EnumReaction reaction = creature.makeMove(typeNextMove);
       switch(reaction) {
           case GO -> moveCreature(creature);
           case ATTACK -> attackHerbivore(creature); // для wolf когда makeMove вернул Hare
           case EAT -> eatGrass(creature); // для hare когда makeMove вернул Grass
           case GO_GRASS -> goGrass(creature);


           default -> System.out.println("Действие не определено" + creature.toString()); // xз чё тут сделать, может остаться на месте
       };
    }

    public void moveCreature(Creature creature) {
        Coordinate currentCoordinate = mapBoard.getCreatureCoordinate(creature);
        Coordinate nextCoordinate = navigator.getNextCoordinateCreature();
        if(entityService.hasGrassByCoordinateInMapGrass(currentCoordinate)) {
            Grass grass = entityService.getGrassMapGrass(currentCoordinate);
            mapBoard.addEntityMap(currentCoordinate,grass);
            mapBoard.addEntityMap(nextCoordinate,creature);
            return;
        }
        mapBoard.addEntityMap(nextCoordinate, creature);
        mapBoard.addEntityMap(currentCoordinate, new Place());
    }

    public void attackHerbivore(Creature creature) {
        Coordinate nextCoordinate = navigator.getNextCoordinateCreature();
        Hare hare = mapBoard.getHare(nextCoordinate);
        creature.attack(hare);
    }

    public void eatGrass(Creature creature) {
        Coordinate nextCoordinate = navigator.getNextCoordinateCreature();
        Grass grass = mapBoard.getGrass(nextCoordinate);
        creature.attack(grass);
    }

    public void goGrass(Creature creature) {
        Coordinate nextCoordinate = navigator.getNextCoordinateCreature();
        Coordinate currentCoordinate = mapBoard.getCreatureCoordinate(creature);
        entityService.saveGrassEntry(nextCoordinate);
        mapBoard.addEntityMap(nextCoordinate, creature);
        mapBoard.addEntityMap(currentCoordinate, new Place());

    }









}
