package presenters;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.maps.GlobeMap;
import model.maps.WorldMap;
import model.simulation.MapChangeListener;
import model.simulation.Simulation;
import model.simulation.SimulationConfigurator;
import model.simulation.SimulationEngine;
import model.utils.Boundary;
import model.utils.MapObjects;
import model.utils.Tile;
import model.utils.Vector2d;

import java.util.ArrayList;
import java.util.List;


public class SimulationPresenter implements MapChangeListener {
    private static final String EMPTY_CELL = " ";
    private WorldMap worldMap;
    @FXML
    private Label infoLabel;

    @FXML
    private TextField movesListTextField;

    @FXML
    private Label moveInfoLabel;

    @FXML
    private Button startButton;

    @FXML
    private GridPane mapGrid;

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
        label.setMinWidth(50);
        label.setMinHeight(50);
        label.setAlignment(Pos.CENTER);
        return label;
    }

    private void drawGridCell(Vector2d position, int column, int row) {
        Tile tile = worldMap.getTile(position);
        String string = EMPTY_CELL;
        if (tile.isOccupied()) {
            if (!tile.getAnimals().isEmpty()) {
                string = tile.getStrongestAnimal().getEnergy() + "";
            } else {
                if (tile.getPlant() != null) {
                    string = "******\n******\n******";
                }
            }
        }
        Label label = createLabelForElement(string);

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
            label.setMinWidth(50);
            label.setMinHeight(50);
            label.setAlignment(Pos.CENTER);
            mapGrid.add(label, j + 1 - boundary.leftDownCorner().getX(), 0); // Dodajemy etykiety osi X na górze siatki
        }
    }

    private void drawYAxis(Boundary boundary) {
        for (int i = boundary.leftDownCorner().getY(); i <= boundary.rightUpperCorner().getY(); i++) {
            Label label = new Label(Integer.toString(i));
            label.setMinWidth(50);
            label.setMinHeight(50);
            label.setAlignment(Pos.CENTER);
            mapGrid.add(label, 0, boundary.rightUpperCorner().getY() - i + 1); // Dodajemy etykiety osi Y po lewej stronie siatki
        }
    }

    private void drawAxes(Boundary boundary) {
        drawXAxis(boundary);
        drawYAxis(boundary);
        Label label = new Label("x/y");
        label.setMinWidth(50);
        label.setMinHeight(50);
        label.setAlignment(Pos.CENTER);
        mapGrid.add(label, 0, 0);

    }

    @FXML
    public void drawMap() {
        clearGrid();
        Boundary boundary = worldMap.getCurrentBounds();
        drawAxes(boundary);
        drawGrid(boundary);
        infoLabel.setText("");
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            this.drawMap();
            moveInfoLabel.setText(message);
        });
    }

    @FXML
    public void onSimulationStartClicked() {
        try {
            Simulation simulation = new Simulation(worldMap, new SimulationConfigurator());
            SimulationEngine simulationEngine = new SimulationEngine(new ArrayList<>(List.of(simulation)));
            simulationEngine.runAsync();
            Platform.runLater(() -> startButton.setDisable(true));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
