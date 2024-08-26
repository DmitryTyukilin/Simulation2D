package dimant.simulation;


import dimant.simulation.service.CoordinateService;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class MapBoard {
    private List<Coordinate> coordinates;
    private Map<Coordinate, Wolf> wolfs = new HashMap<>();
    private Map<Coordinate, Entity> entity = new HashMap<>();


    public MapBoard(CoordinateService coordinateService) {
        this.coordinates = coordinateService.getListCoordinates();
    }

    public void addWolfsOnMapOnEvenCoordinates(List<Wolf> wolfs, CoordinateService coordinateService) {
        Queue<Coordinate> coordinatesEven = coordinateService.getEvenCoordinates();
        for (Wolf wolf : wolfs) {
            if (!coordinatesEven.isEmpty()) {
                coordinatesEven.peek().setEntity(wolf);
                this.wolfs.put(coordinatesEven.remove(), wolf);
            }
        }
    }
}






