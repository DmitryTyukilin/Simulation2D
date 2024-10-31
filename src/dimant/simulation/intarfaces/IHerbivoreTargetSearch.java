package dimant.simulation.intarfaces;

import dimant.simulation.Coordinate;
import dimant.simulation.entity.Creature;

public interface IHerbivoreTargetSearch {
    Coordinate nextCoordinateToTargetHerbivore(Coordinate creature, Coordinate target);

    Coordinate getCoordinateShortestPath(Coordinate currentCoordinate, Creature currentTypeCreature); // координата к ближайшей свободной цели

    Coordinate getCoordinateTargetAboutHerbivore(Coordinate currentCoordinate, Creature creature);

    boolean hasGrassByTargetCoordinate(Coordinate coordinate);

}
