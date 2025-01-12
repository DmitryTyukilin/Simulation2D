package main.java.org.simulation.entity;

import main.java.org.simulation.Coordinate;
import main.java.org.simulation.WordMap;
import main.java.org.simulation.intarfaces.Edible;


public class Wolf extends Predator {

    private final static int DAMAGE = 3;

    public Wolf(int speed) {
        super();
        this.speed = speed;
    }

    @Override
    public void attack(Edible edible) {
        edible.takeDamage(DAMAGE);
    }

    @Override
    protected void eat(Coordinate coordinateEat, WordMap wordMap) {
        Entity entity = wordMap.getEntityByCoordinate(coordinateEat);
        if (entity instanceof Herbivore) {
            attack((Herbivore) entity);
        }
    }
}




