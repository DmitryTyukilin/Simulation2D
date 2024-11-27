package main.java.org.simulation.enums;

public enum EntityEnumEmoji {
    WOLF    ("🐺"),
    HARE    ("🦓"),
    NULL   ("🔳"),
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
