package dimant.simulation.intarfaces;

import dimant.simulation.Coordinate;
import dimant.simulation.entity.Entity;
import dimant.simulation.entity.Place;

import java.util.List;

public interface IMap {
    void addEntityMap(Coordinate coordinate, Entity entity);
    List <Coordinate> getFreeListCoordinates();
    Coordinate getCoordinateByXY(int x, int y);
}
