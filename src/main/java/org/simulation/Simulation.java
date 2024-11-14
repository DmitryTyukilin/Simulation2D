package main.java.org.simulation;


import main.java.org.simulation.actions.init.CreatorEntity;
import main.java.org.simulation.service.*;



public class Simulation {
    private MapBoard mapBoard;
    ConsolePrinter printer;
    SearchRoute searchRoute;
    ScannerType scannerType;
    EntityService entityService;
    Navigator navigator;
    CreatureService creatureService;
    MoveCounter moveCounter;

    public Simulation() {
        this.mapBoard = new MapBoard(8, 8);
        this.printer = new ConsolePrinter(mapBoard);
        this.searchRoute = new SearchRoute(mapBoard);
        this.scannerType = new ScannerType(mapBoard);
        this.entityService = new EntityService(mapBoard);
        this.navigator = new Navigator(searchRoute, scannerType, entityService);
        this.creatureService = new CreatureService(mapBoard, navigator, entityService);
        this.moveCounter = new MoveCounter();
    }


    public void startSimulation() {
        CreatorEntity creatorEntity = new CreatorEntity(mapBoard);
        creatorEntity.addEntityMap();
        try {
            while (entityService.hasHareOnMapBoard()) {
                creatureService.makeMoveAllCreature();
                moveCounter.recordMove();
                printer.printMap();
                Thread.sleep(1500);

            }
            System.out.println("Симуляция окончена");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}



