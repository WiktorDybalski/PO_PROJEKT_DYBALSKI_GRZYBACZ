package presenters;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.maps.GlobeMap;
import model.maps.PoisonedMap;
import model.simulation.SimulationConfigurator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StartPresenter {
    @FXML
    private Button loadConfigButton;
    @FXML
    private Button saveConfigButton;
    @FXML
    private Button startButton;
    @FXML
    public ChoiceBox<String> saveToCsvChoiceBox;
    @FXML
    public ChoiceBox<String> BehaviourVariant;
    @FXML
    public Slider dayLengthSlider;
    @FXML
    public Label dayLengthValueLabel;
    private SimulationPresenter simulationPresenter;
    @FXML
    private ChoiceBox<String> MapVariant;
    @FXML
    private Slider daysCountSlider;
    @FXML
    private GridPane contentArea;
    @FXML
    private Label daysCountValueLabel;
    @FXML
    private Slider mapWidthSlider;
    @FXML
    private Label mapWidthValueLabel;
    @FXML
    private Slider mapHeightSlider;
    @FXML
    private Label mapHeightValueLabel;
    @FXML
    private Slider initialPlantCountSlider;
    @FXML
    private Label initialPlantCountValueLabel;
    @FXML
    private Slider numberOfPlantsGrowingPerDaySlider;
    @FXML
    private Label numberOfPlantsGrowingPerDayValueLabel;
    @FXML
    private Slider initialAnimalCountSlider;
    @FXML
    private Label initialAnimalCountValueLabel;
    @FXML
    private Slider initialAnimalEnergySlider;
    @FXML
    private Label initialAnimalEnergyValueLabel;
    @FXML
    private Slider readyToReproduceEnergySlider;
    @FXML
    private Label readyToReproduceEnergyValueLabel;
    @FXML
    private Slider reproduceEnergyLossSlider;
    @FXML
    private Label reproduceEnergyLossValueLabel;
    @FXML
    private Slider minimumMutationCountSlider;
    @FXML
    private Label minimumMutationCountValueLabel;
    @FXML
    private Slider maximumMutationCountSlider;
    @FXML
    private Label maximumMutationCountValueLabel;
    @FXML
    private Slider genomeLengthSlider;
    @FXML
    private Label genomeLengthValueLabel;

    @FXML
    private ChoiceBox<String> mapTypeChoiceBox;
    @FXML
    private ChoiceBox<String> BehaviourVariantChoiceBox;

    private SimulationConfigurator config = new SimulationConfigurator();

    @FXML
    public void initialize() {
        daysCountValueLabel.textProperty().bind(
                daysCountSlider.valueProperty().asString("%.0f")
        );
        mapWidthValueLabel.textProperty().bind(
                mapWidthSlider.valueProperty().asString("%.0f")
        );
        mapHeightValueLabel.textProperty().bind(
                mapHeightSlider.valueProperty().asString("%.0f")
        );
        initialPlantCountValueLabel.textProperty().bind(
                initialPlantCountSlider.valueProperty().asString("%.0f")
        );
        numberOfPlantsGrowingPerDayValueLabel.textProperty().bind(
                numberOfPlantsGrowingPerDaySlider.valueProperty().asString("%.0f")
        );
        initialAnimalCountValueLabel.textProperty().bind(
                initialAnimalCountSlider.valueProperty().asString("%.0f")
        );
        initialAnimalEnergyValueLabel.textProperty().bind(
                initialAnimalEnergySlider.valueProperty().asString("%.0f")
        );
        readyToReproduceEnergyValueLabel.textProperty().bind(
                readyToReproduceEnergySlider.valueProperty().asString("%.0f")
        );
        reproduceEnergyLossValueLabel.textProperty().bind(
                reproduceEnergyLossSlider.valueProperty().asString("%.0f")
        );
        minimumMutationCountValueLabel.textProperty().bind(
                minimumMutationCountSlider.valueProperty().asString("%.0f")
        );
        maximumMutationCountValueLabel.textProperty().bind(
                maximumMutationCountSlider.valueProperty().asString("%.0f")
        );
        genomeLengthValueLabel.textProperty().bind(
                genomeLengthSlider.valueProperty().asString("%.0f")
        );

        dayLengthValueLabel.textProperty().bind(
                dayLengthSlider.valueProperty().asString("%.0f")
        );

        mapTypeChoiceBox.setItems(FXCollections.observableArrayList("GlobeMap", "PoisonedMap"));
        BehaviourVariantChoiceBox.setItems(FXCollections.observableArrayList("Random", "LittleRandom"));
        saveToCsvChoiceBox.setItems(FXCollections.observableArrayList("Yes", "No"));
        saveToCsvChoiceBox.setValue("No");
    }

    private boolean isChoiceBoxValid() {
        return mapTypeChoiceBox.getValue() != null && BehaviourVariantChoiceBox.getValue() != null && saveToCsvChoiceBox.getValue() != null;
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText("Please select options from all ChoiceBoxes before starting the simulation.");
        alert.showAndWait();
    }

    private void stopSimulation() {
        if (simulationPresenter != null) {
            simulationPresenter.stopSimulation();
        }
    }

    @FXML
    public void onStartClicked() {
        try {
            configureSimulation();
            if (!isChoiceBoxValid()) {
                showAlert();
                return;
            }

            String selectedOption = config.getMapType();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/simulation.fxml"));
            Parent root = loader.load();

            simulationPresenter = loader.getController();

            if (selectedOption.equals("GlobeMap")) {
                config.setMapType("GlobeMap");
                GlobeMap globeMap = new GlobeMap(config);
                simulationPresenter.setWorldMap(globeMap);
                globeMap.addObserver(simulationPresenter);
            } else {
                config.setMapType("PoisonedMap");
                PoisonedMap poisonedMap = new PoisonedMap(config);
                simulationPresenter.setWorldMap(poisonedMap);
                poisonedMap.addObserver(simulationPresenter);
            }
            if(config.isWriteToCsv().equals("Yes")) {
                simulationPresenter.setCsv(true);
            }
            simulationPresenter.onSimulationStartClicked();
            Stage simulationStage = new Stage();
            simulationStage.setScene(new Scene(root));
            simulationStage.setOnCloseRequest(event -> stopSimulation());
            simulationStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SimulationConfigurator getConfig() {
        return config;
    }

    private void configureSimulation() {
        int width = (int) mapWidthSlider.getValue();
        int height = (int) mapHeightSlider.getValue();
        config.setMapSize(width, height);
        config.setNumberOfDays((int) daysCountSlider.getValue());
        config.setInitialPlantCount((int) initialPlantCountSlider.getValue());
        config.setNumberOfPlantsGrowingPerDay((int) numberOfPlantsGrowingPerDaySlider.getValue());
        config.setInitialAnimalCount((int) initialAnimalCountSlider.getValue());
        config.setInitialAnimalEnergy((int) initialAnimalEnergySlider.getValue());
        config.setReadyToReproduceEnergy((int) readyToReproduceEnergySlider.getValue());
        config.setReproduceEnergyLoss((int) reproduceEnergyLossSlider.getValue());
        config.setMinimumMutationCount((int) minimumMutationCountSlider.getValue());
        config.setMaximumMutationCount((int) maximumMutationCountSlider.getValue());
        config.setGenomeLength((int) genomeLengthSlider.getValue());
        config.setDayLength((int) dayLengthSlider.getValue());

        config.setMapType(mapTypeChoiceBox.getValue());
        config.setAnimalBehaviourType(BehaviourVariantChoiceBox.getValue());
        config.setWriteToCsv(saveToCsvChoiceBox.getValue());
    }

    private void updateUIWithConfig() {
        // Aktualizacja elementów UI na podstawie obiektu konfiguracyjnego
        daysCountSlider.setValue(config.getNumberOfDays());
        mapWidthSlider.setValue(config.getMapSize().getX());
        mapHeightSlider.setValue(config.getMapSize().getY());
        initialPlantCountSlider.setValue(config.getInitialPlantCount());
        numberOfPlantsGrowingPerDaySlider.setValue(config.getNumberOfPlantsGrowingPerDay());
        initialAnimalCountSlider.setValue(config.getInitialAnimalCount());
        initialAnimalEnergySlider.setValue(config.getInitialAnimalEnergy());
        readyToReproduceEnergySlider.setValue(config.getReadyToReproduceEnergy());
        reproduceEnergyLossSlider.setValue(config.getReproduceEnergyLoss());
        minimumMutationCountSlider.setValue(config.getMinimumMutationCount());
        maximumMutationCountSlider.setValue(config.getMaximumMutationCount());
        genomeLengthSlider.setValue(config.getGenomeLength());
        dayLengthSlider.setValue(config.getDayLength());
    }

    public void onLoadConfigClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Configuration File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        File file = fileChooser.showOpenDialog(contentArea.getScene().getWindow());

        if (file != null) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                List<String> lines = new ArrayList<>();

                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                reader.close();

                config.fromText(lines.toArray(new String[0]));
                updateUIWithConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void onSaveConfigClicked() {
        configureSimulation();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Configuration File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showSaveDialog(contentArea.getScene().getWindow());

        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                String configText = config.toText();
                writer.write(configText);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
