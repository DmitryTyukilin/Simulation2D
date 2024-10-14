package dimant.simulation.entity;

import dimant.simulation.entity.Creature;
import dimant.simulation.entity.Hare;

public class Wolf extends Creature {
    private Integer energy;
    private final String name;

    public Wolf(String name) {
        this.name = name;
        this.energy = 5;
    }

    @Override
    public String toString() {
        return "Wolf{" +
                "name='" + name + '\'' +
                '}';
    }

    public boolean canMove(){
        boolean haveEnergy = true;
        if (energy <= 0) {
            haveEnergy = false;
        }
        return haveEnergy;
    }
    public void attack(Hare hare) {
        hare.takeDamage(5);
    }
}


