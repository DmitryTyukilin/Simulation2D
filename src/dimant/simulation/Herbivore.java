package dimant.simulation;

public class Herbivore extends Entity{
    private String name;

    public Herbivore(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Herbivore{" +
                "name='" + name + '\'' +
                '}';
    }
}
