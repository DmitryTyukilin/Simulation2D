package main.java.org.simulation.entity;


import main.java.org.simulation.enums.EnumReaction;
import main.java.org.simulation.intarfaces.Edible;

public class Hare extends Creature {

    private int HP;
    private final static int DAMAGE = 2;

    public Hare(int HP) {
        this.HP = HP;
    }


    @Override
    public EnumReaction makeMove(String typeNextMove) {
        return switch(typeNextMove) {
            case "Place" -> EnumReaction.GO;
            case "Grass" -> EnumReaction.EAT;
            default -> EnumReaction.STOP ;
        };
    }

    public int getHP() {
        return HP;
    }

    @Override
    public void attack(Edible edible) {
        edible.takeDamage(DAMAGE);
    }

    @Override
    public void takeDamage(int damage) {
        HP = HP - damage;
    }


}
