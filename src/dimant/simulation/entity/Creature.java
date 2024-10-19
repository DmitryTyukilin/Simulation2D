package dimant.simulation.entity;


import dimant.simulation.enums.EnumReaction;
import dimant.simulation.enums.EnumTypeReaction;
import dimant.simulation.intarfaces.Edible;
//import dimant.simulation.service.SearchRoute;

public abstract class Creature extends Entity implements Edible {
    private Integer energy;

    public Creature() {
    }

    public abstract EnumReaction makeMove(String typeNextMove);
    public abstract void attack(Edible edible);

    @Override
    public abstract void takeDamage(int damage);

    @Override
    public abstract Integer repayHealth();

}

