package dimant.simulation;

import dimant.simulation.entity.*;
import dimant.simulation.service.*;


import java.util.*;
import java.util.List;

public class Simulation {
    public static void main(String[] args) {
        Wolf wolf = new Wolf("wolf");
        Wolf wolf2 = new Wolf("wolf2");
        Hare hare = new Hare("hare", 10);
        Entity rock = new Rock();
        Entity rock2 = new Rock();
        Entity rock3 = new Rock();
        Entity rock4 = new Rock();
        Entity grass = new Grass();
        Entity grass2 = new Grass();



        MapBoard mapBoard = new MapBoard(5, 5);
        ConsolePrinter printer = new ConsolePrinter(mapBoard);

        mapBoard.addEntityMapByCoordinate(rock, 1, 1);
        mapBoard.addEntityMapByCoordinate(rock2, 2, 2);
        mapBoard.addEntityMapByCoordinate(rock3, 3, 2);
        mapBoard.addEntityMapByCoordinate(rock4, 1, 4);
        mapBoard.addEntityMapByCoordinate(wolf, 3, 1);
        mapBoard.addEntityMapByCoordinate(hare, 3, 2);
//        mapBoard.addEntityMapByCoordinate(grass, 3, 3);
//        mapBoard.addEntityMapByCoordinate(grass2, 4, 4);


        SearchRoute searchRoute = new SearchRoute(mapBoard);
        ScannerType scannerType = new ScannerType(mapBoard);
        EntityService entityService = new EntityService(mapBoard);
        Navigator navigator = new Navigator(searchRoute,scannerType,entityService);
        hare.setNavigator(navigator);
        hare.makeMove();



        printer.printMap();


        }
    }



