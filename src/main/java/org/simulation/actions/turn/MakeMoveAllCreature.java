package main.java.org.simulation.actions.turn;

import main.java.org.simulation.WordMap;
import main.java.org.simulation.actions.Action;
import main.java.org.simulation.entity.Creature;
import main.java.org.simulation.entity.Entity;
import main.java.org.simulation.entity.Herbivore;
import main.java.org.simulation.entity.Predator;

import java.util.ArrayList;
import java.util.List;

public class MakeMoveAllCreature extends Action {


    public MakeMoveAllCreature(WordMap wordMap) {
        super(wordMap);
    }

    @Override
    public void execute() {
        List<Creature> creatures = new ArrayList<>();

        List<Entity> entities = new ArrayList<>(wordMap.getEntityList());
        for (Entity entity : entities) {
            if (entity instanceof Predator) {
                creatures.add((Predator) entity);
            } else if (entity instanceof Herbivore) {
                creatures.add((Herbivore) entity);
            }
        }
        for (Creature creature : creatures) {
            creature.makeMove(wordMap);
        }
    }

}
