package main.java.org.simulation.entity;
import main.java.org.simulation.enums.EnumReaction;


public class Hare extends Herbivore {

    public Hare(int HP) {
        super(HP);
    }

    @Override
    public EnumReaction makeMove(String typeNextMove) {
        return switch(typeNextMove) {
            case "null" -> EnumReaction.GO;
            case "Grass" -> EnumReaction.EAT;
            default -> EnumReaction.STOP ;
        };
    }

    @Override
    public void takeDamage(int damage) {
        super.HP = HP - damage;
    }
}
