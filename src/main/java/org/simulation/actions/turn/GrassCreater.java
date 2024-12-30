package main.java.org.simulation.actions.turn;

import main.java.org.simulation.Coordinate;
import main.java.org.simulation.WordMap;
import main.java.org.simulation.actions.Action;
import main.java.org.simulation.entity.Entity;
import main.java.org.simulation.entity.Grass;
import main.java.org.simulation.intarfaces.Edible;
import main.java.org.simulation.utils.RandomIntValue;


public class GrassCreater extends Action {

    public GrassCreater(WordMap wordMap) {
        super(wordMap);

    }

    @Override
    public void execute() {
        if (isValueGrassLowOnMapBoard()) {
            addGrassMapBoard();
        }
    }

    private boolean isValueGrassLowOnMapBoard() {
        int grass = valueGrass(wordMap);
        return grass <= 2;
    }

    private void addGrassMapBoard() {
        int valueGrass = RandomIntValue.randomIndex(2, 4);
        for (int i = 0; i < valueGrass; i++) {
            Coordinate coordinate = wordMap.getFreeCoordinate();
            Grass grass = new Grass();
            wordMap.addEntityMap(coordinate, grass);
        }
    }

    private int valueGrass(WordMap wordMap) {
        int result = 0;
        for (Entity entity : wordMap.getEntityList()) {
            if (entity instanceof Grass) {
                result++;
            }
        }
        return result;
    }
}
