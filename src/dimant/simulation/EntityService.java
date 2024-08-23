package dimant.simulation;

import java.util.ArrayList;
import java.util.List;

public class EntityService {
    private List<Wolf> wolfs = new ArrayList<>();
    private EntityContaining mapBoard;
    private CoordinatesService coordinatesService;

    public EntityService(List<Wolf> wolfs) {
        this.wolfs.addAll(wolfs);
    }

    public EntityService(List<Wolf> wolfs, EntityContaining mapBoard, CoordinatesService coordinatesService) {
        this.wolfs.addAll(wolfs);
        this.mapBoard = mapBoard;
        this.coordinatesService = coordinatesService;
    }

    public List<Wolf> getWolfsList() {
        return wolfs;
    }

    public void addEntityMap() {
        Coordinate coordinate = coordinatesService.getIsFreeCoordinate();
        mapBoard.addEntity(coordinate, getWolf(coordinate));
    }

    public Wolf getWolf(Coordinate coordinate) {
        Wolf targetWolf = new Wolf("name");
        for (Wolf wolf : wolfs) {
            if (wolf.getCoordinate() == null) {
                targetWolf = wolf;
                targetWolf.setCoordinate(coordinate);
                break;
            }
        }
        return targetWolf;
    }


    public void addWolfs(List<Wolf> wolfs) {
        this.wolfs = wolfs;
    }

    public void addWolf(Wolf wolf) {
        this.wolfs.add(wolf);
    }

}
