package main.java.org.simulation;


import main.java.org.simulation.actions.init.CreatorEntity;
import main.java.org.simulation.actions.init.GrassCreater;
import main.java.org.simulation.actions.turn.MakeMoveAllCreature;
import main.java.org.simulation.service.*;


public class Simulation {
    private final WordMap wordMap;
    ConsolePrinter printer;
    SearchRoute searchRoute;
    EntityService entityService;
    Navigator navigator;
    CreatureService creatureService;
    MoveCounter moveCounter;
    GrassCreater grassCreater;

    public Simulation() {
        this.wordMap = new WordMap(8, 8);
        this.printer = new ConsolePrinter(new DisplayShaper(wordMap));
        this.searchRoute = new SearchRoute(wordMap);
        this.entityService = new EntityService(wordMap);
        this.navigator = new Navigator(wordMap);
        this.grassCreater = new GrassCreater(wordMap);
        this.creatureService = new CreatureService(wordMap, navigator, entityService, grassCreater);
        this.moveCounter = new MoveCounter();
    }

    public void startSimulation() {
        CreatorEntity creatorEntity = new CreatorEntity(wordMap);
        creatorEntity.addEntityMap();
        wordMap.setHerbivoresListEntityService();
        wordMap.setGrassListEntityService();
        MakeMoveAllCreature makeMoveAllCreature = new MakeMoveAllCreature(wordMap);
        System.out.println("Исходные позиции");
        printer.printMap();
        while (wordMap.hasHerbivoreMapBoard()) {
            makeMoveAllCreature.execute();
            printer.printMap();
            System.out.println();
            moveCounter.recordMove();
        }

    }

}






