package dimant.simulation;

public class Wolf extends Entity{

    private final String name;

    public Wolf(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Wolf{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }
}


