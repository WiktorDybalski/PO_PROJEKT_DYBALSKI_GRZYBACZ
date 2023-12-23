import model.maps.GlobeMap;
import model.simulation.Simulation;

public class Main {
    private static Simulation simulation;

    public Main() {
        simulation = new Simulation(new GlobeMap(10, 10, 10, 10));
    }
    public static void main(String[] args) {

        simulation.run(10,20);
    }
}
