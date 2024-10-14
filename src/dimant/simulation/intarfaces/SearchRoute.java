package dimant.simulation.intarfaces;

import dimant.simulation.Coordinate;

public interface SearchRoute {
    Coordinate getNextCoordinate(Coordinate currentCoordinate);
}
