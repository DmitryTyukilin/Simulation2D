package main.java.org.simulation.service;


import main.java.org.simulation.Coordinate;
import main.java.org.simulation.WordMap;
import main.java.org.simulation.entity.Creature;

public class Navigator {
    private final SearchRoute searchRoute;
    private final WordMap wordMap;
    private Coordinate nextCoordinateEntity;

    public Navigator(WordMap wordMap) {
        searchRoute = new SearchRoute(wordMap);
        this.wordMap = wordMap;
    }


//    public String getTypeEntityNextStep(Creature creature) {
//        Coordinate coordinateNextStep = nextCoordinateAboutCurrent(creature);
//        Entity entityNextStep = mapBoard.getEntityByCoordinate(coordinateNextStep);
//        if (entityNextStep != null) {
//            nextCoordinateEntity = coordinateNextStep;
//            return entityNextStep.getClass().getSimpleName();
//        } else {
//            return null;
//        }
//    }



    public Coordinate getNextCoordinateEntity(Creature creature) {
        Coordinate currentCreatureCoordinate = wordMap.getCoordinateCreature(creature);
        return searchRoute.getCoordinateNextStep(currentCreatureCoordinate, creature);
    }

//    private Coordinate nextCoordinateAboutCurrent() {
//        Coordinate currentCreatureCoordinate = wordMap.getCoordinateCreature(creature);
//        return searchRoute.getCoordinateNextStep(currentCreatureCoordinate, creature);
//    }

}
