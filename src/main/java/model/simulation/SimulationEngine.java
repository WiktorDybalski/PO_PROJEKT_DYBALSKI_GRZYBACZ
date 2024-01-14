package model.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine implements Runnable {
    private List<Simulation> simulations;

    private final List<Thread> threads;

    public SimulationEngine(List<Simulation> simulations) {
        this.simulations = simulations;
        this.threads = new ArrayList<>();
    }

    /**
     * Runs all simulations in the list synchronously.
     */
    public void runSync() {
        for (Simulation simulation : simulations) {
            simulation.run();
        }
    }

    /**
     * Runs all simulations in the list asynchronously.
     */
    public void runAsync() {
        for (Simulation simulation : this.simulations) {
            simulation.start();
        }
    }

    /**
     * Waits for the completion of all simulation threads.
     *
     * @throws InterruptedException Thrown if waiting for threads is interrupted.
     */
    public void awaitSimulationsEnd() throws InterruptedException {
        for (Thread thread : threads) {
            thread.join();
        }
    }

    /**
     * Runs simulations in a thread pool.
     */
    public void runAsyncInThreadPool() {
        int numThreads = 4;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        for (Simulation simulation : simulations) {
            executorService.submit(simulation::run);
        }

        executorService.shutdown();

        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public boolean isRunning() {
        for (Simulation simulation : this.simulations) {
            if (simulation.isRunning()) {
                return true;
            }
        }
        return false;
    }
    public void pauseSimulation() {
        for (Simulation simulation : this.simulations) {
            simulation.pauseSimulation();
        }
    }

    public void resumeSimulation() {
        for (Simulation simulation : this.simulations) {
            simulation.resumeSimulation();
        }
    }

    @Override
    public void run() {
        System.out.println("Thread started.");
    }

}
