package dimant.simulation;

public class Coordinate {
    private Integer x;
    private Integer y;
    private boolean isFree;

    public Coordinate(Integer x, Integer y, boolean isFree) {
        this.x = x;
        this.y = y;
        this.isFree = isFree;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}
