package oop_image;

public class Gun {
    int ammo_count;
    int damage;

    public Gun(int damage) {
        this.damage = 2;
        this.reload(); // перезарядили пушку
    }

    public void fire() {
        this.ammo_count -= 1; // стреляем расходуем боеприпас
    }
    public int damage(){
        return this.damage;
    }

    public void reload() {
        this.ammo_count = 10;
    }
}
