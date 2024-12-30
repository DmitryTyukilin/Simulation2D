package main.java.org.simulation;

import main.java.org.simulation.entity.*;
import main.java.org.simulation.intarfaces.IMap;


import java.util.*;

public final class WordMap implements IMap {
    private final Map<Coordinate, Entity> entityMap = new HashMap<>();
    private final Integer sizeMapHeight;
    private final Integer sizeMapWeight;


    public WordMap(int sizeMapHeight, int sizeMapWeight) {
        this.sizeMapHeight = sizeMapHeight;
        this.sizeMapWeight = sizeMapWeight;
        for (int coordinateX = 1; coordinateX < sizeMapHeight; coordinateX++) {
            for (int coordinateY = 1; coordinateY < sizeMapWeight; coordinateY++) {
                entityMap.put(new Coordinate(coordinateX, coordinateY), null);
            }
        }
    }

    public Integer getSizeMapHeight() {
        return sizeMapHeight;
    }

    public Integer getSizeMapWeight() {
        return sizeMapWeight;
    }


    public Coordinate getCoordinateEntity(Entity entity) {
        for (Map.Entry<Coordinate, Entity> entry : entityMap.entrySet()) {
            if (entry.getValue() != null && entry.getValue().equals(entity)) {
                return entry.getKey();
            }
        }
        return new Coordinate(1, 1);
    }

    public List<Entity> getEntityList() {
        return new ArrayList<>(entityMap.values());
    }


    public Coordinate getCoordinateCreature(Creature creature) {
        for (Map.Entry<Coordinate, Entity> entry : entityMap.entrySet()) {
            if (isEntityByCoordinateNotNull(entry.getKey()) && entry.getValue().equals(creature)) {
                return entry.getKey();
            }
        }
        return new Coordinate(1, 1);
    }

    public Coordinate getCoordinateByXY(int x, int y) throws IllegalArgumentException {
        if (x < 1 || y < 1) {
            throw new IllegalArgumentException("X Y должны быть больше 1");
        }
        Coordinate resultCoordinate = new Coordinate(x, y);
        for (Coordinate coordinate : entityMap.keySet()) {
            if (coordinate != null && coordinate.equals(resultCoordinate)) {
                resultCoordinate = coordinate;
                break;
            }
        }
        return resultCoordinate;
    }


    public boolean isEntityByCoordinateNotNull(Coordinate coordinate) {
        return entityMap.get(coordinate) != null;
    }

    public boolean isAvailableToMove(Coordinate coordinate) {
        return entityMap.get(coordinate) == null;
    }


    public Entity getEntityByCoordinate(Coordinate coordinate) {
        if (isEntityByCoordinateNotNull(coordinate)) {
            return entityMap.get(coordinate);
        } else return new Entity();
    }


    public Grass getGrass(Coordinate coordinate) {
        Entity entity = entityMap.get(coordinate);
        if (entity instanceof Grass) {
            return (Grass) entity;
        }
        return null;
    }

    public Coordinate getFreeCoordinate() {
        for (Map.Entry<Coordinate, Entity> entry : entityMap.entrySet()) {
            if (entry.getValue() == null) {
                return entry.getKey();
            }
        }
        return new Coordinate(1, 1);
    }

    public List<Coordinate> getFreeListCoordinates() {
        List<Coordinate> coordinatesFreePlace = new ArrayList<>();
        for (Map.Entry<Coordinate, Entity> entry : entityMap.entrySet()) {
            if (entry.getValue() == null) {
                Coordinate coordinatePlace = entry.getKey();
                coordinatesFreePlace.add(coordinatePlace);
            }
        }
        return coordinatesFreePlace;
    }


    public void addEntityMap(Coordinate coordinateNewPosition, Entity entity) {
        entityMap.put(coordinateNewPosition, entity);
    }

    public boolean containsMapBoardIsHasRock(Coordinate coordinate) {
        boolean result = false;
        if (entityMap.get(coordinate) != null) {
            result = entityMap.get(coordinate) instanceof Rock;
        }
        return result;
    }

    public boolean hasPredatorByCoordinate(Coordinate coordinate) {
        boolean result = false;
        if (entityMap.get(coordinate) != null) {
            result = entityMap.get(coordinate) instanceof Predator;
        }
        return result;
    }

    public void deleteEntity(Entity entity) {
        Coordinate coordinate = getCoordinateEntity(entity);
        entityMap.put(coordinate, null);
    }

}







