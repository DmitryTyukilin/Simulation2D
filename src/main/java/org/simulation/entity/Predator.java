package main.java.org.simulation.entity;
import main.java.org.simulation.Coordinate;
import main.java.org.simulation.WordMap;
import main.java.org.simulation.intarfaces.DamageDealer;
import main.java.org.simulation.intarfaces.Edible;

import java.util.ArrayList;
import java.util.List;


public abstract class Predator extends Creature implements DamageDealer {

    List<Coordinate> coordinateVisitGrass = new ArrayList<>();

    public abstract void attack(Edible herbivore);

    @Override
    public void makeMove(WordMap wordMap) {
        super.makeMove(wordMap);
        if (wordMap.getEntityByCoordinate(nextCoordinateToMove) instanceof Grass) {
            System.out.println("через траву");
            if (coordinateVisitGrass.contains(wordMap.getCoordinateCreature(this))) {
                Coordinate visitedCoordinate = coordinateVisitGrass.get(0);
                wordMap.moveEntityByNewCoordinate(visitedCoordinate, new Grass());
                wordMap.moveEntityByNewCoordinate(nextCoordinateToMove, this);
                coordinateVisitGrass.add(nextCoordinateToMove);
                coordinateVisitGrass.remove(visitedCoordinate);
            } else {
                coordinateVisitGrass.add(nextCoordinateToMove);
                wordMap.moveEntityByNewCoordinate(nextCoordinateToMove, this);
            }
        }
    }


}
