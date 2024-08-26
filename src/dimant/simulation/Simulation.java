package dimant.simulation;

import dimant.simulation.service.CoordinateService;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Simulation {
    public static void main(String[] args) {
        Wolf bim = new Wolf("bim");
        Wolf reks = new Wolf("reks");
        Wolf bony = new Wolf("bony");
        Entity ger = new Hare("pert");
        List<Wolf> wolfsList = new ArrayList<>();
        wolfsList = Arrays.asList(bim,reks,bony);

        CoordinateService coordinateService = new CoordinateService();
        coordinateService.creatCoordinates(6,6);
        MapBoard mapBoard = new MapBoard(coordinateService);
        mapBoard.addWolfsOnMapOnEvenCoordinates(wolfsList,coordinateService);
        System.out.println();



    }


}


