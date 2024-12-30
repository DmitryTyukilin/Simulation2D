package main.java.org.simulation.actions.turn;

import main.java.org.simulation.WordMap;
import main.java.org.simulation.actions.Action;
import main.java.org.simulation.entity.Entity;
import main.java.org.simulation.entity.Herbivore;

public class StatusSimulationChecker  {

   private final WordMap wordMap;

    public StatusSimulationChecker(WordMap wordMap) {
       this.wordMap = wordMap;
    }

    public boolean isGameOver(){
        int herbivores = 0;
        for(Entity entity : wordMap.getEntityList()) {
            if (entity instanceof Herbivore) {
                herbivores ++;
            }
        }
        return herbivores <= 0;
    }
}
