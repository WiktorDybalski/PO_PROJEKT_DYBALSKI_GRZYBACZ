package model.utils;

import model.maps.GlobeMap;
import model.simulation.Simulation;
import model.simulation.SimulationConfigurator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// TODO: add, tests
public class StatisticsTest {

    SimulationConfigurator config;
    GlobeMap map;
    Simulation simulation;
    Statistics statistics;
    @BeforeEach
    void setUp() {
        this.config = new SimulationConfigurator();
        this.config.setGenomeLength(2);
        this.config.setInitialAnimalCount(100);
        this.map = new GlobeMap(config);
        this.simulation = new Simulation(map, config);
        this.statistics = new Statistics(map);
    }

    @Test
    void testUpdateStatistics() {
        this.statistics.updateStatistics();

    }
}
