package main.java.org.simulation.service;


import main.java.org.simulation.Coordinate;
import main.java.org.simulation.MapBoard;
import main.java.org.simulation.entity.Entity;
import main.java.org.simulation.enums.EntityEnumEmoji;
import main.java.org.simulation.intarfaces.DisplayedInConsole;

public class ConsolePrinter {
    private final DisplayedInConsole displayed;

    public ConsolePrinter(DisplayedInConsole displayed) {
        this.displayed = displayed;
    }

    public void printMap() {
        displayed.displayInSquareView();
    }

}
