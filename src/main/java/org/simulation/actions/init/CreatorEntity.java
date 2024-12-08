package main.java.org.simulation.actions.init;



import main.java.org.simulation.Coordinate;
import main.java.org.simulation.entity.*;
import main.java.org.simulation.intarfaces.IMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreatorEntity {
    IMap mapBoard;
    private final Integer numberWolf;
    private final Integer numberHare;
    private final Integer numberGrass;
    private final Integer numberRock;
    private final Integer hpMin;
    private final Integer hpMax;
    Random random = new Random();

    public CreatorEntity(IMap mapBoard) {
        this.mapBoard = mapBoard;
        this.numberWolf = 2;
        this.numberHare = 5;
        this.numberGrass = 2;
        this.numberRock = 5;
        this.hpMin = 1;
        this.hpMax = 8;
    }

    public void addEntityMap() {
        List<Entity> entityList = getEntityList();
        for (Entity entity : entityList) {
            List<Coordinate> freeCoordinates = mapBoard.getFreeListCoordinates();
            int randomPlaceCreature = random.nextInt(freeCoordinates.size());
            Coordinate freeCoordinateRandom = freeCoordinates.get(randomPlaceCreature);
            mapBoard.addEntityMap(freeCoordinateRandom, entity);
        }
    }

    private List<Entity> getEntityList() {
        List<Entity> entityList = new ArrayList<>();
        entityList.addAll(createListHare());
        entityList.addAll(createListWolf());
        entityList.addAll(createListGrass());
        entityList.addAll(createListRock());
        return entityList;
    }

    private List<Wolf> createListWolf() {
        List<Wolf> wolfs = new ArrayList<>();
        for (int i = 0; i < numberWolf; i++) {
            wolfs.add(new Wolf());
        }
        return wolfs;
    }

    private List<Hare> createListHare() {
        List<Hare> hares = new ArrayList<>();
        for (int i = 0; i < numberHare; i++) {
            int HP = hpMin + random.nextInt(hpMax);
            hares.add(new Hare(HP));
        }
        return hares;
    }

    private List<Grass> createListGrass() {
        List<Grass> grass = new ArrayList<>();
        for (int i = 0; i < numberGrass; i++) {
            grass.add(new Grass());
        }
        return grass;
    }

    private List<Rock> createListRock() {
        List<Rock> rocks = new ArrayList<>();
        for (int i = 0; i < numberRock; i++) {
            rocks.add(new Rock());
        }
        return rocks;
    }
}


