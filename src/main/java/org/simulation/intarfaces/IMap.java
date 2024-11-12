package main.java.org.simulation.intarfaces;


import main.java.org.simulation.Coordinate;
import main.java.org.simulation.entity.Entity;

import java.util.List;

public interface IMap {
    void addEntityMap(Coordinate coordinate, Entity entity);
    List<Coordinate> getFreeListCoordinates();
    Coordinate getCoordinateByXY(int x, int y);
}
