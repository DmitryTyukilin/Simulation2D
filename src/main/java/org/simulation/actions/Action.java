package main.java.org.simulation.actions;

import main.java.org.simulation.WordMap;

public abstract class Action {
    protected WordMap wordMap;
    public abstract void execute();

    public Action(WordMap wordMap) {
        this.wordMap = wordMap;
    }
}
