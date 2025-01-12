package main.java.org.simulation;


import main.java.org.simulation.actions.init.CreatorEntity;
import main.java.org.simulation.actions.turn.ResourcesCreater;
import main.java.org.simulation.actions.turn.CleanerDeadHerbivore;
import main.java.org.simulation.actions.turn.MakeMoveAllCreature;
import main.java.org.simulation.service.*;


public class Simulation implements Runnable {
    private final WordMap wordMap;
    private final CreatorEntity creatorEntity;
    private final ConsolePrinter printer;
    private final MoveCounter moveCounter;
    private final ResourcesCreater grassCreater;
    private final CleanerDeadHerbivore cleanerDeadHerbivore;
    private volatile boolean paused = false;
    private volatile boolean stop = false;


    public Simulation() {
        this.wordMap = new WordMap(10, 10);
        this.creatorEntity = new CreatorEntity(wordMap);
        creatorEntity.execute();
        this.printer = new ConsolePrinter(new DisplayShaper(wordMap));
        this.grassCreater = new ResourcesCreater(wordMap, creatorEntity);
        this.moveCounter = new MoveCounter();
        this.cleanerDeadHerbivore = new CleanerDeadHerbivore(wordMap);

    }

    public void run() {
        MakeMoveAllCreature makeMoveAllCreature = new MakeMoveAllCreature(wordMap);
        while (!stop) {
            if (!paused) {
                try {
                    makeMoveAllCreature.execute();
                    cleanerDeadHerbivore.execute();
                    grassCreater.execute();
                    printer.printMap();
                    moveCounter.recordMove();
                    System.out.println("Введите '1' для паузы, '2' для продолжения или '3' для выхода:");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        paused = false;
    }

    public void stop() {
        stop = true;
    }
}









