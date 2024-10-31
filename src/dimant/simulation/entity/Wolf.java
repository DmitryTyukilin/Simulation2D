package dimant.simulation.entity;

import dimant.simulation.enums.EnumReaction;
import dimant.simulation.intarfaces.Edible;

public class Wolf extends Creature {
    private Integer energy;
    private int HP;
    private final String name;
    private final static int DAMAGE = 3;
    private final static int SPEED = 2;

    public Wolf(String name, int HP) {
        this.name = name;
        this.energy = 5;
        this.HP = HP;
    }

    @Override
    public String toString() {
        return "Wolf{" +
                "name='" + name + '\'' +
                '}';
    }

    public boolean canMove() {
        boolean haveEnergy = true;
        if (energy <= 0) {
            haveEnergy = false;
        }
        return haveEnergy;
    }


    @Override
    public EnumReaction makeMove(String typeNextMove) {
        return switch(typeNextMove) {
            case "Place" -> EnumReaction.GO;
            case "Hare" -> EnumReaction.ATTACK;
            case "Grass" -> EnumReaction.GO_GRASS;
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

    }

    @Override
    public Integer repayHealth() {
        return HP - 2;
    }
}


