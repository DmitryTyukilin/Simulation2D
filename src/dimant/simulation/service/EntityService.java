package dimant.simulation.service;

import dimant.simulation.*;
import dimant.simulation.entity.*;
import dimant.simulation.utils.RandomIntValue;

import java.util.*;


public class EntityService {
    private MapBoard mapBoard;
    private Map<Coordinate, Grass> mapGrass = new HashMap<>();


    public EntityService(MapBoard mapBoard) {
        this.mapBoard = mapBoard;
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

    public Coordinate getNextCoordinate(Coordinate currentCoordinate, Creature targetEat) {
        SearchRoute searchRoute = new SearchRoute(mapBoard);
        return searchRoute.getNextCoordinate(currentCoordinate, targetEat);

    }

    public void saveGrassEntry(Coordinate coordinateGrass) {
        Grass grass = mapBoard.getGrass(coordinateGrass);
        mapGrass.put(coordinateGrass, grass);
    }

    public void deleteEntityMap(Entity entity) {
        mapBoard.deleteEntity(entity);
    }

    public void addGrassMapBoard() {
        int valueGrass = RandomIntValue.randomIndex(4);
            while(valueGrass > 0){
                Coordinate coordinate = mapBoard.getFreeCoordinate();
                mapBoard.addEntityMap(coordinate, new Grass());
                valueGrass--;
            }
        }


    public boolean isValueGrassLowOnMapBoard() {
        int counter = 0;
        for (Entity entity : mapBoard.getEntityList()) {
            if (entity instanceof Grass) {
                counter++;
            }
        }
        return counter <= 2;
    }

    public boolean hasHareOnMapBoard() {
        int counter = 0;
        for (Entity entity : mapBoard.getEntityList()) {
            if (entity instanceof Hare) {
                counter++;
            }
        }
        return counter > 0;
    }


    public Grass getGrassMapGrass(Coordinate coordinate) {
        return mapGrass.get(coordinate);
    }

    public boolean hasGrassByCoordinateInMapGrass(Coordinate coordinate) {
        return mapGrass.containsKey(coordinate);
    }

    public void recoverGrassByCoordinate(Coordinate coordinate) {
        mapBoard.addEntityMap(coordinate, mapGrass.get(coordinate));
    }
}
