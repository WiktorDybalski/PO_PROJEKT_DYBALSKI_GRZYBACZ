package model.utils;

import model.maps.GlobeMap;
import model.simulation.Simulation;
import model.simulation.SimulationConfigurator;
import org.junit.jupiter.api.Test;

// TODO: add, tests
public class StatisticsTest {

    SimulationConfigurator config;
    GlobeMap map;
    Simulation simulation;
    Statistics statistics;
    @Test
    void setUp() {
        this.config = new SimulationConfigurator();
        this.map = new GlobeMap(config);
        this.simulation = new Simulation(map, config);
        this.statistics = new Statistics(map);
    }
}
