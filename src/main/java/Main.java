import model.maps.GlobeMap;
import model.simulation.Simulation;
import model.simulation.SimulationConfigurator;

public class Main {
    private static Simulation simulation;

    public Main() {
        simulation = new Simulation(new GlobeMap(new SimulationConfigurator()));
    }
    public static void main(String[] args) {

        simulation.run(10);
    }
}
