package main.java.org.simulation.entity;

import main.java.org.simulation.enums.EnumReaction;
import main.java.org.simulation.intarfaces.DamageDealer;
import main.java.org.simulation.intarfaces.Edible;

public  abstract class Predator extends Creature implements DamageDealer {

    public abstract void attack (Edible herbivore);
    @Override
    public abstract EnumReaction makeMove(String typeNextMove);
}
