package main.java.org.simulation.service;


import main.java.org.simulation.Coordinate;
import main.java.org.simulation.WordMap;
import main.java.org.simulation.entity.*;

import java.util.*;

public class SearchRoute implements main.java.org.simulation.intarfaces.SearchRoute {

    private List<Coordinate> foundCoordinatesPath = new LinkedList<>();
    private List<ParentChildCoordinate> savePath = new ArrayList<>();
    private final WordMap wordMap;
    private Queue<ParentChildCoordinate> checkCoordinates = new LinkedList<>();
    private boolean isPathFound;


    public SearchRoute(WordMap wordMap) {
        this.wordMap = wordMap;
        this.isPathFound = false;
    }

    public Coordinate getCoordinateNextStep(Coordinate currentCoordinate, Creature currentCreature) {
        searchPath(currentCoordinate, currentCreature);
        Coordinate result;
        Coordinate coordinateNextStep = getCoordinateNextStep(currentCreature);
        if (!coordinateNextStep.equals(new Coordinate(1, 1))) {
            result = coordinateNextStep;
        } else {
            result = currentCoordinate;
        }
        resetValueFields();
        return result;
    }

    private Coordinate getCoordinateNextStep(Creature currentCreature) {
        Coordinate validateCoordinate = new Coordinate(1, 1);
        if (foundCoordinatesPath.size() != 0) {
            if (hasCreatureSpeed(currentCreature) && !isSpeedMorePathSize(currentCreature)) {
                validateCoordinate = foundCoordinatesPath.get(foundCoordinatesPath.size() - 1 - currentCreature.getSpeed());
            } else {
                validateCoordinate = foundCoordinatesPath.get(foundCoordinatesPath.size() - 1);
            }
        }
        return validateCoordinate;
    }

    private boolean hasCreatureSpeed(Creature creature) {
        return creature.getSpeed() > 0;
    }

    private boolean isSpeedMorePathSize(Creature creature) {
        return foundCoordinatesPath.size() <= creature.getSpeed();
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
            Coordinate viewCoordinateChildren = viewCoordinate.getChildren();
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
        Entity entity = wordMap.getEntityByCoordinate(coordinate);
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
                Coordinate checkingCoordinate = wordMap.getCoordinateByXY(newX, newY);
                if (!wordMap.containsMapBoardIsHasRock(checkingCoordinate) && !wordMap.hasPredatorByCoordinate(checkingCoordinate)) {
                    resultCoordinates.add(new ParentChildCoordinate(current, checkingCoordinate));
                }
            }
        }
        return resultCoordinates;
    }

    private boolean isXYValidParameters(int x, int y) {
        return x >= 1 && x <= wordMap.getSizeMapHeight() && y >= 1 && y <= wordMap.getSizeMapWeight();
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


















