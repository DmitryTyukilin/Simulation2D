package main.java.org.simulation.actions.turn;

import main.java.org.simulation.Coordinate;
import main.java.org.simulation.WordMap;
import main.java.org.simulation.actions.Action;
import main.java.org.simulation.actions.init.CreatorEntity;
import main.java.org.simulation.entity.Entity;
import main.java.org.simulation.entity.Grass;
import main.java.org.simulation.entity.Hare;
import main.java.org.simulation.entity.Herbivore;
import main.java.org.simulation.utils.RandomIntValue;


public class ResourcesCreater extends Action {

    CreatorEntity creatorEntity;

    public ResourcesCreater(WordMap wordMap, CreatorEntity creatorEntity) {
        super(wordMap);
        this.creatorEntity = creatorEntity;

    }

    @Override
    public void execute() {
        if (isGrassLowOnMapBoard()) {
            addGrassMapBoard();
        }
        if (isHerbivoreLowOnMapBoard()) {
            addHerbivoreMapBoard();
        }
    }

    private boolean isGrassLowOnMapBoard() {
        int grass = valueGrass(wordMap);
        return grass <= 2;
    }

    private boolean isHerbivoreLowOnMapBoard() {
        int herb = valueHerbivore(wordMap);
        return herb <= 2;
    }



    private void addGrassMapBoard() {
        int valueGrass = RandomIntValue.randomIndex(2, 4);
        for (int i = 0; i < valueGrass; i++) {
            Coordinate coordinate = wordMap.getFreeCoordinate();
            Grass grass = new Grass();
            wordMap.addEntityMap(coordinate, grass);
        }
    }

    private void addHerbivoreMapBoard() {
        int valueHerbivore = RandomIntValue.randomIndex(2, 4);
        int HP = RandomIntValue.randomIndex(6, 15);
        for (int i = 0; i < valueHerbivore; i++) {
            Coordinate coordinate = wordMap.getFreeCoordinate();
            Herbivore herbivore = new Hare(HP);
            wordMap.addEntityMap(coordinate, herbivore);
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

    private int valueHerbivore(WordMap wordMap) {
        int result = 0;
        for (Entity entity : wordMap.getEntityList()) {
            if (entity instanceof Herbivore) {
                result++;
            }
        }
        return result;
    }
}
