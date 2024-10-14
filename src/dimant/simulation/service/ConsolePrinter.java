package dimant.simulation.service;

import dimant.simulation.Coordinate;
import dimant.simulation.entity.Entity;
import dimant.simulation.enums.EntityEnumEmoji;
import dimant.simulation.MapBoard;

public class ConsolePrinter {
    MapBoard mapBoard;

    public ConsolePrinter(MapBoard mapBoard) {
        this.mapBoard = mapBoard;
    }

    public void printMap() {

        for (int x = 1; x < mapBoard.getSizeMapHeight(); x++) {
            int counter = mapBoard.getSizeMapWeight() - 1;
            for (int y = 1; y < mapBoard.getEntityList().size(); y++) {
                Coordinate coordinate = mapBoard.getCoordinateByXY(x, y);
                Entity entity = mapBoard.getEntityByCoordinate(coordinate);
                String emojiEntity = returnEmojiEntity(entity);
                System.out.print(emojiEntity + " ");
                counter--;
                if (counter == 0) {
                    break;
                }
            }
            System.out.println();
        }
    }


    public String returnEmojiEntity(Entity entity) {
        return switch (entity.getClass().getSimpleName()) {
            case "Wolf" -> EntityEnumEmoji.WOLF.getEmoji();
            case "Hare" -> EntityEnumEmoji.HARE.getEmoji();
            case "Place" -> EntityEnumEmoji.PLACE.getEmoji();
            case "Rock" -> EntityEnumEmoji.ROCK.getEmoji();
            case "Grass" -> EntityEnumEmoji.GRASS.getEmoji();
            default -> "Entity не обнаружено";
        };
    }
}
