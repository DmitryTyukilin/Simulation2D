package dimant.simulation.service;

import dimant.simulation.Coordinate;
import dimant.simulation.Entity;
import dimant.simulation.EntityEnum;

import java.util.List;

public class ConsolePrinter {


    public ConsolePrinter() {
    }

    public void printEntity(List<Coordinate> coordinates) {
        for (Coordinate coordinate : coordinates) {
            Entity entity = coordinate.getEntity();
            System.out.println(returnEmojiEntity(entity));
        }
    }

    public String returnEmojiEntity(Entity entity) {
        return switch (entity.getClass().getSimpleName()) {
            case "Wolf" -> EntityEnum.WOLF.getEmoji();
            case "Hare" -> EntityEnum.HARE.getEmoji();
            case "Place" -> EntityEnum.PLACE.getEmoji();
            default -> "Entity не обнаружено";
        };
    }
}
