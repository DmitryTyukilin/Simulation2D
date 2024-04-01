package oop_image;

public class Start {
    public static void main(String[] args) {
        Transformer optimus = new Transformer(10);
        Zombi zombi = new Zombi(10, 1);

        while (optimus.HP > 0 && zombi.HP != 0) {
            optimus.takeDamage(zombi.damage);
            System.out.println("Трансформер атакован остаток здоровья + " + optimus.HP);
            if (optimus.HP < 3) {
                while (zombi.HP > 0) {
                    Gun gun = new Gun(2);
                    zombi.takeDamage(optimus.attack(gun));
                    System.out.println("Зомби атакован остаток здоровья " + zombi.HP);
                    if (zombi.HP <= 0) {
                        zombi.dead();
                    }
                }

            }
        }
    }
}
