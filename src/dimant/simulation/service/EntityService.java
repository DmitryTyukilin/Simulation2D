package dimant.simulation.service;

import dimant.simulation.*;
import dimant.simulation.entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EntityService {
    private MapBoard mapBoard;
    private Map<Coordinate, Grass> mapGrass = new HashMap<>();


    public EntityService(MapBoard mapBoard) {
        this.mapBoard = mapBoard;
    }


    public Coordinate getCoordinateCreature(Creature creature) {
        for (Map.Entry<Coordinate, Entity> entry : mapBoard.getEntityMap().entrySet()) {
            String value = entry.getValue().getClass().getName();
            if (entry.getValue().equals(creature)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public Coordinate getNextCoordinate(Coordinate currentCoordinate, Creature targetEat) {
        SearchRoute searchRoute = new SearchRoute(mapBoard);
        return searchRoute.getNextCoordinate(currentCoordinate, targetEat);

    }

    public void saveGrassEntry(Coordinate coordinateGrass) {
        Grass grass = mapBoard.getGrass(coordinateGrass);
        mapGrass.put(coordinateGrass,grass);
    }
    public void deleteGrassMap(Grass grass){
        if(grass.getHP() <= 0) {
            mapBoard.deleteEntity(grass);
        }
    }



    public Grass getGrassMapGrass(Coordinate coordinate){
        return mapGrass.get(coordinate);
    }
    public boolean hasGrassByCoordinateInMapGrass(Coordinate coordinate){
        return mapGrass.containsKey(coordinate);
    }

    public void recoverGrassByCoordinate(Coordinate coordinate) {
        mapBoard.addEntityMap(coordinate,mapGrass.get(coordinate));
    }
}
