package dimant.simulation.service;

import dimant.simulation.*;

import java.util.*;

public class SearchRoute {

    private MapBoard mapBoard;
    private Map<Coordinate, Coordinate> savePath = new HashMap<>();
    private Queue<Coordinate> checkCoordinates = new LinkedList<>();
    private Set<Coordinate> viewedCoordinates = new HashSet<>();


    public SearchRoute(List<Coordinate> coordinates, MapBoard mapBoard) {
        this.mapBoard = mapBoard;
    }


    public void searchHare(Coordinate coordinateWolf) {
        Queue<Coordinate> notCheckCoordinates;

        checkCoordinates.add(coordinateWolf);
        while (!checkCoordinates.isEmpty()) {
            Coordinate currentCoordinate = checkCoordinates.remove();
            notCheckCoordinates = getFreeCoordinatesAboutCurrent(currentCoordinate);
            while (!notCheckCoordinates.isEmpty()) {
                Coordinate coordinate = notCheckCoordinates.remove();
                if (hasEntityIsCoordinateHare(coordinate)) {
                    savePath(coordinate, currentCoordinate);
                    break;
                } else if (!hasCoordinateQueue(coordinate)) {
                    checkCoordinates.add(coordinate);
                    savePath(coordinate, currentCoordinate);
                    viewedCoordinates.add(coordinate);
                }

            }
        }
        System.out.println("Пути нет");
    }


    public Queue<Coordinate> getFreeCoordinatesAboutCurrent(Coordinate current) {

        int currentX = current.getX();
        int currentY = current.getY();
        int newX = 0;
        int newY = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        Queue<Coordinate> resultCoordinates = new LinkedList<>();
        for (int i = 0; i < directions.length; i++) {
            newX = currentX + directions[i][0];
            newY = currentY + directions[i][1];
            for (Coordinate coordinate : mapBoard.getCoordinates()) {
                if (coordinate.getX() == newX && coordinate.getY() == newY && !mapBoard.containsMapBoardIsHasRock(coordinate)) {
                    resultCoordinates.add(coordinate);
                }
            }
        }
        return resultCoordinates;
    }

    public boolean hasEntityIsCoordinateHare(Coordinate coordinate) {
        Entity entity = mapBoard.getEntityMap(coordinate);
        return entity.getClass().getSimpleName().equals(Hare.class.getSimpleName());
    }

    public void savePath(Coordinate currentCoordinate, Coordinate checkCoordinate) {
        savePath.put(checkCoordinate, currentCoordinate);
    }

    public boolean hasCoordinateQueue(Coordinate coordinate) {
        return viewedCoordinates.contains(coordinate);

    }

}
