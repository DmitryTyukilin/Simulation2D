package dimant.simulation.entity;

import dimant.simulation.enums.EnumReaction;
import dimant.simulation.intarfaces.Edible;

public class Hare extends Creature {
    private String name;
    private int HP;
    private final static int DAMAGE = 2;

    public Hare(String name, int HP) {
        this.name = name;
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

    @Override
    public void attack(Edible edible) {
        edible.takeDamage(DAMAGE);
        HP = HP + edible.repayHealth();
    }

    @Override
    public void takeDamage(int damage) {
        HP = HP - damage;
        System.out.println(HP+ "травоядное атаковано");
    }

    @Override
    public Integer repayHealth() {
        return HP - 1;
    }

    @Override
    public String toString() {
        return "Herbivore{" +
                "name='" + name + '\'' +
                '}';
    }
}
