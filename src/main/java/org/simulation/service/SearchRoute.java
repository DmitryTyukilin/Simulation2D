package main.java.org.simulation.service;


import main.java.org.simulation.Coordinate;
import main.java.org.simulation.MapBoard;
import main.java.org.simulation.entity.*;

import java.util.*;

public class SearchRoute implements main.java.org.simulation.intarfaces.SearchRoute {

    private List<Coordinate> foundCoordinatesPath = new LinkedList<>();
    private List<ParentChildCoordinate> savePath = new ArrayList<>();
    private final MapBoard mapBoard;
    private Queue<ParentChildCoordinate> checkCoordinates = new LinkedList<>();
    private boolean isPathFound;


    public SearchRoute(MapBoard mapBoard) {
        this.mapBoard = mapBoard;
        this.isPathFound = false;
    }

    public Coordinate getCoordinateNextStep(Coordinate currentCoordinate, Creature targetEat) {
        searchPath(currentCoordinate, targetEat);
        Coordinate result;
        Coordinate coordinateNextStep = getCoordinateNextStep();
        if (!coordinateNextStep.equals(new Coordinate(1, 1))) {
            result = coordinateNextStep;
        } else {
            result = currentCoordinate;
        }
        resetValueFields();
        return result;
    }

    private Coordinate getCoordinateNextStep() {
        Coordinate validateCoordinate = new Coordinate(1, 1);
        if (foundCoordinatesPath.size() != 0) {
            validateCoordinate = foundCoordinatesPath.get(foundCoordinatesPath.size() - 1);
        }
        return validateCoordinate;
    }

    private void resetValueFields() {
        foundCoordinatesPath = new LinkedList<>();
        savePath = new ArrayList<>();
        checkCoordinates = new LinkedList<>();
        isPathFound = false;
    }

    private void searchPath(Coordinate coordinateCreature, Creature creature) {
        ParentChildCoordinate currentCoordinate = new ParentChildCoordinate(null, coordinateCreature);
        ParentChildCoordinate targetEat = new ParentChildCoordinate(null, coordinateCreature);
        checkCoordinates.add(currentCoordinate);
        while (!checkCoordinates.isEmpty()) {
            ParentChildCoordinate viewCoordinate = checkCoordinates.remove();
            Coordinate viewCoordinateChildren = viewCoordinate.getChildren();//рассматриваемая координата
            if (isCoordinateEatForCreature(viewCoordinateChildren, creature)) {
                savePath.add(viewCoordinate);
                isPathFound = true;
                targetEat = viewCoordinate;
                break;
            } else if (!hasCoordinateInSavePatch(viewCoordinate)) {
                savePath.add(viewCoordinate);
                Queue<ParentChildCoordinate> coordinates = getFreeCoordinatesAboutCurrent(viewCoordinateChildren);
                checkCoordinates.addAll(coordinates);
            }
        }
        if (!isPathFound) {
            System.out.print("");
        } else {
            createSavePath(targetEat);
        }
    }

    private boolean isCoordinateEatForCreature(Coordinate coordinate, Creature creature) {
        Entity entity = mapBoard.getEntityByCoordinate(coordinate);
        boolean result = false;
        if (creature instanceof Predator) {
            result = entity instanceof Herbivore;
        } else if (creature instanceof Herbivore) {
            result = entity instanceof Grass;
        }
        return result;
    }

    private boolean hasCoordinateInSavePatch(ParentChildCoordinate coordinate) {
        return savePath.contains(coordinate);
    }

    private Queue<ParentChildCoordinate> getFreeCoordinatesAboutCurrent(Coordinate current) {
        int currentX = current.getX();
        int currentY = current.getY();
        int newX;
        int newY;
        int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        Queue<ParentChildCoordinate> resultCoordinates = new LinkedList<>();
        for (int i = 0; i < directions.length; i++) {
            newX = currentX + directions[i][0];
            newY = currentY + directions[i][1];
            if (isXYValidParameters(newX, newY)) {
                Coordinate checkingCoordinate = mapBoard.getCoordinateByXY(newX, newY);
                if (!mapBoard.containsMapBoardIsHasRock(checkingCoordinate) && !mapBoard.hasPredatorByCoordinate(checkingCoordinate)) {
                    resultCoordinates.add(new ParentChildCoordinate(current, checkingCoordinate));
                }
            }
        }
        return resultCoordinates;
    }

    private boolean isXYValidParameters(int x, int y) {
        return x >= 1 && x <= mapBoard.getSizeMapHeight() && y >= 1 && y <= mapBoard.getSizeMapWeight();
    }


    private void createSavePath(ParentChildCoordinate coordinateTargetChildren) {
        Coordinate currentCoordinateParent = coordinateTargetChildren.parent;
        foundCoordinatesPath.add(coordinateTargetChildren.children);
        for (int i = savePath.size() - 1; i > 0; i--) {
            if (savePath.get(i).children.equals(currentCoordinateParent)) {
                foundCoordinatesPath.add(savePath.get(i).children);
                currentCoordinateParent = savePath.get(i).parent;
            }
        }
    }

    private static class ParentChildCoordinate {
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


















