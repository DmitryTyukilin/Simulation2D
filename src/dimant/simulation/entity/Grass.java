package dimant.simulation.entity;

import dimant.simulation.intarfaces.Edible;

public class Grass extends Entity implements Edible {
    private int HP;

    public Grass() {
        this.HP = 15;
    }

    @Override
    public void takeDamage(int damage) {
        HP = HP - damage;
        System.out.println(HP + " трава откусана" );
    }

    public int getHP() {
        return HP;
    }



    @Override
    public Integer repayHealth() {
        return 1;
    }
}
