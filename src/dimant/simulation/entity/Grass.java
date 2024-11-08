package dimant.simulation.entity;

import dimant.simulation.intarfaces.Edible;

public class Grass extends Entity implements Edible {


    public Grass() {

    }

    @Override
    public void takeDamage(int damage) {

    }





    @Override
    public Integer repayHealth() {
        return 1;
    }
}
