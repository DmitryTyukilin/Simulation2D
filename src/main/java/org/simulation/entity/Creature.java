package main.java.org.simulation.entity;


import main.java.org.simulation.Coordinate;
import main.java.org.simulation.WordMap;
import main.java.org.simulation.service.Navigator;

import java.util.Objects;


public abstract class Creature extends Entity {
     protected Coordinate nextCoordinateToMove;
     private Coordinate currentCoordinate;


    public Creature() {
    }

    public void makeMove(WordMap wordMap) {
        Navigator navigator = new Navigator(wordMap);
        Coordinate nextCoordinate = navigator.getNextCoordinateEntity(this);
        nextCoordinateToMove = nextCoordinate;
        if (isEdibleByCoordinate(nextCoordinate, wordMap)) {
            this.eat(nextCoordinate, wordMap);
        } else if (wordMap.isAvailableToMove(nextCoordinate)) {
            wordMap.moveEntityByNewCoordinate(nextCoordinate, this);
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


    @Override
    public boolean equals(Object o) {
        return this == o;

    }

    @Override
    public int hashCode() {
        return Objects.hash(this);
    }

    protected abstract void eat(Coordinate coordinateEat, WordMap wordMap);

}

