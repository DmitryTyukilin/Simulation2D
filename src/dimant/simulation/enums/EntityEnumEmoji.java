package dimant.simulation.enums;

public enum EntityEnumEmoji {
    WOLF("🐺"),
    HARE("🦓"),
    PLACE("🔳"),
    ROCK("🗻"),
    GRASS("🍀");



    private String emoji;

    EntityEnumEmoji(String emoji) {
        this.emoji = emoji;
    }

    public String getEmoji() {
        return emoji;
    }
}
