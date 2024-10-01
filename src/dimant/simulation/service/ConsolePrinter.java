package dimant.simulation.service;

import dimant.simulation.Coordinate;
import dimant.simulation.Entity;
import dimant.simulation.EntityEnum;
import dimant.simulation.MapBoard;

import java.util.List;

public class ConsolePrinter {
    MapBoard mapBoard;

    public ConsolePrinter(MapBoard mapBoard) {
        this.mapBoard = mapBoard;
    }

    public void printMap() {
        for (Entity entity : mapBoard.getEntityList()) {
            System.out.println(returnEmojiEntity(entity));
        }
    }

    public String returnEmojiEntity(Entity entity) {
        return switch (entity.getClass().getSimpleName()) {
            case "Wolf" -> EntityEnum.WOLF.getEmoji();
            case "Hare" -> EntityEnum.HARE.getEmoji();
            case "Place" -> EntityEnum.PLACE.getEmoji();
            case "Rock" -> EntityEnum.ROCK.getEmoji();
            default -> "Entity не обнаружено";
        };
    }
}
