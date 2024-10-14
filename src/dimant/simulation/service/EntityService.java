package dimant.simulation.service;

import dimant.simulation.*;
import dimant.simulation.entity.Creature;
import dimant.simulation.entity.Entity;
import dimant.simulation.entity.Place;
import dimant.simulation.entity.Wolf;

import java.util.Map;


public class EntityService {
    MapBoard mapBoard;

    public EntityService(MapBoard mapBoard) {
        this.mapBoard = mapBoard;
    }


    public void makeMoveWolf() {
        Coordinate currentCoordinateWolf = mapBoard.getCoordinateWolf();
        Coordinate newCoordinate = getNextCoordinate(currentCoordinateWolf);
        if (mapBoard.isLocatedHare(newCoordinate)) {
            Wolf wolf = mapBoard.getWolf(currentCoordinateWolf);
            wolf.attack(mapBoard.getHare(newCoordinate));
        } else {
            Wolf wolf = mapBoard.getWolf(currentCoordinateWolf);
            vacatePlace(currentCoordinateWolf);
            mapBoard.addEntityMap(newCoordinate, wolf);
        }
    }

    public Coordinate getCoordinateCreature(Creature creature) {
        for (Map.Entry<Coordinate, Entity> entry : mapBoard.getEntityMap().entrySet()) {
            String value = entry.getValue().getClass().getName();
            if (entry.getValue().equals(creature)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public Coordinate getNextCoordinate(Coordinate currentCoordinate) {
        SearchRoute searchRoute = new SearchRoute(mapBoard);
        return searchRoute.getNextCoordinate(currentCoordinate);

    }


    public void vacatePlace(Coordinate currentCoordinate) {
        mapBoard.addEntityMap(currentCoordinate, new Place());
    }
}
