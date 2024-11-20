package main.java.org.simulation.entity;

import main.java.org.simulation.enums.EnumReaction;

public  abstract class Predator extends Creature{

    public abstract void attack (Herbivore herbivore);
    @Override
    public abstract EnumReaction makeMove(String typeNextMove);
}
