package main.java.org.simulation;


import main.java.org.simulation.actions.init.CreatorEntity;
import main.java.org.simulation.actions.turn.GrassCreater;
import main.java.org.simulation.actions.turn.CleanerDeadHerbivore;
import main.java.org.simulation.actions.turn.MakeMoveAllCreature;
import main.java.org.simulation.actions.turn.StatusSimulationChecker;
import main.java.org.simulation.service.*;


public class Simulation {
    private final WordMap wordMap;
    private final CreatorEntity creatorEntity;
    private final ConsolePrinter printer;
    private final MoveCounter moveCounter;
    private final GrassCreater grassCreater;
    private final CleanerDeadHerbivore cleanerDeadHerbivore;
    private final StatusSimulationChecker statusSimulationChecker;

    public Simulation() {
        this.wordMap = new WordMap(8, 8);
        this.creatorEntity = new CreatorEntity(wordMap);
        creatorEntity.execute();
        this.printer = new ConsolePrinter(new DisplayShaper(wordMap));
        this.grassCreater = new GrassCreater(wordMap);
        this.moveCounter = new MoveCounter();
        this.cleanerDeadHerbivore = new CleanerDeadHerbivore(wordMap);
        this.statusSimulationChecker = new StatusSimulationChecker(wordMap);

    }

    public void startSimulation() {
        MakeMoveAllCreature makeMoveAllCreature = new MakeMoveAllCreature(wordMap);
        System.out.println("Исходные позиции");
        printer.printMap();
        while (!statusSimulationChecker.isGameOver()) {
            makeMoveAllCreature.execute();
            cleanerDeadHerbivore.execute();
            grassCreater.execute();
            printer.printMap();
            moveCounter.recordMove();

        }
        System.out.println("Cиммуляция окончена");

    }

}






