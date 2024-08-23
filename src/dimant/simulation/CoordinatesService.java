package dimant.simulation;

import java.util.ArrayList;
import java.util.List;

public class CoordinatesService {
    private List<Coordinate> coordinatesList = new ArrayList<>();


    public CoordinatesService() {
        generationCoordinates();
    }

    public List<Coordinate> getCoordinatesList() {
        return coordinatesList;
    }

    public void setCoordinatesList(List<Coordinate> coordinatesList) {
        this.coordinatesList = coordinatesList;
    }

    public Coordinate getIsFreeCoordinate() {
        Coordinate targetCoordinate = new Coordinate(0,0,false); // TODO: Узнать как правильно не возвращать null
        for (Coordinate coordinate : getCoordinatesList())
            if (coordinate.isFree()){
                targetCoordinate = coordinate;
                targetCoordinate.setFree(false);
                break;
            }
        return targetCoordinate;
    }
    private void generationCoordinates(){
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                coordinatesList.add(new Coordinate(i, j, true));
            }
            
        }
        
    }
}
