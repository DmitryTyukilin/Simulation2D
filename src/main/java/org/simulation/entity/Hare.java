package main.java.org.simulation.entity;

import main.java.org.simulation.enums.EnumReaction;


public class Hare extends Herbivore {

    public Hare(int HP) {
        super(HP);
    }

    @Override
    public EnumReaction makeMove(String typeNextMove) {
        if (typeNextMove.equals("Entity")) {
            reduceHP();
            return EnumReaction.GO;
        } else if (typeNextMove.equals("Grass")) {
            eatGrass();
            return EnumReaction.EAT;
        } else if (typeNextMove.equals("Wolf")) {
            return EnumReaction.STOP;
        } else return EnumReaction.STOP;
    }

    private void reduceHP() {
        HP = HP - 1;
    }

    private void eatGrass() {
        HP = HP + 5;
    }

    @Override
    public void takeDamage(int damage) {
        super.HP = HP - damage;
    }
}
