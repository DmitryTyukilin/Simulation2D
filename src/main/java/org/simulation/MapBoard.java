package main.java.org.simulation;

import main.java.org.simulation.entity.*;
import main.java.org.simulation.intarfaces.IMap;
import main.java.org.simulation.utils.RandomIntValue;

import java.util.*;

public final class MapBoard implements IMap {
    private final Map<Coordinate, Entity> entityMap = new HashMap<>();
    private final Integer sizeMapHeight;
    private final Integer sizeMapWeight;
    private final List<Grass> grassList = new ArrayList<>();
    private final List<Herbivore> herbivoresList = new ArrayList<>();


    public MapBoard(int sizeMapHeight, int sizeMapWeight) {
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

    public List<Creature> getCreature() {
        List<Creature> creaturesList = new ArrayList<>();
        for (Map.Entry<Coordinate, Entity> entry : entityMap.entrySet()) {
            if (entry.getValue() instanceof Creature) {
                Creature creature = (Creature) entry.getValue();
                creaturesList.add(creature);
            }
        }
        return creaturesList;
    }

    public Coordinate getCoordinateEntity(Entity entity) {
        for (Map.Entry<Coordinate, Entity> entry : entityMap.entrySet()) {
            if (entry.getValue() != null && entry.getValue().equals(entity)) {
                return entry.getKey();
            }
        }
        return new Coordinate(1, 1);
    }

    public void deleteEntity(Entity entity) {
        Coordinate coordinate = getCoordinateEntity(entity);
        entityMap.put(coordinate, null);
    }

    public List<Entity> getEntityList() {
        return new ArrayList<>(entityMap.values());
    }

    /**
     * @param creature
     * @return null теоретически не должно быть так как координаты на карте не зануляются в нулл
     * и всегда сущетсвуют на протяжении всего цикла проекта
     */
    public Coordinate getCoordinateCreature(Creature creature) {
        for (Map.Entry<Coordinate, Entity> entry : entityMap.entrySet()) {
            if (isEntityByCoordinateNotNull(entry.getKey()) && entry.getValue().equals(creature)) {
                return entry.getKey();
            }
        }
        return new Coordinate(1, 1);
    }

    public Coordinate getCoordinateByXY(int x, int y) {
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

    public Entity getEntityByCoordinate(Coordinate coordinate) {
        if (isEntityByCoordinateNotNull(coordinate)) {
            return entityMap.get(coordinate);
        } else return new Entity();
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

    public boolean hasEntityOnMapBoard(Entity entity) {
        return entityMap.containsValue(entity);
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

    public List<Coordinate> getListCoordinates() {
        return new ArrayList<>(entityMap.keySet());
    }

    public boolean hasHerbivoreMapBoard() {
        return herbivoresList.size() > 0;
    }

    public void setHerbivoresListEntityService() {
        for (Entity entity : getEntityList()) {
            if (entity instanceof Herbivore) {
                herbivoresList.add((Herbivore) entity);
            }
        }
    }

    public void setGrassListEntityService() {
        for (Entity entity : getEntityList()) {
            if (entity instanceof Grass) {
                grassList.add((Grass) entity);
            }
        }
    }
    public void deleteEntityMap(Entity entity) {
        if (entity instanceof Grass) {
            grassList.remove(entity);
            deleteEntity(entity);
        } else if (entity instanceof Herbivore) {
            herbivoresList.remove(entity);
            deleteEntity(entity);
        }
    }

    public void addGrassMapBoard() {
        int valueGrass = RandomIntValue.randomIndex(1, 3);
        for (int i = 0; i < valueGrass; i++) {
            Coordinate coordinate = getFreeCoordinate();
            Grass grass = new Grass();
            grassList.add(grass);
            addEntityMap(coordinate, grass);
        }
    }
    public int getValueGrass() {
        return grassList.size();
    }
}







