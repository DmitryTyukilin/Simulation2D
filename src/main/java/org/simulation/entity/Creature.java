package main.java.org.simulation.entity;


import main.java.org.simulation.enums.EnumReaction;

import java.util.Objects;


public abstract class Creature extends Entity {

    public Creature() {
    }

    public abstract EnumReaction makeMove(String typeNextMove);

    @Override
    public boolean equals(Object o) {
        return this == o;

    }
    @Override
    public int hashCode() {
        return Objects.hash(this);
    }

}

