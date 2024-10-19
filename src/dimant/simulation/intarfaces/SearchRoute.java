package dimant.simulation.intarfaces;

import dimant.simulation.Coordinate;
import dimant.simulation.entity.Entity;

public interface SearchRoute {
    Coordinate getNextCoordinate(Coordinate currentCoordinate, Entity targetEat);
}
