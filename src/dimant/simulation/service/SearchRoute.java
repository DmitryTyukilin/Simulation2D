package dimant.simulation.service;

import dimant.simulation.*;

import java.nio.charset.CoderResult;
import java.util.*;

public class SearchRoute {

    private MapBoard mapBoard;
    private Queue<ParentChildCoordinate> parentChildCoordinates = new LinkedList<>();
    private List<Coordinate> resultCoordinates = new LinkedList<>();
    private List<ParentChildCoordinate> savePath = new ArrayList<>();
    private Queue<ParentChildCoordinate> checkCoordinates = new LinkedList<>();
    private Deque<Coordinate> viewedCoordinates = new LinkedList<>();
    private boolean isPathFound;


    public SearchRoute(MapBoard mapBoard) {
        this.mapBoard = mapBoard;
        this.isPathFound = false;
    }


    public void searchHare(Coordinate coordinateWolf) {
        ParentChildCoordinate currentCoordinate = new ParentChildCoordinate(null, coordinateWolf);
        ParentChildCoordinate target = new ParentChildCoordinate(null, coordinateWolf);
        checkCoordinates.add(currentCoordinate);
        while (!checkCoordinates.isEmpty()) {
            ParentChildCoordinate viewCoordinate = checkCoordinates.remove();
            if (hasEntityIsCoordinateHare(viewCoordinate.getChildren())) {
                savePath.add(viewCoordinate);
                System.out.println("цель найдена");
                isPathFound = true;
                target = viewCoordinate;
                break;
            } else if (!isAccessToMove(viewCoordinate) && !hasCoordinateInSavePatch(viewCoordinate) ) {
                savePath.add(viewCoordinate);
                checkCoordinates.addAll(getFreeCoordinatesAboutCurrent(viewCoordinate.children));
            }
        }
        if (!isPathFound) {
            System.out.println("пути нет");
        } else {
            createSavePath(target);
        }
    }

    public boolean hasEntityIsCoordinateHare(Coordinate coordinate) {
        Entity entity = mapBoard.getEntityMap(coordinate);
        return entity.getClass().getSimpleName().equals(Hare.class.getSimpleName());
    }

    public boolean hasCoordinateInSavePatch(ParentChildCoordinate coordinate) {
        return savePath.contains(coordinate);
    }


    public Queue<ParentChildCoordinate> getFreeCoordinatesAboutCurrent(Coordinate current) {
        int currentX = current.getX();
        int currentY = current.getY();
        int newX = 0;
        int newY = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        Queue<ParentChildCoordinate> resultCoordinates = new LinkedList<>();
        for (int i = 0; i < directions.length; i++) {
            newX = currentX + directions[i][0];
            newY = currentY + directions[i][1];
            for (Coordinate coordinate : mapBoard.getCoordinates()) {
                if (coordinate.getX() == newX && coordinate.getY() == newY && !mapBoard.containsMapBoardIsHasRock(coordinate)) {
                    resultCoordinates.add(new ParentChildCoordinate(current, coordinate));
                }
            }
        }
        return resultCoordinates;
    }

    public boolean isAccessToMove(ParentChildCoordinate coordinate) { // место по координате доступно для хода не скала
        return mapBoard.getEntityMap(coordinate.children).getClass().equals(Rock.class);
    }


    public void createSavePath(ParentChildCoordinate coordinateTarget) {
        Coordinate currentCoordinateParent = coordinateTarget.parent;
        resultCoordinates.add(coordinateTarget.children);
        for (int i = savePath.size() - 1; i > 0; i--) {
            if (savePath.get(i).children.equals(currentCoordinateParent)) {
                resultCoordinates.add(savePath.get(i).children);
                currentCoordinateParent = savePath.get(i).parent;
            }
        }
    }


    public List<Coordinate> getFreeCoordinatesAboutCurrentList(Coordinate current) {
        int currentX = current.getX();
        int currentY = current.getY();
        int newX = 0;
        int newY = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        List<Coordinate> resultCoordinates = new LinkedList<>();
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


    public Coordinate getNextCoordinate(Coordinate currentCoordinate) {
        searchHare(currentCoordinate);
        return resultCoordinates.get(resultCoordinates.size() - 1);
    }


    public class ParentChildCoordinate {
        Coordinate children;
        Coordinate parent;

        public ParentChildCoordinate(Coordinate parent, Coordinate children) {
            this.parent = parent;
            this.children = children;
        }

        public Coordinate getCoordinateParent() {
            return parent;
        }

        public Coordinate getChildren() {
            return children;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ParentChildCoordinate that = (ParentChildCoordinate) o;
            return Objects.equals(children, that.children);
        }

        @Override
        public int hashCode() {
            return Objects.hash(children);
        }
    }
}
















