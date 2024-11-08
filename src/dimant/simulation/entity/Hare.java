package dimant.simulation.entity;

import dimant.simulation.enums.EnumReaction;
import dimant.simulation.intarfaces.Edible;

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

    public void setHP(int HP) {
        this.HP = HP;
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

}
