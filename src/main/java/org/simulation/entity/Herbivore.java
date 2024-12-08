package main.java.org.simulation.entity;

import main.java.org.simulation.enums.EnumReaction;
import main.java.org.simulation.intarfaces.Edible;


public abstract class Herbivore extends Creature implements Edible {
    protected int HP;

    public Herbivore(int HP) {
        this.HP = HP;
    }

    public abstract void takeDamage(int damage);

    public int getHP() {
        return HP;
    }

}

