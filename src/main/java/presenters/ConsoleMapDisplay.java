package presenters;

import model.maps.WorldMap;
import model.simulation.MapChangeListener;

public class ConsoleMapDisplay implements MapChangeListener {
    private int operationsCounter = 0;

    @Override
    public synchronized void mapChanged(WorldMap worldMap, String message) {
//        synchronized (System.out) {
//            operationsCounter++;
//            System.out.println(" ");
//            System.out.println("Map ID: ");
//            System.out.println("Number of changes: " + operationsCounter);
//            System.out.println(message);
//            System.out.println(worldMap);
//        }
        return;
    }
}
