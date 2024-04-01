package oop_image;

public class Transformer {
    int HP;


    public Transformer(int HP) {
        this.HP = HP;
    }
    public void heal() {
        this.HP +=5;
    }
    public void takeDamage(int damage) {
        this.HP -= damage;
    }
    public int attack (Gun gunOptimus) {
        return gunOptimus.damage;
    }


@Override
public String toString() {
    return "Transformer{" +
            "HP=" + HP +
            '}';
}

}
