package main.java.org.simulation.service;


import main.java.org.simulation.Coordinate;
import main.java.org.simulation.MapBoard;
import main.java.org.simulation.entity.*;
import java.util.HashMap;
import java.util.Map;


public class EntityService {
    private final MapBoard mapBoard;
    private final Map<Coordinate, Grass> mapGrass = new HashMap<>();


    public EntityService(MapBoard mapBoard) {
        this.mapBoard = mapBoard;
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


    public boolean isValueGrassLowOnMapBoard() {
        return mapBoard.getValueGrass() < 2;
    }
}


