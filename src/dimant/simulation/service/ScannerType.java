package dimant.simulation.service;

import dimant.simulation.Coordinate;
import dimant.simulation.MapBoard;
import dimant.simulation.entity.*;

import dimant.simulation.intarfaces.IRadar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScannerType {
    private MapBoard mapBoard;
    private List<Entity> entities = new ArrayList<>();
    Map<Coordinate, ? extends Entity> entityMap = new HashMap<>();

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



