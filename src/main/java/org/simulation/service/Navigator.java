package main.java.org.simulation.service;


import main.java.org.simulation.Coordinate;
import main.java.org.simulation.WordMap;
import main.java.org.simulation.entity.Creature;

public class Navigator {
    private final SearchRoute searchRoute;
    private final WordMap wordMap;

    public Navigator(WordMap wordMap) {
        searchRoute = new SearchRoute(wordMap);
        this.wordMap = wordMap;
    }

    public Coordinate getNextCoordinateEntity(Creature creature) {
        Coordinate currentCreatureCoordinate = wordMap.getCoordinateCreature(creature);
        return searchRoute.getCoordinateNextStep(currentCreatureCoordinate, creature);
    }


}
