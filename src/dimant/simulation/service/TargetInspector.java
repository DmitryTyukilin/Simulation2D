package dimant.simulation.service;

import dimant.simulation.Coordinate;
import dimant.simulation.entity.Creature;

import java.util.ArrayList;
import java.util.List;

public class TargetInspector {
    private List<Coordinate> targets = new ArrayList<>();
    private List<Coordinate> targetsLost = new ArrayList<>();


    public TargetInspector() {

    }

    public boolean addNewCoordinate(Coordinate targetCoordinate) {
        return isCoordinateTarget(targetCoordinate);
    }


    private boolean isCoordinateTarget(Coordinate coordinate) {
        boolean result;
        if (targets.isEmpty()) {
            targets.add(coordinate);
            result = true;
        } else if (targetsLost.contains(coordinate)) {
            result = false;
        } else if (targets.contains(coordinate)) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }



    public void addCoordinateTargetLost(Coordinate coordinate) {
        Coordinate coordinateIsTargets = targets.get(0);
        targets.remove(coordinateIsTargets);
        targetsLost.add(coordinateIsTargets);
        targets.add(coordinate);
        }
    }
