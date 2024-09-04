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
    private List<Coordinate> notFreeCoordinates = new ArrayList<>();

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

//    public Coordinate getFreeCoordinate() {
//        Coordinate result = null;
//        for (Coordinate coordinate : coordinates) {
//            if (coordinate.getEntity() instanceof Place && !notFreeCoordinates.contains(coordinate)) {
//                result = coordinate; // TODO: 02.09.2024 отрефакторить, нельзя возвращать null
//                notFreeCoordinates.add(result);
//                break;
//            }
//        }
//        return result;
//    }

    public Queue<Coordinate> getEvenCoordinates() {
        Queue<Coordinate> coordinatesEven = new LinkedList<>();
        for (Coordinate coordinate : coordinates) {
            if (coordinate.getX() != 0 && coordinate.getX() % 2 == 0 && coordinate.getY() != 0 && coordinate.getY() % 2 == 0) {
                coordinatesEven.add(coordinate);
            }

        }
        return coordinatesEven;
    }


}
