package main.java.org.simulation.enums;

public enum EntityEnumEmoji {
    WOLF    ("🐺"),
    HARE    ("🦓"),
    PLACE   ("🔳"),
    ROCK    ("🗻"),
    GRASS   ("🍀");



    private final String emoji;

    EntityEnumEmoji(String emoji) {
        this.emoji = emoji;
    }

    public String getEmoji() {
        return emoji;
    }
}
