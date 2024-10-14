package dimant.simulation.service;

import dimant.simulation.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class CoordinateService {

    private List<Coordinate> coordinates = new ArrayList<>();

    public CoordinateService(int sizeX, int sizeY) {
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
