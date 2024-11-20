package main.java.org.simulation;
import main.java.org.simulation.entity.*;
import main.java.org.simulation.intarfaces.IMap;
import main.java.org.simulation.service.CoordinateService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MapBoard implements IMap {
    private final List<Coordinate> coordinates;
    private final Map<Coordinate, Entity> entityMap = new HashMap<>();
    private final Integer sizeMapHeight;
    private final Integer sizeMapWeight;


    public MapBoard(int sizeMapHeight, int sizeMapWeight) {
        this.sizeMapHeight = sizeMapHeight;
        this.sizeMapWeight = sizeMapWeight;
        CoordinateService coordinateService = new CoordinateService(sizeMapHeight, sizeMapWeight);
        coordinates = coordinateService.getListCoordinates();
        for (Coordinate coordinate : coordinates) {
            entityMap.put(coordinate, new Place());
        }
    }

    public Map<Coordinate, Entity> getEntityMap() {
        return entityMap;

    }

    public Integer getSizeMapHeight() {
        return sizeMapHeight;
    }

    public Integer getSizeMapWeight() {
        return sizeMapWeight;
    }

    public List<Creature> getCreature() {
        List<Creature> creaturesList = new ArrayList<>();
        for (Map.Entry<Coordinate, Entity> entry : entityMap.entrySet()) {
            if (entry.getValue() instanceof  Creature) {
                Creature creature = (Creature) entry.getValue();
                creaturesList.add(creature);
            }
        }
        return creaturesList;
    }

    public Coordinate getCoordinateEntity(Entity entity) {
        for (Map.Entry<Coordinate, Entity> entry : entityMap.entrySet()) {
            if (entry.getValue().equals(entity)) {
                return entry.getKey();
            }
        }
        return new Coordinate(1, 1);
    }

    public void deleteEntity(Entity entity) {
        Coordinate coordinate = getCoordinateEntity(entity);
        entityMap.put(coordinate, new Place());
    }

    public List<Entity> getEntityList() {
        return new ArrayList<>(entityMap.values());
    }

    public Coordinate getCreatureCoordinate(Creature creature) {
        for (Map.Entry<Coordinate, ? extends Entity> entries : entityMap.entrySet()) {
            if (entries.getValue().equals(creature)) {
                return entries.getKey();
            }
        }
        return null;
    }

    public Coordinate getCoordinateByXY(int x, int y) {
        Coordinate resultCoordinate = new Coordinate(x, y);
        for (Coordinate coordinate : entityMap.keySet()) {
            if (coordinate.getX() == x && coordinate.getY() == y) {
                resultCoordinate = coordinate;
                break;
            }
        }
        return resultCoordinate;
    }

    public Entity getEntityByCoordinate(Coordinate coordinate) {
        return entityMap.get(coordinate);
    }

    public Entity getEntityMap(Coordinate coordinateEntity) {
        return entityMap.get(coordinateEntity);
    }

    public Hare getHare(Coordinate coordinate) {
        Entity entity = entityMap.get(coordinate);
        if (entity instanceof Hare) {
            return (Hare) entity;
        }
        return null;
    }

    public Grass getGrass(Coordinate coordinate) {
        Entity entity = entityMap.get(coordinate);
        if (entity instanceof Grass) {
            return (Grass) entity;
        }
        return null;
    }

    public Coordinate getFreeCoordinate() {
        Coordinate coordinate = null; // TODO: 03.09.2024 не возвращай null
        for (Coordinate coordinateCurrent : entityMap.keySet()) {
            if (entityMap.get(coordinateCurrent) instanceof Place) {
                coordinate = coordinateCurrent;
            }
        }
        return coordinate;
    }

    public List <Coordinate> getFreeListCoordinates() {
        List<Coordinate> coordinatesFreePlace = new ArrayList<>();
        for (Map.Entry<Coordinate,Entity> entry : entityMap.entrySet()) {
            if (entry.getValue() instanceof Place) {
                Coordinate coordinatePlace = entry.getKey();
                coordinatesFreePlace.add(coordinatePlace);
            }
        }
        return coordinatesFreePlace;
    }

    public void addEntityMap(Coordinate coordinateNewPosition, Entity entity) {
        entityMap.put(coordinateNewPosition, entity);
    }

    public boolean hasEntityOnMapBoard(Entity entity){
        return entityMap.containsValue(entity);
    }

    public boolean containsMapBoardIsHasRock(Coordinate coordinate) {
        boolean result = false;
        if (entityMap.get(coordinate) != null) {
            result = entityMap.get(coordinate) instanceof Rock;
        }
        return result;
    }

    public boolean hasWolfByCoordinate(Coordinate coordinate) {
        boolean result = false;
        if (entityMap.get(coordinate) != null) {
            result = entityMap.get(coordinate) instanceof Wolf;
        }
        return result;
    }


    public List<Coordinate> getCoordinates() {
        return coordinates;
    }
}







