package main.java.org.simulation.enums;

public enum EnumReaction {
    GO("Null"),
    STOP("Wolf"),
    ATTACK("Hare"),
    EAT("Grass"),
    GO_GRASS("Grass");



    private final String type;

    EnumReaction(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

