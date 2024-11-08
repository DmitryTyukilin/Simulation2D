package dimant.simulation.service;

import dimant.simulation.*;
import dimant.simulation.entity.*;
import dimant.simulation.intarfaces.IHerbivoreTargetSearch;

import java.util.*;

public class SearchRoute implements dimant.simulation.intarfaces.SearchRoute, IHerbivoreTargetSearch {

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

    public Coordinate getNextCoordinate(Coordinate currentCoordinate, Creature targetEat) {
        searchPath(currentCoordinate, targetEat);
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

    private Coordinate getNextCoordinateHerbivoreToTargetEat(Coordinate creature, Coordinate targetEat) {
        Coordinate result;
        Coordinate validateCoordinate = getValidateCoordinate();
        if (!validateCoordinate.equals(new Coordinate(1, 1))) {
            result = validateCoordinate;
        } else {
            result = creature;
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
                checkCoordinates.addAll(getFreeCoordinatesAboutCurrent(viewCoordinate.children));
            }
        }
        if (!isPathFound) {
            System.out.println("пути нет" + creature.toString());
        } else {
            createSavePath(targetEat);
        }
    }


    private boolean isCoordinateEatForCreature(Coordinate coordinate, Creature creature) {
        Entity entity = mapBoard.getEntityMap(coordinate);
        String nameClass = entity.getClass().getSimpleName();
        boolean result = false;
        if (creature instanceof Wolf) {
            result = nameClass.equals(Hare.class.getSimpleName());
        } else if (creature instanceof Hare) {
            result = nameClass.equals(Grass.class.getSimpleName());
        }
        return result;
    }

    private boolean isCoordinateTargetEatForHerbivore(Coordinate targetEatHerbivore, Coordinate viewCoordinate) {
        return targetEatHerbivore.equals(viewCoordinate);
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
                if (coordinate.getX() == newX && coordinate.getY() == newY && !mapBoard.containsMapBoardIsHasRock(coordinate) && !mapBoard.hasWolfByCoordinate(coordinate)) {
                    resultCoordinates.add(new ParentChildCoordinate(current, coordinate));
                }
            }
        }
        return resultCoordinates;
    }

    private void createSavePath(ParentChildCoordinate coordinateTargetChildren) {
        Coordinate currentCoordinateParent = coordinateTargetChildren.parent;
        resultCoordinates.add(coordinateTargetChildren.children);
        for (int i = savePath.size() - 1; i > 0; i--) {
            if (savePath.get(i).children.equals(currentCoordinateParent)) {
                resultCoordinates.add(savePath.get(i).children);
                currentCoordinateParent = savePath.get(i).parent;
            }
        }
    }

    @Override
    public Coordinate nextCoordinateToTargetHerbivore(Coordinate creature, Coordinate target) {
        searchToTargetHerbivore(creature, target);
        return getNextCoordinateHerbivoreToTargetEat(creature, target);
    }

    @Override
    public Coordinate getCoordinateShortestPath(Coordinate currentCoordinate, Creature currentTypeCreature) {
        return getNextCoordinate(currentCoordinate, currentTypeCreature);
    }

    @Override
    public Coordinate getCoordinateTargetAboutHerbivore(Coordinate coordinate, Creature creature) {
        searchPath(coordinate, creature);
        Coordinate target = resultCoordinates.get(0);
        resetValueFields();
        return target;
    }

    @Override
    public boolean hasGrassByTargetCoordinate(Coordinate coordinate) {
       if (mapBoard.getGrass(coordinate) != null) {
           return true;
       } else return false;
    }


    private void searchToTargetHerbivore(Coordinate creature, Coordinate targetEatHerbivore) {
        ParentChildCoordinate currentCoordinate = new ParentChildCoordinate(null, creature);
        ParentChildCoordinate targetEat = new ParentChildCoordinate(null, creature);
        checkCoordinates.add(currentCoordinate);
        while (!checkCoordinates.isEmpty()) {
            ParentChildCoordinate viewCoordinate = checkCoordinates.remove();
            Coordinate viewCoordinateChildren = viewCoordinate.getChildren();//рассматриваемая координата
            if (isCoordinateTargetEatForHerbivore(targetEatHerbivore, viewCoordinateChildren)) {
                savePath.add(viewCoordinate);
                isPathFound = true;
                targetEat = viewCoordinate;
                break;
            } else if (!hasCoordinateInSavePatch(viewCoordinate)) {
                savePath.add(viewCoordinate);
                checkCoordinates.addAll(getFreeCoordinatesAboutCurrent(viewCoordinate.children));
            }
        }
        if (!isPathFound) {
            System.out.println("пути нет" + creature.toString());
        } else {
            createSavePath(targetEat);
        }

    }


    private boolean hasCoordinateInSavePatch(ParentChildCoordinate coordinate) {
        return savePath.contains(coordinate);
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


















