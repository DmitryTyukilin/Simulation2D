package main.java.org.simulation.service;


import main.java.org.simulation.Coordinate;
import main.java.org.simulation.MapBoard;
import main.java.org.simulation.entity.Creature;
import main.java.org.simulation.entity.Entity;

public class Navigator {
    private final SearchRoute searchRoute;
    private final MapBoard mapBoard;
    private final EntityService entityService;
    private Coordinate nextCoordinateEntity;

    public Navigator(SearchRoute searchRoute, MapBoard mapBoard, EntityService entityService) {
        this.searchRoute = searchRoute;
        this.mapBoard = mapBoard;
        this.entityService = entityService;
    }

    String getTypeEntityNextStep(Creature creature) {
        Coordinate coordinateNextStep = nextCoordinateAboutCurrent(creature);
        Entity entityNextStep = mapBoard.getEntityByCoordinate(coordinateNextStep);
        if (entityNextStep != null) {
            nextCoordinateEntity = coordinateNextStep;
            return entityNextStep.getClass().getSimpleName();
        } else return "null";
    }

    public Coordinate getNextCoordinateEntity() {
        return nextCoordinateEntity;
    }

    private Coordinate nextCoordinateAboutCurrent(Creature creature) {
        Coordinate currentCreatureCoordinate = entityService.getCoordinateCreature(creature);
        return searchRoute.getNextCoordinate(currentCreatureCoordinate, creature);
    }
}
