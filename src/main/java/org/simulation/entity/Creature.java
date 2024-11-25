package main.java.org.simulation.entity;


import main.java.org.simulation.enums.EnumReaction;


public abstract class Creature extends Entity {

    public Creature() {
    }

    public abstract EnumReaction makeMove(String typeNextMove);


}

