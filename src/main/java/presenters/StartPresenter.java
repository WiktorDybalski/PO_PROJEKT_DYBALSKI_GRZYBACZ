package presenters;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.simulation.SimulationConfigurator;

import java.io.IOException;
import java.util.function.Predicate;

public class StartPresenter {

    @FXML public Label infoLabel;
    @FXML public TextField reproduceEnergyLossField;
    @FXML public TextField readyToReproduceEnergyField;
    @FXML public TextField minimumMutationCountField;
    @FXML public TextField maximumMutationCountField;
    @FXML public ChoiceBox<String> animalBehaviourTypeChoiceBox;
    @FXML private TextField mapSizeXField;
    @FXML private TextField mapSizeYField;
    @FXML private TextField initialPlantCountField;
    @FXML private ChoiceBox<String> mapTypeChoiceBox;
    @FXML private TextField initialAnimalCountField;
    @FXML private TextField initialAnimalEnergyField;
    @FXML private TextField plantEnergyField;
    @FXML private TextField genomeLengthField;
    @FXML private TextField numberOfPlantsGrowingPerDayField;
    @FXML private Button startButton;
    @FXML private TextField numberOfDaysField;
    @FXML private TextField modeOfPlantGrowingField;
    @FXML private TextField mutationVariantField;

    private SimulationConfigurator config;

    @FXML
    public void initialize() {
        mapTypeChoiceBox.setItems(FXCollections.observableArrayList("GlobeMap", "PoisonedMap"));
        animalBehaviourTypeChoiceBox.setItems(FXCollections.observableArrayList("Normal", "Mutation"));

        setupValidation();
        setupStartButtonBinding();
    }

    private void setupValidation() {
        validateTextField(mapSizeXField, this::isNonNegativeInteger);
        validateTextField(mapSizeYField, this::isNonNegativeInteger);
        validateTextField(initialPlantCountField, this::isNonNegativeInteger);
        validateTextField(initialAnimalCountField, this::isNonNegativeInteger);
        validateTextField(initialAnimalEnergyField, this::isNonNegativeInteger);
        validateTextField(plantEnergyField, this::isNonNegativeInteger);
        validateTextField(genomeLengthField, this::isNonNegativeInteger);
        validateTextField(numberOfPlantsGrowingPerDayField, this::isNonNegativeInteger);
        validateTextField(reproduceEnergyLossField, this::isNonNegativeInteger);
        validateTextField(readyToReproduceEnergyField, this::isNonNegativeInteger);
        validateTextField(minimumMutationCountField, this::isNonNegativeInteger);
        validateTextField(maximumMutationCountField, this::isNonNegativeInteger);
        // Additional validations can be added here if necessary
    }

    private boolean isNonNegativeInteger(String value) {
        try {
            return Integer.parseInt(value) >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void setupStartButtonBinding() {
        BooleanBinding areFieldsEmpty = Bindings.createBooleanBinding(() ->
                        mapSizeXField.getText().isEmpty() || mapSizeYField.getText().isEmpty() ||
                                initialPlantCountField.getText().isEmpty() || initialAnimalCountField.getText().isEmpty() ||
                                initialAnimalEnergyField.getText().isEmpty() || plantEnergyField.getText().isEmpty() ||
                                genomeLengthField.getText().isEmpty() || numberOfPlantsGrowingPerDayField.getText().isEmpty() ||
                                reproduceEnergyLossField.getText().isEmpty() || readyToReproduceEnergyField.getText().isEmpty() ||
                                minimumMutationCountField.getText().isEmpty() || maximumMutationCountField.getText().isEmpty() ||
                                numberOfDaysField.getText().isEmpty() || modeOfPlantGrowingField.getText().isEmpty() ||
                                mutationVariantField.getText().isEmpty() || mapTypeChoiceBox.getValue() == null ||
                                animalBehaviourTypeChoiceBox.getValue() == null,
                mapSizeXField.textProperty(), mapSizeYField.textProperty(),
                initialPlantCountField.textProperty(), initialAnimalCountField.textProperty(),
                initialAnimalEnergyField.textProperty(), plantEnergyField.textProperty(),
                genomeLengthField.textProperty(), numberOfPlantsGrowingPerDayField.textProperty(),
                reproduceEnergyLossField.textProperty(), readyToReproduceEnergyField.textProperty(),
                minimumMutationCountField.textProperty(), maximumMutationCountField.textProperty(),
                numberOfDaysField.textProperty(), modeOfPlantGrowingField.textProperty(),
                mutationVariantField.textProperty(), mapTypeChoiceBox.valueProperty(),
                animalBehaviourTypeChoiceBox.valueProperty()
        );

        startButton.disableProperty().bind(areFieldsEmpty);
    }

    private void validateTextField(TextField textField, Predicate<String> validationFunction) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!validationFunction.test(newValue)) {
                textField.setText(oldValue);
            }
        });
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

    private void configureSimulation() {
        config.setMapSize(parseTextFieldToInt(mapSizeXField), parseTextFieldToInt(mapSizeYField));
        config.setInitialAnimalCount(parseTextFieldToInt(initialAnimalCountField));
        config.setInitialPlantCount(parseTextFieldToInt(initialPlantCountField));
        config.setPlantEnergy(parseTextFieldToInt(plantEnergyField));
        config.setInitialAnimalEnergy(parseTextFieldToInt(initialAnimalEnergyField));
        config.setGenomeLength(parseTextFieldToInt(genomeLengthField));
        config.setNumberOfPlantsGrowingPerDay(parseTextFieldToInt(numberOfPlantsGrowingPerDayField));
        config.setReproduceEnergyLoss(parseTextFieldToInt(reproduceEnergyLossField));
        config.setReadyToReproduceEnergy(parseTextFieldToInt(readyToReproduceEnergyField));
        config.setMinimumMutationCount(parseTextFieldToInt(minimumMutationCountField));
        config.setMaximumMutationCount(parseTextFieldToInt(maximumMutationCountField));
        config.setNumberOfDays(parseTextFieldToInt(numberOfDaysField));
        config.setModeOfPlantGrowing(modeOfPlantGrowingField.getText());
        config.setMutationVariant(mutationVariantField.getText());
        config.setMapType(mapTypeChoiceBox.getValue());
        config.setAnimalBehaviourType(animalBehaviourTypeChoiceBox.getValue());
    }

    private int parseTextFieldToInt(TextField textField) {
        try {
            return Integer.parseInt(textField.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
