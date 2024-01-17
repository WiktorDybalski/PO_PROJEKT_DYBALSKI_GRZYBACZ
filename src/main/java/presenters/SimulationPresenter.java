package presenters;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.maps.WorldMap;
import model.simulation.MapChangeListener;
import model.simulation.Simulation;
import model.simulation.SimulationEngine;
import model.utils.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class SimulationPresenter implements MapChangeListener {
    private String desktopPath;
    private SimulationCSV simulationCSV;
    private static final String EMPTY_CELL = " ";
    @FXML
    public HBox all;
    @FXML
    public Label header;
    private Animal selectedAnimal;
    private Statistics statistics;
    private int day;
    private Simulation simulation;
    private SimulationEngine simulationEngine;
    private WorldMap worldMap;
    @FXML
    private Label animalInfoLabel;
    @FXML
    private Button hideAnimalButton;
    @FXML
    private Label currentDayLabel;
    @FXML
    private Label moveInfoLabel;

    @FXML
    private Button startStopButton;

    @FXML
    private GridPane mapGrid;

    @FXML
    private Button showDominantGenotypeButton;

    @FXML
    private Button showPlantFieldsButton;

    private boolean isSimulationRunning = false;

    private double cellSize = 60;
    private boolean isCsv;

    public void setCsv(boolean csv) {
        isCsv = csv;
    }

    public Simulation getSimulation() {
        return this.simulation;
    }

    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0));
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

    private Label createLabelForElement(Object element) {
        Label label;
        if (element != null) {
            label = new Label(element.toString());
        } else {
            label = new Label(" ");
        }
        label.setMinWidth(cellSize);
        label.setMinHeight(cellSize);
        label.setAlignment(Pos.CENTER);
        label.getStyleClass().add("map-grid-cell");
        return label;
    }

    private void drawGridCell(Vector2d position, int column, int row) {
        Tile tile = worldMap.getTile(position);
        String string = EMPTY_CELL;
        Label label;
        if (tile.isOccupied()) {
            if (!tile.getAnimals().isEmpty()) {
                string = tile.getStrongestAnimal().getEnergy() + "";
                label = createLabelForElement(string);
            } else {
                if (tile.getPlant() != null) {
                    string = "";
                    label = createLabelForElement(string);
                    label.getStyleClass().add("plant-cell");
                } else {
                    label = createLabelForElement(string);
                }
            }
        } else {
            label = createLabelForElement(string);
        }

        Tooltip tooltip = new Tooltip();
        label.setOnMouseEntered(event -> {
            if (!isSimulationRunning && tile.getAnimals() != null && !tile.getAnimals().isEmpty()) {
                String animalInfo = tile.getStrongestAnimal().getInfo();
                tooltip.setText(animalInfo);
                tooltip.show(label, event.getScreenX(), event.getScreenY() + 10);
            }
        });
        label.setOnMouseExited(event -> tooltip.hide());

        label.setOnMouseClicked(event -> {
            if (tile.getAnimals() != null && !tile.getAnimals().isEmpty()) {
                selectedAnimal = tile.getStrongestAnimal();
                showAnimalInfo(selectedAnimal);
                hideAnimalButton.setDisable(false);
                animalInfoLabel.setVisible(true);
            }
        });

        mapGrid.add(label, column, row);
    }

    private void drawGrid(Boundary boundary) {
        for (int i = boundary.leftDownCorner().getY(); i <= boundary.rightUpperCorner().getY(); i++) {
            for (int j = boundary.leftDownCorner().getX(); j <= boundary.rightUpperCorner().getX(); j++) {
                Vector2d position = new Vector2d(j, i);
                drawGridCell(position, j - boundary.leftDownCorner().getX() + 1, boundary.rightUpperCorner().getY() - i + 1);
            }
        }
    }

    private void drawXAxis(Boundary boundary) {
        for (int j = boundary.leftDownCorner().getX(); j <= boundary.rightUpperCorner().getX(); j++) {
            Label label = new Label(Integer.toString(j));
            label.setMinWidth(cellSize);
            label.setMinHeight(cellSize);
            label.setAlignment(Pos.CENTER);
            mapGrid.add(label, j + 1 - boundary.leftDownCorner().getX(), 0); // Dodajemy etykiety osi X na górze siatki
        }
    }

    private void drawYAxis(Boundary boundary) {
        for (int i = boundary.leftDownCorner().getY(); i <= boundary.rightUpperCorner().getY(); i++) {
            Label label = new Label(Integer.toString(i));
            label.setMinWidth(cellSize);
            label.setMinHeight(cellSize);
            label.setAlignment(Pos.CENTER);
            mapGrid.add(label, 0, boundary.rightUpperCorner().getY() - i + 1);
        }
    }

    private void drawAxes(Boundary boundary) {
        drawXAxis(boundary);
        drawYAxis(boundary);
        Label label = new Label("x/y");
        label.setMinWidth(cellSize);
        label.setMinHeight(cellSize);
        label.setAlignment(Pos.CENTER);
        mapGrid.add(label, 0, 0);

    }

    @FXML
    public void drawMap() {
        clearGrid();
        Boundary boundary = worldMap.getCurrentBounds();
        drawAxes(boundary);
        drawGrid(boundary);
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            currentDayLabel.setText("   Day: " + worldMap.getCurrentDay());
            moveInfoLabel.setText(statistics.getStatistics());
            this.drawMap();
            moveInfoLabel.setText(statistics.getStatistics());
            if(hideAnimalButton.isVisible()) {
                showAnimalInfo(selectedAnimal);
            }
            if(isCsv) {
                simulationCSV.toCSV("Simulation", desktopPath);
            }
        });
    }

    public void stopSimulation() {
        if (simulation != null) {
            simulation.stopSimulation();
        }
    }

    @FXML
    public void onSimulationStartClicked() {
        try {
            if (simulationEngine == null) {
                simulation = new Simulation(worldMap, worldMap.getConfig());
                int maxsize = Math.max(worldMap.getConfig().getMapSizeX(), worldMap.getConfig().getMapSizeY());
                cellSize *= (10.0 / maxsize);
                cellSize = Math.min(cellSize, 40);
                simulationEngine = new SimulationEngine(new ArrayList<>(List.of(simulation)));
                simulationEngine.runAsync();
                statistics = new Statistics(worldMap);
                simulation.setStatistics(statistics);
                Platform.runLater(() -> startStopButton.setText("Start"));
                isSimulationRunning = false;
                animalInfoLabel.setText(Animal.getInfoForNone());
                animalInfoLabel.setVisible(false);
                showDominantGenotypeButton.setVisible(true);
                showPlantFieldsButton.setVisible(true);
                hideAnimalButton.setVisible(true);
                showDominantGenotypeButton.setDisable(true);
                showPlantFieldsButton.setDisable(true);
                hideAnimalButton.setDisable(true);
                if(isCsv) {
                    String userHome = System.getProperty("user.home"); // Pobiera katalog domowy użytkownika
                    desktopPath = userHome + File.separator + "Desktop"; // Dodaje do ścieżki folder Desktop
                    simulationCSV = new SimulationCSV(statistics);
                    simulationCSV.toCSV("Simulation", desktopPath);
                }
            } else {
                Platform.runLater(() -> {
                    if (simulationEngine.isRunning()) {
                        simulationEngine.pauseSimulation();
                        startStopButton.setText("Continue");
                        isSimulationRunning = false;
                        showDominantGenotypeButton.setDisable(false);
                        showPlantFieldsButton.setDisable(false);
                    } else {
                        simulationEngine.resumeSimulation();
                        startStopButton.setText("Stop");
                        isSimulationRunning = true;
                        showDominantGenotypeButton.setDisable(true);
                        showPlantFieldsButton.setDisable(true);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onNotShowAnimalClicked() {
        if (animalInfoLabel != null && animalInfoLabel.isVisible()) {
            animalInfoLabel.setVisible(false);
            hideAnimalButton.setDisable(true);
        }
    }

    private void showAnimalInfo(Animal animal) {
        if (animal != null) {
            animalInfoLabel.setText(animal.getInfo());
        }
    }
    @FXML
    private void onShowDominantGenotypeClicked() {
        Set<Vector2d> dominantGenotypePositions = simulation.getDominantGenotypeAnimals();

        for (Node node : mapGrid.getChildren()) {
            Integer colIndex = GridPane.getColumnIndex(node);
            Integer rowIndex = GridPane.getRowIndex(node);

            colIndex = colIndex != null ? colIndex : 0;
            rowIndex = rowIndex != null ? rowIndex : 0;

            Vector2d position = new Vector2d(colIndex-1, worldMap.getHeight()-rowIndex);
            if (dominantGenotypePositions.contains(position)) {
                node.setStyle("-fx-background-color: red; -fx-border-color: black; -fx-border-width: 1;");
            } else {
                node.setStyle("-fx-background-color: transparent; -fx-border-color: black; -fx-border-width: 1;");
            }
        }
    }



        public void onShowPlantFieldsClicked () {
            Set<Vector2d> plantPreferredFields = simulation.getPlantPreferredFields();

            for (Node node : mapGrid.getChildren()) {
                Integer colIndex = GridPane.getColumnIndex(node);
                Integer rowIndex = GridPane.getRowIndex(node);
                colIndex = colIndex != null ? colIndex : 0;
                rowIndex = rowIndex != null ? rowIndex : 0;

                Vector2d position = new Vector2d(colIndex, rowIndex);
                if (plantPreferredFields.contains(position)) {
                    node.setStyle("-fx-background-color: red; -fx-border-color: black; -fx-border-width: 1;");
                } else {
                    node.setStyle("-fx-background-color: transparent; -fx-border-color: black; -fx-border-width: 1;");
                }
            }
        }
    }