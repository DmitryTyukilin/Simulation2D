package oop_image;

public class Zombi {
    int HP;
    int damage;

    public Zombi(int HP, int damage) {
        this.HP = HP;
        this.damage = 1;
    }

    public int attack(Gun gunOptimus) {
        return gunOptimus.damage;
    }

    public void takeDamage(int damage) {
        this.HP -= damage;
    }

    public void dead() {
        if (this.HP <= 0) {
            System.out.println("Zombi dead");
        }
    }
}
