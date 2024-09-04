package dimant.simulation;

import dimant.simulation.service.ConsolePrinter;
import dimant.simulation.service.CoordinateService;
import dimant.simulation.service.SearchRoute;


import java.util.*;
import java.util.List;

public class Simulation {
    public static void main(String[] args) {
        Wolf bim = new Wolf("bim");
        Wolf reks = new Wolf("reks");
        Wolf bony = new Wolf("bony");
        Hare ger = new Hare("pert");
        Entity rock = new Rock();
        Entity rock2 = new Rock();
        Entity rock3 = new Rock();
        Entity rock4 = new Rock();

        List<Wolf> wolfsList = Arrays.asList(bim, reks, bony);

        CoordinateService coordinateService = new CoordinateService();
        coordinateService.creatCoordinates(5, 5);
        MapBoard mapBoard = new MapBoard(coordinateService);
        ConsolePrinter printer = new ConsolePrinter(mapBoard);

        mapBoard.addEntityMapByCoordinate(rock, 1, 1);
        mapBoard.addEntityMapByCoordinate(rock2, 2, 2);
        mapBoard.addEntityMapByCoordinate(rock3, 3, 2);
        mapBoard.addEntityMapByCoordinate(rock4, 1, 4);
        mapBoard.addEntityMapByCoordinate(bony, 2, 1);
        mapBoard.addEntityMapByCoordinate(ger, 2, 3);


        System.out.println();
        SearchRoute searchRoute = new SearchRoute(coordinateService.getListCoordinates(), mapBoard);
        searchRoute.searchHare(mapBoard.getWolfCoordinate());
        printer.printEntity();

        System.out.println();


    }


}


