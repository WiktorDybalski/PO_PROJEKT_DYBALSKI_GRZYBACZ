package presenters;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.simulation.SimulationConfigurator;

import java.io.IOException;

public class StartPresenter {
    @FXML private Slider daysCountSlider;
    @FXML private Label daysCountValueLabel;
    @FXML private Slider mapWidthSlider;
    @FXML private Label mapWidthValueLabel;
    @FXML private Slider mapHeightSlider;
    @FXML private Label mapHeightValueLabel;
    @FXML private Slider initialPlantCountSlider;
    @FXML private Label initialPlantCountValueLabel;
    @FXML private Slider numberOfPlantsGrowingPerDaySlider;
    @FXML private Label numberOfPlantsGrowingPerDayValueLabel;
    @FXML private Slider initialAnimalCountSlider;
    @FXML private Label initialAnimalCountValueLabel;
    @FXML private Slider initialAnimalEnergySlider;
    @FXML private Label initialAnimalEnergyValueLabel;
    @FXML private Slider readyToReproduceEnergySlider;
    @FXML private Label readyToReproduceEnergyValueLabel;
    @FXML private Slider reproduceEnergyLossSlider;
    @FXML private Label reproduceEnergyLossValueLabel;
    @FXML private Slider minimumMutationCountSlider;
    @FXML private Label minimumMutationCountValueLabel;
    @FXML private Slider maximumMutationCountSlider;
    @FXML private Label maximumMutationCountValueLabel;
    @FXML private Slider genomeLengthSlider;
    @FXML private Label genomeLengthValueLabel;

    @FXML private ChoiceBox<String> mapTypeChoiceBox;
    @FXML private ChoiceBox<String> BehaviourVariantChoiceBox;

    @FXML private Button startButton;

    private SimulationConfigurator config;

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
        mapTypeChoiceBox.setItems(FXCollections.observableArrayList("GlobeMap", "PoisonedMap"));
        BehaviourVariantChoiceBox.setItems(FXCollections.observableArrayList("Normal", "Mutation"));
    }

    @FXML
    public void onStartClicked() {
        try {
            config = new SimulationConfigurator();
            configureSimulation();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/simulation.fxml"));
            Parent root = loader.load();

            SimulationPresenter simulationPresenter = loader.getController();
            simulationPresenter.setWorldMap(config);

            Stage simulationStage = new Stage();
            simulationStage.setScene(new Scene(root));
            simulationStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SimulationConfigurator getConfig() {
        return config;
    }

    private void configureSimulation() {
        config.setMapSize((int) mapWidthSlider.getValue(), (int) mapHeightSlider.getValue());
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

        config.setMapType(mapTypeChoiceBox.getValue());
        config.setAnimalBehaviourType(BehaviourVariantChoiceBox.getValue());
    }
}
