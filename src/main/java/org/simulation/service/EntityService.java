package main.java.org.simulation.service;


import main.java.org.simulation.Coordinate;
import main.java.org.simulation.MapBoard;
import main.java.org.simulation.entity.Creature;
import main.java.org.simulation.entity.Entity;
import main.java.org.simulation.entity.Grass;
import main.java.org.simulation.entity.Hare;
import main.java.org.simulation.utils.RandomIntValue;

import java.util.HashMap;
import java.util.Map;


public class EntityService {
    private final MapBoard mapBoard;
    private final Map<Coordinate, Grass> mapGrass = new HashMap<>();


    public EntityService(MapBoard mapBoard) {
        this.mapBoard = mapBoard;
    }


    public Coordinate getCoordinateCreature(Creature creature) {
        return mapBoard.getCoordinateCreature(creature);
    }

    public Grass getGrassMapGrass(Coordinate coordinate) {
        return mapGrass.get(coordinate);
    }

    public void saveGrassEntry(Coordinate coordinateGrass) {
        Grass grass = mapBoard.getGrass(coordinateGrass);
        mapGrass.put(coordinateGrass, grass);
    }

    public boolean hasGrassMapGrass(Coordinate coordinate) {
        return mapGrass.containsKey(coordinate);
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

}
