package dimant.simulation;

import dimant.simulation.entity.*;
import dimant.simulation.service.*;
import dimant.simulation.utils.RandomIntValue;

import java.util.Random;

public class Simulation {
    public static void main(String[] args) {
        Wolf wolf = new Wolf("wolf", 15);
        Wolf wolf2 = new Wolf("wolf2", 15);
        Hare hare = new Hare("hare", 10);
        Hare hare2 = new Hare("hare2", 15);
        Entity rock = new Rock();
        Entity rock2 = new Rock();
        Entity rock3 = new Rock();
        Entity rock4 = new Rock();
        Entity grass = new Grass();
        Entity grass2 = new Grass();
        Entity grass3 = new Grass();
        Entity grass4 = new Grass();
        Entity grass5 = new Grass();



        MapBoard mapBoard = new MapBoard(8, 8);
        ConsolePrinter printer = new ConsolePrinter(mapBoard);

        mapBoard.addEntityMapByCoordinate(rock, 1, 1);
//        mapBoard.addEntityMapByCoordinate(rock2, 2, 2);
//        mapBoard.addEntityMapByCoordinate(rock3, 3, 2);
//        mapBoard.addEntityMapByCoordinate(rock4, 1, 4);
        mapBoard.addEntityMapByCoordinate(wolf, 7, 3);
        mapBoard.addEntityMapByCoordinate(wolf2, 6, 7);
        mapBoard.addEntityMapByCoordinate(hare, 4, 1);
        mapBoard.addEntityMapByCoordinate(hare2, 4, 5);
        mapBoard.addEntityMapByCoordinate(grass, 1, 3);
        mapBoard.addEntityMapByCoordinate(grass2, 2, 1);
        mapBoard.addEntityMapByCoordinate(grass3, 4, 4);
        mapBoard.addEntityMapByCoordinate(grass4, 6, 5);
        mapBoard.addEntityMapByCoordinate(grass5, 4, 4);


        SearchRoute searchRoute = new SearchRoute(mapBoard);
        ScannerType scannerType = new ScannerType(mapBoard);
        EntityService entityService = new EntityService(mapBoard);
        SearchHerbivoreService searchHerbivoreService = new SearchHerbivoreService(searchRoute);
        Navigator navigator = new Navigator(searchRoute,scannerType,entityService, searchHerbivoreService);
        CreatureService creatureService = new CreatureService(mapBoard,navigator,entityService);

        printer.printMap();
        System.out.println();
        creatureService.makeMoveAllCreature();
        printer.printMap();
        creatureService.makeMoveAllCreature();
        System.out.println();
        printer.printMap();
        creatureService.makeMoveAllCreature();
        System.out.println();
        printer.printMap();
        creatureService.makeMoveAllCreature();
        System.out.println();
        printer.printMap();
        creatureService.makeMoveAllCreature();
        System.out.println();
        printer.printMap();
        creatureService.makeMoveAllCreature();
        System.out.println();
        printer.printMap();
        creatureService.makeMoveAllCreature();
        System.out.println();
        printer.printMap();
        creatureService.makeMoveAllCreature();
        printer.printMap();
        System.out.println();
        creatureService.makeMoveAllCreature();
        printer.printMap();
        System.out.println();
        creatureService.makeMoveAllCreature();
        printer.printMap();
        mapBoard.addEntityMapByCoordinate(new Place(), 4, 4);
        System.out.println();
        creatureService.makeMoveAllCreature();
        printer.printMap();
        System.out.println();
        creatureService.makeMoveAllCreature();
        printer.printMap();
        System.out.println();
        creatureService.makeMoveAllCreature();
        printer.printMap();
        System.out.println();
        creatureService.makeMoveAllCreature();
        printer.printMap();
        System.out.println();
        creatureService.makeMoveAllCreature();
        printer.printMap();
        System.out.println();
        creatureService.makeMoveAllCreature();
        printer.printMap();
        System.out.println();
        creatureService.makeMoveAllCreature();
        printer.printMap();
        System.out.println();
        creatureService.makeMoveAllCreature();
        printer.printMap();
        System.out.println();
        creatureService.makeMoveAllCreature();
        printer.printMap();
        System.out.println();
        creatureService.makeMoveAllCreature();
        printer.printMap();
        System.out.println();
        creatureService.makeMoveAllCreature();
        printer.printMap();








        }
    }



