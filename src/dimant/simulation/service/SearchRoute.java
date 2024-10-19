package dimant.simulation.service;

import dimant.simulation.*;
import dimant.simulation.entity.*;

import java.util.*;

public class SearchRoute implements dimant.simulation.intarfaces.SearchRoute {

    private List<Coordinate> resultCoordinates = new LinkedList<>();
    private List<ParentChildCoordinate> savePath = new ArrayList<>();
    private MapBoard mapBoard;
    private Queue<ParentChildCoordinate> parentChildCoordinates = new LinkedList<>();
    private Queue<ParentChildCoordinate> checkCoordinates = new LinkedList<>();
    private Deque<Coordinate> viewedCoordinates = new LinkedList<>();
    private boolean isPathFound;


    public SearchRoute(MapBoard mapBoard) {
        this.mapBoard = mapBoard;
        this.isPathFound = false;
    }

    public Coordinate getNextCoordinate(Coordinate currentCoordinate, Entity targetEat ) {
        searchTarget(currentCoordinate, targetEat);
        Coordinate result;
        Coordinate validateCoordinate = getValidateCoordinate();
        if (!validateCoordinate.equals(new Coordinate(1, 1))) {
            result = validateCoordinate;
        } else {
            result = currentCoordinate;
        }
        resetValueFields();
        return result;
    }

    private Coordinate getValidateCoordinate() {
        Coordinate validateCoordinate = new Coordinate(1, 1);
        if (resultCoordinates.size() != 0) {
            validateCoordinate = resultCoordinates.get(resultCoordinates.size() - 1);
        }
        return validateCoordinate;
    }


    private void resetValueFields() {
        parentChildCoordinates = new LinkedList<>();
        resultCoordinates = new LinkedList<>();
        savePath = new ArrayList<>();
        checkCoordinates = new LinkedList<>();
        viewedCoordinates = new LinkedList<>();
        isPathFound = false;
    }

    private void searchTarget(Coordinate coordinateCreature, Entity targetEat) {
        ParentChildCoordinate currentCoordinate = new ParentChildCoordinate(null, coordinateCreature);
        ParentChildCoordinate target = new ParentChildCoordinate(null, coordinateCreature);
        checkCoordinates.add(currentCoordinate);
        while (!checkCoordinates.isEmpty()) {
            ParentChildCoordinate viewCoordinate = checkCoordinates.remove();
            if (hasEntityIsCoordinateTargetEat(viewCoordinate.getChildren(), targetEat)) {
                savePath.add(viewCoordinate);
                isPathFound = true;
                target = viewCoordinate;
                break;
            } else if (!isAccessToMove(viewCoordinate) && !hasCoordinateInSavePatch(viewCoordinate)) {
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

    private boolean hasEntityIsCoordinateTargetEat(Coordinate coordinate, Entity targetEat) {
        Entity entity = mapBoard.getEntityMap(coordinate);
        String nameClass = entity.getClass().getSimpleName();
        boolean result = false;
        if (targetEat.getClass().getSimpleName().equals(Wolf.class.getSimpleName())) {
            result = nameClass.equals(Hare.class.getSimpleName());
        } else if (targetEat.getClass().getSimpleName().equals(Hare.class.getSimpleName())) {
            result = nameClass.equals(Grass.class.getSimpleName());
        }
        return result;
    }

    private boolean isAccessToMove(ParentChildCoordinate coordinate) { // место по координате доступно для хода не скала
        return mapBoard.getEntityMap(coordinate.children).getClass().equals(Rock.class);
    }

    private boolean hasCoordinateInSavePatch(ParentChildCoordinate coordinate) {
        return savePath.contains(coordinate);
    }

    private Queue<ParentChildCoordinate> getFreeCoordinatesAboutCurrent(Coordinate current) {
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

    private void createSavePath(ParentChildCoordinate coordinateTarget) {
        Coordinate currentCoordinateParent = coordinateTarget.parent;
        resultCoordinates.add(coordinateTarget.children);
        for (int i = savePath.size() - 1; i > 0; i--) {
            if (savePath.get(i).children.equals(currentCoordinateParent)) {
                resultCoordinates.add(savePath.get(i).children);
                currentCoordinateParent = savePath.get(i).parent;
            }
        }
    }

    private class ParentChildCoordinate {
        Coordinate children;
        Coordinate parent;

        public ParentChildCoordinate(Coordinate parent, Coordinate children) {
            this.parent = parent;
            this.children = children;
        }


        private Coordinate getChildren() {
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
















