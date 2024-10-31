package dimant.simulation;


import dimant.simulation.entity.*;
import dimant.simulation.intarfaces.Edible;
import dimant.simulation.service.CoordinateService;
import dimant.simulation.service.SearchRoute;


import java.util.*;

public class MapBoard {
    private List<Coordinate> coordinates;
    private Map<Coordinate, Wolf> wolfs = new HashMap<>();
    private Map<Coordinate, Entity> entityMap = new HashMap<>();
    private Integer sizeMapHeight;
    private Integer sizeMapWeight;


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
            if (entry.getValue().getClass().getSimpleName().equals(Wolf.class.getSimpleName()) || entry.getValue().getClass().getSimpleName().equals(Hare.class.getSimpleName())) {
                Creature creature = (Creature) entry.getValue();
                creaturesList.add(creature);
            }
        }
        return creaturesList;
    }

    public List<Coordinate> getCoordinateWolfsMap() {
        List<Coordinate> coordinates = new ArrayList<>();
        for (Coordinate coordinate : wolfs.keySet()) {
            coordinates.add(coordinate);
        }
        return coordinates;
    }


    public Coordinate getCoordinateWolf() {
        Coordinate coordinate = null; // TODO: 03.09.2024 не возвращай null
        for (Coordinate coordinateCurrent : entityMap.keySet()) {
            if (entityMap.get(coordinateCurrent) instanceof Wolf) {
                coordinate = coordinateCurrent;
            }
        }
        return coordinate;
    }

    public Coordinate getCoordinateHare() {
        Coordinate coordinate = null; // TODO: 03.09.2024 не возвращай null
        for (Coordinate coordinateCurrent : entityMap.keySet()) {
            if (entityMap.get(coordinateCurrent) instanceof Hare) {
                coordinate = coordinateCurrent;
            }
        }
        return coordinate;
    }

    public List<Coordinate> getCoordinateList() {
        List<Coordinate> list = new ArrayList<>();
        for (Coordinate coordinate : entityMap.keySet()) {
            list.add(coordinate);
        }
        return list;
    }

    public List<Entity> getEntityList() {
        List<Entity> list = new ArrayList<>();
        for (Entity entity : entityMap.values()) {
            list.add(entity);
        }
        return list;
    }


    public Coordinate getCreatureCoordinate(Creature creature) {
//        Coordinate coordinate = null; // TODO: 03.09.2024 не возвращай null
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


    public Wolf getWolf(Coordinate coordinate) {
        Entity entity = entityMap.get(coordinate);
        if (entity instanceof Wolf) {
            return (Wolf) entity;
        }
        return null;
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


    public void addEntityMap(Entity entity, CoordinateService coordinateService) {
        Coordinate coordinate = getFreeCoordinate();
        entityMap.put(coordinate, entity);
    }

    public void addEntityMap(Coordinate coordinateNewPosition, Entity entity) {
        entityMap.put(coordinateNewPosition, entity);
    }

    public void addEntityMapByCoordinate(Entity entity, int rowX, int colY) {
        for (Coordinate coordinate : coordinates) {
            if (coordinate.getX() == rowX && coordinate.getY() == colY) {
                entityMap.put(coordinate, entity);
            }
        }
    }


    public boolean isLocatedHare(Coordinate coordinate) {
        return entityMap.get(coordinate).getClass().getSimpleName().equals(Hare.class.getSimpleName());
    }

    public boolean isHare(Creature creature) {
        return creature instanceof Hare;
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







