package main.java.org.simulation.intarfaces;

import main.java.org.simulation.Coordinate;
import main.java.org.simulation.entity.Creature;

public interface SearchRoute {
    Coordinate getCoordinateNextStep(Coordinate currentCoordinate, Creature targetEat);
}
