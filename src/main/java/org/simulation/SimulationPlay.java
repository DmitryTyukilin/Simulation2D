package main.java.org.simulation;

import java.util.Scanner;

public class SimulationPlay {
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        Thread simulationThread = new Thread(simulation);
        simulationThread.start();


        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if ("1".equalsIgnoreCase(input)) {
                simulation.pause();
                System.out.println("Симуляция приостановлена.");
            } else if ("2".equalsIgnoreCase(input)) {
                simulation.resume();
                System.out.println("Симуляция возобновлена.");
            } else if ("3".equalsIgnoreCase(input)) {
                simulationThread.interrupt();
                simulation.stop();
                break;
            }
        }
        scanner.close();
        System.out.println("Симуляция закончена.");
    }
}

