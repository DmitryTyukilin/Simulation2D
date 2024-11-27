package main.java.org.simulation.service;

import main.java.org.simulation.Coordinate;
import main.java.org.simulation.MapBoard;
import main.java.org.simulation.entity.Entity;
import main.java.org.simulation.enums.EntityEnumEmoji;
import main.java.org.simulation.intarfaces.DisplayedInConsole;

import java.util.Map;

public class DisplayShaper implements DisplayedInConsole {
    private final MapBoard mapBoard;

    public DisplayShaper(MapBoard mapBoard) {
        this.mapBoard = mapBoard;
    }

    @Override
    public void displayInSquareView() {
        for (int x = 1; x < mapBoard.getSizeMapHeight(); x++) {
            for (int y = 1; y < mapBoard.getSizeMapWeight(); y++) {
                Coordinate coordinate = mapBoard.getCoordinateByXY(x, y);
                Entity entity = mapBoard.getEntityByCoordinate(coordinate);
                String emojiEntity = returnEmojiEntity(entity);
                System.out.print(emojiEntity + " ");
            }
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
