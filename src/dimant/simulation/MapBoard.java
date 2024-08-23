package dimant.simulation;


import java.util.HashMap;
import java.util.Map;

public class MapBoard implements EntityContaining {
    private Map<Coordinate, Wolf> map = new HashMap<>();
    private final Integer MAPBOARDSIZE = 15;
    private EntityService entityService;
    private CoordinatesService coordinatesService;

    public MapBoard() {
    }

    public MapBoard(EntityService entityService, CoordinatesService coordinatesService) {
        this.entityService = entityService;
        this.coordinatesService = coordinatesService;
    }



    @Override
    public void addEntity(Coordinate coordinate, Wolf wolf) {
        map.put(coordinate, wolf);
    }

//    public void addEntityMap() {
//        Coordinate coordinate = coordinatesService.getIsFreeCoordinate();
//        map.put(coordinate,entityService.getWolf(coordinate));
//    }

    public void setEntityService(EntityService entityService) {
        this.entityService = entityService;
    }
}
