package dimant.simulation.intarfaces;

import dimant.simulation.Coordinate;
import dimant.simulation.entity.Creature;
import dimant.simulation.entity.Entity;

public interface SearchRoute {
    Coordinate getNextCoordinate(Coordinate currentCoordinate, Creature targetEat);
}
