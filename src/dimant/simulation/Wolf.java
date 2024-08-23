package dimant.simulation;

public class Wolf {
    private Coordinate coordinate;
    private final String name;

    public Wolf(String name) {
        this.name = name;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
