package main.java.org.simulation.service;


import main.java.org.simulation.Coordinate;
import main.java.org.simulation.MapBoard;
import main.java.org.simulation.entity.Entity;

import java.util.List;

public class ScannerType {
    private final MapBoard mapBoard;
    private final List<Entity> entities;


    public ScannerType(MapBoard mapBoard) {
        this.mapBoard = mapBoard;
        entities = mapBoard.getEntityList();
    }

    public String getEntityType(Coordinate coordinate) {
        String targetTypeEntity = getEntityByCoordinate(coordinate).getClass().getSimpleName();
        for (Entity entity : entities) {
            String typeEntity = entity.getClass().getSimpleName();
            if (typeEntity.equals(targetTypeEntity.getClass().getSimpleName())) {
                targetTypeEntity = typeEntity;
            }
        }
        return targetTypeEntity;
    }

    private Entity getEntityByCoordinate(Coordinate coordinate) {
        return mapBoard.getEntityMap(coordinate);
    }
}



