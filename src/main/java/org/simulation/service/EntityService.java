package main.java.org.simulation.service;


import main.java.org.simulation.Coordinate;
import main.java.org.simulation.WordMap;
import main.java.org.simulation.entity.*;

import java.util.HashMap;
import java.util.Map;


public class EntityService {
    private final WordMap wordMap;
    private final Map<Coordinate, Grass> mapGrass = new HashMap<>();
    private int grassCounter = 5;


    public EntityService(WordMap wordMap) {
        this.wordMap = wordMap;
    }

    public Grass getGrassMapGrass(Coordinate coordinate) {
        return mapGrass.get(coordinate);
    }

    public void saveGrassEntry(Coordinate coordinateGrass) {
        Grass grass = wordMap.getGrass(coordinateGrass);
        mapGrass.put(coordinateGrass, grass);
    }

    public boolean hasGrassMapGrass(Coordinate coordinate) {
        return mapGrass.containsKey(coordinate);
    }


    public boolean isValueGrassLowOnMapBoard() {
        grassCounter--;
        return grassCounter > 0 && wordMap.getValueGrass() < 2;
    }
}


