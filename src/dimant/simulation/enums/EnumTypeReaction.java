package dimant.simulation.enums;

public enum EnumTypeReaction {
    HARE    ("Hare"),
    WOLF    ("Wolf"),
    PLACE   ("Place"),
    GRASS   ("Grass");


    private String type;

    EnumTypeReaction(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
