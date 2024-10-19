package dimant.simulation.enums;

public enum EnumReaction {
    GO("Place"),
    STOP("Wolf"),
    ATTACK("Hare"),
    EAT("Grass"),
    GO_GRASS("Grass"),
    NULL("Grass");


    private String type;

    EnumReaction(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

