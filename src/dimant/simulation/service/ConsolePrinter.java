package dimant.simulation.service;

import dimant.simulation.Coordinate;
import dimant.simulation.Entity;
import dimant.simulation.EntityEnum;
import dimant.simulation.MapBoard;

import java.sql.SQLOutput;
import java.util.List;

public class ConsolePrinter {
    MapBoard mapBoard;

    public ConsolePrinter(MapBoard mapBoard) {
        this.mapBoard = mapBoard;
    }

    public void printMap() {

        for (int x = 1; x < mapBoard.getSizeMapHeight(); x++) {
            int counter = mapBoard.getSizeMapWeight() - 1;
            for (int y = 1; y < mapBoard.getEntityList().size(); y++) {
                Coordinate coordinate = mapBoard.getCoordinateByXY(x,y);
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
            case "Wolf" -> EntityEnum.WOLF.getEmoji();
            case "Hare" -> EntityEnum.HARE.getEmoji();
            case "Place" -> EntityEnum.PLACE.getEmoji();
            case "Rock" -> EntityEnum.ROCK.getEmoji();
            default -> "Entity не обнаружено";
        };
    }
}
