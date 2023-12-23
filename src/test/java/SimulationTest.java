import model.maps.GlobeMap;
import model.simulation.Simulation;
import org.junit.jupiter.api.Test;

public class SimulationTest {
    @Test
    public void testSimulation() {
        Simulation simulation = new Simulation(new GlobeMap(10, 10, 30, 30));
        simulation.run(20,20);
    }
}
