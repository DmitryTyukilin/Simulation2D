package dimant.simulation.entity;

public class Hare extends Creature {
    private String name;
    private int HP;

    public Hare(String name, int HP) {
        this.name = name;
        this.HP = HP;
    }

    @Override
    public String toString() {
        return "Herbivore{" +
                "name='" + name + '\'' +
                '}';
    }

    public void takeDamage(int damage) {
        HP = HP - damage;
        System.out.println("Получен урон -" + damage + " единиц");
    }
}
