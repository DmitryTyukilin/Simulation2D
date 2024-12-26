package main.java.org.simulation.actions.init;

import main.java.org.simulation.Coordinate;
import main.java.org.simulation.WordMap;
import main.java.org.simulation.actions.Action;
import main.java.org.simulation.entity.Grass;

import java.util.HashMap;
import java.util.Map;

public class GrassCreater extends Action {
    private final Map<Coordinate, Grass> mapGrass = new HashMap<>();
    private int grassCounter = 5;

    public GrassCreater(WordMap wordMap) {
        super(wordMap);
    }

    @Override
    public void execute() {
        if (isValueGrassLowOnMapBoard()) {
            wordMap.addGrassMapBoard();
        }

    }
    public boolean hasGrassMapGrass(Coordinate coordinate) {
        return mapGrass.containsKey(coordinate);
    }

    public void saveGrassEntry(Coordinate coordinateGrass) {
        Grass grass = wordMap.getGrass(coordinateGrass);
        mapGrass.put(coordinateGrass, grass);
    }
    public Grass getGrassMapGrass(Coordinate coordinate) {
        return mapGrass.get(coordinate);
    }
    private boolean isValueGrassLowOnMapBoard() {
        grassCounter--;
        return grassCounter > 0 && wordMap.getValueGrass() < 2;
    }

}
