package dimant.simulation.service;

import dimant.simulation.Coordinate;
import dimant.simulation.MapBoard;
import dimant.simulation.entity.*;
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
            if (typeEntity.equals(typeEntity.getClass().getSimpleName())) {
                targetTypeEntity = typeEntity;
            }
        }
        return targetTypeEntity;
    }

    private Entity getEntityByCoordinate(Coordinate coordinate) {
        return mapBoard.getEntityMap(coordinate);
    }
}



