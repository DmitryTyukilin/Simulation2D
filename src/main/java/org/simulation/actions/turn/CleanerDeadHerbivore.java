package main.java.org.simulation.actions.turn;

import main.java.org.simulation.WordMap;
import main.java.org.simulation.actions.Action;
import main.java.org.simulation.entity.Entity;
import main.java.org.simulation.entity.Herbivore;


public class CleanerDeadHerbivore extends Action {


    public CleanerDeadHerbivore(WordMap wordMap) {
        super(wordMap);
    }

    @Override
    public void execute() {
        for(Entity entity : wordMap.getEntityList()) {
            if (entity instanceof Herbivore) {
                Herbivore herbivore = (Herbivore) entity;
                if (isHerbivoreDead(herbivore)) {
                    wordMap.deleteEntityMap(herbivore);
                }
            }
        }
    }

    private boolean isHerbivoreDead(Herbivore hare) {
        return hare.getHP() <= 0;
    }
}
