package dimant.simulation.actions.init;

import dimant.simulation.Coordinate;
import dimant.simulation.entity.*;
import dimant.simulation.intarfaces.IMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreatorEntity {
    IMap mapBoard;
    private final Integer numberWolf;
    private final Integer numberHare;
    private final Integer numberGrass;
    private final Integer numberRock;
    final Integer hpMin;
    final Integer hpMax;
    Random random = new Random();

    public CreatorEntity(IMap mapBoard) {
        this.mapBoard = mapBoard;
        this.numberWolf = 3;
        this.numberHare = 4;
        this.numberGrass = 5;
        this.numberRock = 3;
        this.hpMin = 5;
        this.hpMax = 10;
    }

    public void addEntityMap() {
        List<Entity> entityList = getEntityList();
        for (Entity entity : entityList) {
            int randomPlaceCreature = random.nextInt(mapBoard.getFreeListCoordinates().size());
            List<Coordinate> freeCoordinates = mapBoard.getFreeListCoordinates();
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
            int HP = random.nextInt(hpMin, hpMax);
            wolfs.add(new Wolf(HP));
        }
        return wolfs;
    }

    private List<Hare> createListHare() {
        List<Hare> hares = new ArrayList<>();
        for (int i = 0; i < numberHare; i++) {
            int HP = random.nextInt(hpMin, hpMax);
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


