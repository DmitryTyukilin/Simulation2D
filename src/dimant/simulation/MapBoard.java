package dimant.simulation;


import dimant.simulation.service.CoordinateService;


import java.util.*;

public class MapBoard {
    private List<Coordinate> coordinates;
    private Map<Coordinate, Wolf> wolfs = new HashMap<>();
    private Map<Coordinate, Entity> entityMap = new HashMap<>();


    public MapBoard(CoordinateService coordinateService) {
        coordinates = coordinateService.getListCoordinates();
        for(Coordinate coordinate : coordinates){
            entityMap.put(coordinate,new Place());
        }
    }

    public List<Coordinate> getCoordinateWolfsMap() {
        List<Coordinate> coordinates = new ArrayList<>();
        for (Coordinate coordinate : wolfs.keySet()) {
            coordinates.add(coordinate);
        }
        return coordinates;
    }





    public List<Entity> getEntityList() {
        List<Entity> entitylist = new ArrayList<>();
        entitylist.addAll(entityMap.values());
        return entitylist;
    }


    public Coordinate getCoordinateWolf() {
        Coordinate coordinate = null; // TODO: 03.09.2024 не возвращай null
        for (Coordinate coordinateCurrent : entityMap.keySet()){
            if(entityMap.get(coordinateCurrent) instanceof Wolf) {
                coordinate = coordinateCurrent;
            }
        }
        return coordinate;
    }

    public Wolf getWolf(Coordinate currentCoordinateWolf) {
        Wolf wolf = null; // TODO: 03.09.2024 не возвращай null
        for (Entity coordinateCurrent : entityMap.values()){
            if(coordinateCurrent instanceof Wolf) {
                wolf = (Wolf) coordinateCurrent;
            }
        }
        return wolf;
    }

    public Hare getHare(Coordinate currentCoordinate) {
       Hare hare = null; // TODO: 03.09.2024 не возвращай null
        for (Entity coordinateCurrent : entityMap.values()){
            if(coordinateCurrent instanceof Hare) {
                hare = (Hare) coordinateCurrent;
            }
        }
        return hare;
    }



    public Coordinate getFreeCoordinate() {
        Coordinate coordinate = null; // TODO: 03.09.2024 не возвращай null 
        for (Coordinate coordinateCurrent : entityMap.keySet()){
            if(entityMap.get(coordinateCurrent) instanceof Place) {
                coordinate = coordinateCurrent;
            }
        }
        return coordinate;
    }



    public void addEntityMap(Entity entity, CoordinateService coordinateService) {
        Coordinate coordinate = getFreeCoordinate();
        entityMap.put(coordinate, entity);
    }
    public void addEntityMap(Coordinate coordinateNewPosition,Entity entity) {
        entityMap.put(coordinateNewPosition, entity);
    }

    public void addEntityMapByCoordinate(Entity entity, int rowX, int colY) {
        for (Coordinate coordinate : coordinates) {
            if (coordinate.getX() == rowX && coordinate.getY() == colY) {
                entityMap.put(coordinate, entity);
            }
        }
    }


    public Entity getEntityMap(Coordinate coordinateEntity) {
        return entityMap.get(coordinateEntity);
    }

    public boolean isLocatedHare(Coordinate coordinate){
           return entityMap.get(coordinate).getClass().getSimpleName().equals(Hare.class.getSimpleName());
    }

    public boolean containsMapBoardIsHasRock(Coordinate coordinate) {
        boolean result = false;
        if (entityMap.get(coordinate) != null) {
            result = entityMap.get(coordinate).getClass().getSimpleName().equals(Rock.class.getSimpleName());
        }
        return result;
    }


    public List<Coordinate> getCoordinates() {
        return coordinates;
    }
}







