package main.java.org.simulation;

public class MoveCounter {
    private int move = 0;

    public void recordMove() {
        ++move;
        System.out.println("Ход номер: " + move);
    }
}
