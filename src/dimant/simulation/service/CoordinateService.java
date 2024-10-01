package dimant.simulation.service;

import dimant.simulation.Coordinate;
import dimant.simulation.MapBoard;
import dimant.simulation.Place;
import dimant.simulation.Wolf;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CoordinateService {

    private List<Coordinate> coordinates = new ArrayList<>();

    public void creatCoordinates(int sizeX, int sizeY) {
        for (int coordinateX = 1; coordinateX < sizeX; coordinateX++) {
            for (int coordinateY = 1; coordinateY < sizeY; coordinateY++) {
                coordinates.add(new Coordinate(coordinateX, coordinateY));
            }
        }
    }

    public List<Coordinate> getListCoordinates() {
        return coordinates;
    }





}
