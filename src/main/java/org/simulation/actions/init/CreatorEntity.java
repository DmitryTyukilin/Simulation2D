package main.java.org.simulation.actions.init;



import main.java.org.simulation.Coordinate;
import main.java.org.simulation.WordMap;
import main.java.org.simulation.actions.Action;
import main.java.org.simulation.entity.*;
import main.java.org.simulation.utils.PercentCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreatorEntity extends Action {

    private final int numberWolf;
    private final int numberHare;
    private final int numberGrass;
    private final int numberRock;
    private final int hpMin;
    private final int hpMax;
    private final int SPEED = 2;
    Random random = new Random();

    @Override
    public void execute() {
        List<Entity> entityList = getEntityList();
        for (Entity entity : entityList) {
            List<Coordinate> freeCoordinates = wordMap.getFreeListCoordinates();
            int randomPlaceCreature = random.nextInt(freeCoordinates.size());
            Coordinate freeCoordinateRandom = freeCoordinates.get(randomPlaceCreature);
            wordMap.addEntityMap(freeCoordinateRandom, entity);
        }
    }

    public CreatorEntity(WordMap mapWord) {
        super(mapWord);
        int mapWordSize = mapWord.getSizeMapHeight() * mapWord.getSizeMapWeight();
        this.numberWolf = PercentCalculator.numberFromPercent(mapWordSize, 5);
        this.numberHare = PercentCalculator.numberFromPercent(mapWordSize, 8);
        this.numberGrass = PercentCalculator.numberFromPercent(mapWordSize, 7);
        this.numberRock = PercentCalculator.numberFromPercent(mapWordSize, 5);
        this.hpMin = 4;
        this.hpMax = 15;
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
            wolfs.add(new Wolf(SPEED));
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


