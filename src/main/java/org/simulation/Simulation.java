package main.java.org.simulation;


import main.java.org.simulation.actions.init.CreatorEntity;
import main.java.org.simulation.service.*;



public class Simulation {
    private final MapBoard mapBoard;
    ConsolePrinter printer;
    SearchRoute searchRoute;
    EntityService entityService;
    Navigator navigator;
    CreatureService creatureService;
    MoveCounter moveCounter;

    public Simulation() {
        this.mapBoard = new MapBoard(8, 8);
        this.printer = new ConsolePrinter(new DisplayShaper(mapBoard));
        this.searchRoute = new SearchRoute(mapBoard);
        this.entityService = new EntityService(mapBoard);
        this.navigator = new Navigator(searchRoute, mapBoard, entityService);
        this.creatureService = new CreatureService(mapBoard, navigator, entityService);
        this.moveCounter = new MoveCounter();
    }

    public void startSimulation() {
        CreatorEntity creatorEntity = new CreatorEntity(mapBoard);
        creatorEntity.addEntityMap();
        mapBoard.setHerbivoresListEntityService();
        mapBoard.setGrassListEntityService();
        System.out.println("Исходные позиции");
        printer.printMap();

            while (mapBoard.hasHerbivoreMapBoard()) {
                creatureService.makeMoveAllCreature();
                moveCounter.recordMove();
                printer.printMap();


            }
            System.out.println("Симуляция окончена");
    }
}



