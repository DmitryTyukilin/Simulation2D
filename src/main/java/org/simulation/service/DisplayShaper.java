package main.java.org.simulation.service;

import main.java.org.simulation.Coordinate;
import main.java.org.simulation.WordMap;
import main.java.org.simulation.entity.Entity;
import main.java.org.simulation.enums.EntityEnumEmoji;
import main.java.org.simulation.intarfaces.DisplayedInConsole;

public class DisplayShaper implements DisplayedInConsole {
    private final WordMap wordMap;

    public DisplayShaper(WordMap wordMap) {
        this.wordMap = wordMap;
    }

        @Override
    public void displayInSquareView() {
        for (int x = 1; x < wordMap.getSizeMapHeight(); x++) {
            for (int y = 1; y < wordMap.getSizeMapWeight(); y++) {
                Coordinate coordinate = wordMap.getCoordinateByXY(x, y);
                Entity entity = wordMap.getEntityByCoordinate(coordinate);
                String emojiEntity = returnEmojiEntity(entity);
                System.out.print(" " + emojiEntity + "  ");
            }
            System.out.println(" ");
            System.out.println(" ");

        }
    }

    public String returnEmojiEntity(Entity entity) {
        return switch (entity.getClass().getSimpleName()) {
            case "Wolf" -> EntityEnumEmoji.WOLF.getEmoji();
            case "Hare" -> EntityEnumEmoji.HARE.getEmoji();
            case "Rock" -> EntityEnumEmoji.ROCK.getEmoji();
            case "Grass" -> EntityEnumEmoji.GRASS.getEmoji();
            default -> EntityEnumEmoji.NULL.getEmoji();
        };
    }
}
