package dimant.simulation.service;

import dimant.simulation.Coordinate;
import dimant.simulation.Hare;
import dimant.simulation.Herbivore;
import dimant.simulation.Wolf;

import java.util.List;
import java.util.Queue;

public class SearchRoute {

    List<Coordinate> coordinates;

    public SearchRoute(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public Queue<Coordinate> searchHare(Wolf wolf, Hare hare) {

    }

    public Queue<Coordinate> getFreeCoordinateAboutCurrent(Coordinate current) {

    }
}
