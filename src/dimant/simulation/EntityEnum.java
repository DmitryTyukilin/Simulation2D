package dimant.simulation;

public enum EntityEnum {
    WOLF("🐺"),
    HARE("🦓"),
    PLACE("🔳");
    private String emoji;

    EntityEnum(String emoji) {
        this.emoji = emoji;
    }

    public String getEmoji() {
        return emoji;
    }
}
