package dimant.simulation.service;

import dimant.simulation.Coordinate;
import dimant.simulation.Place;
import dimant.simulation.Wolf;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CoordinateService {
    private List<Coordinate> coordinates = new ArrayList<>();

    public void creatCoordinates(int sizeX, int sizeY) {
        for (int coordinateX = 0; coordinateX < sizeX; coordinateX++) {
            for (int coordinateY = 0; coordinateY < sizeY; coordinateY++) {
                coordinates.add(new Coordinate(coordinateX, coordinateY));
            }
        }
        for(Coordinate coordinate : coordinates) {
            coordinate.setEntity(new Place());
        }
    }
    public List<Coordinate> getListCoordinates(){
        return coordinates;
    }

    public Queue<Coordinate> getEvenCoordinates(){
        Queue<Coordinate> coordinatesEven = new LinkedList<>();
        for (Coordinate coordinate : coordinates) {
            if(coordinate.getX() != 0 && coordinate.getX() % 2 == 0 && coordinate.getY() != 0 && coordinate.getY() % 2 == 0){
                coordinatesEven.add(coordinate);
            }

        }
        return coordinatesEven;
    }

}
