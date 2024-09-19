package dimant.simulation.service;

import dimant.simulation.*;

import java.util.*;

public class SearchRoute {

    private MapBoard mapBoard;
    private HashMap<Coordinate, Coordinate> savePath = new HashMap<>();
    private Queue<Coordinate> checkCoordinates = new LinkedList<>();
    private Deque<Coordinate> viewedCoordinates = new LinkedList<>();
    private boolean isPathFound;


    public SearchRoute(MapBoard mapBoard) {
        this.mapBoard = mapBoard;
        this.isPathFound = false;
    }


    public void searchHare(Coordinate coordinateWolf) {
        checkCoordinates.add(coordinateWolf);
        while (!checkCoordinates.isEmpty()) {
            Coordinate viewCoordinate = checkCoordinates.remove();
            // если на извлеченной координате из списка просмотренных координат
            // находится травоядное то цель найдена поиск завершается
            if (hasEntityIsCoordinateHare(viewCoordinate)) {
                viewedCoordinates.add(viewCoordinate);
                System.out.println("цель найдена");
                isPathFound = true;
                break;
                // если координата, которую мы рассматриваем нет в списке
                // просмотренных координат, то я добавляю её в список просмотренных
                // а также добовляю все свободные соседние координаты в список проверяемых
            } else if (!hasCoordinateInViewedCoordinates(viewCoordinate)) {
                viewedCoordinates.add(viewCoordinate);
                checkCoordinates.addAll(getFreeCoordinatesAboutCurrent(viewCoordinate));
            }
        }
        if (!isPathFound) {
            System.out.println("пути нет");
        } else {
            while (!viewedCoordinates.isEmpty()) {
                Coordinate parentCoordinate = viewedCoordinates.pollLast();
                for (Coordinate childCoordinate : getFreeCoordinatesAboutCurrent(parentCoordinate)) {
                    if (hasCoordinateInViewedCoordinates(childCoordinate)) {
                            savePath(parentCoordinate,childCoordinate);
                        }
                    }
                }
            }
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

    public void savePath(Coordinate childCoordinate, Coordinate parentCoordinate) {
        savePath.put(childCoordinate, parentCoordinate);
    }

    public HashMap<Coordinate, Coordinate> getPath(Coordinate coordinateWolf) {
        searchHare(coordinateWolf);
        HashMap<Coordinate, Coordinate> result = new HashMap<>();
        for(Map.Entry<Coordinate,Coordinate> entry: savePath.entrySet()) {
            result.put(entry.getValue(), entry.getKey());
        }
        return result;
    }

    public boolean hasCoordinateInViewedCoordinates(Coordinate coordinate) {
        return viewedCoordinates.contains(coordinate);
    }

    public Coordinate getCoordinateToHare(Coordinate parent) {
        return savePath.get(parent);
    }


}
