package main.java.org.simulation.entity;


import main.java.org.simulation.enums.EnumReaction;
import main.java.org.simulation.intarfaces.Edible;

public class Wolf extends Predator {
    private int HP;
    private final static int DAMAGE = 3;

    public Wolf(int HP) {
        this.HP = HP;
    }

    @Override
    public EnumReaction makeMove(String typeNextMove) {
        return switch (typeNextMove) {
            case "Place" -> EnumReaction.GO;
            case "Hare" -> EnumReaction.ATTACK;
            case "Grass" -> EnumReaction.GO_GRASS;
            default -> EnumReaction.STOP;
        };
    }

    @Override
    public void attack(Herbivore edible) {
        edible.takeDamage(DAMAGE);
    }


}




