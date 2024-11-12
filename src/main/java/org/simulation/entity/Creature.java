package main.java.org.simulation.entity;


import main.java.org.simulation.enums.EnumReaction;
import main.java.org.simulation.intarfaces.Edible;

public abstract class Creature extends Entity implements Edible {

    public Creature() {
    }

    public abstract EnumReaction makeMove(String typeNextMove);
    public abstract void attack(Edible edible);

    @Override
    public abstract void takeDamage(int damage);



}

