package dimant.simulation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Simulation {
    public static void main(String[] args) {
        Wolf bim = new Wolf("bim");
        Wolf reks = new Wolf("reks");
        Wolf bony = new Wolf("bony");
        List<Wolf> wolfs = new ArrayList<>();
        wolfs.add(bim);
        wolfs.add(reks);
        wolfs.add(bony);

        CoordinatesService coordinatesService = new CoordinatesService();
        MapBoard mapBoard = new MapBoard();
        EntityService entityService = new EntityService(wolfs,mapBoard,coordinatesService);
        entityService.addEntityMap();
        entityService.addEntityMap();
        entityService.addEntityMap();








    }
}
