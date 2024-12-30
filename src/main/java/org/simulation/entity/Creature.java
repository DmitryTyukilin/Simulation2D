package main.java.org.simulation.entity;


import main.java.org.simulation.Coordinate;
import main.java.org.simulation.WordMap;
import main.java.org.simulation.service.Navigator;

import java.util.Objects;


public abstract class Creature extends Entity {

    private Coordinate grass;


    public Creature() {
    }

    public void makeMove(WordMap wordMap) {
        Navigator navigator = new Navigator(wordMap);
        Coordinate nextCoordinate = navigator.getNextCoordinateEntity(this);
        if (isEdibleByCoordinate(nextCoordinate, wordMap)) {
            this.eat(nextCoordinate, wordMap);
        } else if (wordMap.isAvailableToMove(nextCoordinate)) {
            moveEntityByNewCoordinate(nextCoordinate, wordMap);
        } else if (isEntityGrass(nextCoordinate, wordMap)) {
            moveOnGrass(nextCoordinate, wordMap);
        }
    }


    private void moveOnGrass(Coordinate nextCoordinateGrass, WordMap wordMap) {
        if (grass == null) {
            grass = nextCoordinateGrass;
            wordMap.deleteEntity(this);
            wordMap.addEntityMap(nextCoordinateGrass, this);
        } else {
            wordMap.deleteEntity(this);
            wordMap.addEntityMap(grass, new Grass());
            grass = nextCoordinateGrass;
            wordMap.addEntityMap(nextCoordinateGrass, this);
        }
    }

    public void moveEntityByNewCoordinate(Coordinate newCoordinate, WordMap wordMap) {
        if (grass != null) {
            wordMap.deleteEntity(this);
            wordMap.addEntityMap(grass, new Grass());
            grass = null;
            wordMap.addEntityMap(newCoordinate, this);
        } else {
            wordMap.deleteEntity(this);
            wordMap.addEntityMap(newCoordinate, this);
        }
    }


    private boolean isEdibleByCoordinate(Coordinate coordinate, WordMap wordMap) {
        Entity entity = wordMap.getEntityByCoordinate(coordinate);
        if (this instanceof Herbivore && entity instanceof Grass) {
            return true;
        } else if (this instanceof Predator && entity instanceof Herbivore) {
            return true;
        } else return false;
    }

    private boolean isEntityGrass(Coordinate coordinate, WordMap wordMap) {
        Entity entity = wordMap.getEntityByCoordinate(coordinate);
        return entity instanceof Grass;
    }


    protected abstract void eat(Coordinate coordinateEat, WordMap wordMap);

    @Override
    public boolean equals(Object o) {
        return this == o;

    }

    @Override
    public int hashCode() {
        return Objects.hash(this);
    }
}

