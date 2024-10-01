package dimant.simulation;

import dimant.simulation.service.ConsolePrinter;
import dimant.simulation.service.CoordinateService;
import dimant.simulation.service.EntityService;


import java.util.*;
import java.util.List;

public class Simulation {
    public static void main(String[] args) {
        Wolf bim = new Wolf("bim");
        Wolf reks = new Wolf("reks");
        Wolf bony = new Wolf("bony");
        Hare ger = new Hare("pert",10);
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
        mapBoard.addEntityMapByCoordinate(bony, 3, 1);
        mapBoard.addEntityMapByCoordinate(ger, 1, 3);


        System.out.println();


       // получаю текущую координату волка
        //запускается поиск пути до тровоядного
        printer.printMap();
        EntityService entityService = new EntityService(mapBoard);
        entityService.makeMoveWolf();
        entityService.makeMoveWolf();
        entityService.makeMoveWolf();
        entityService.makeMoveWolf();
        entityService.makeMoveWolf();
        entityService.makeMoveWolf();
        entityService.makeMoveWolf();
        printer.printMap();
        System.out.println();


    }


}


