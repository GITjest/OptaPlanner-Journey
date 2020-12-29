package journey.guifx.configuration.algorithm;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.optaplanner.core.config.localsearch.LocalSearchPhaseConfig;
import org.optaplanner.core.config.localsearch.LocalSearchType;
import org.optaplanner.core.config.localsearch.decider.acceptor.AcceptorConfig;
import org.optaplanner.core.config.localsearch.decider.acceptor.AcceptorType;
import org.optaplanner.core.config.localsearch.decider.acceptor.stepcountinghillclimbing.StepCountingHillClimbingType;
import org.optaplanner.core.config.localsearch.decider.forager.FinalistPodiumType;
import org.optaplanner.core.config.localsearch.decider.forager.LocalSearchForagerConfig;
import org.optaplanner.core.config.localsearch.decider.forager.LocalSearchPickEarlyType;

import java.util.Collections;

public class LocalSearch extends GridPane {
    private LocalSearchPhaseConfig localSearchPhaseConfig;
    private static CheckBox save = new CheckBox();
    private GridPane acceptorGridPane = new GridPane();

    public LocalSearch(LocalSearchPhaseConfig localSearchPhaseConfig) {
        if(localSearchPhaseConfig == null) this.localSearchPhaseConfig = new LocalSearchPhaseConfig();
        else this.localSearchPhaseConfig = localSearchPhaseConfig;
        if(localSearchPhaseConfig.getAcceptorConfig() == null)
            localSearchPhaseConfig.setAcceptorConfig(new AcceptorConfig());
        if(localSearchPhaseConfig.getForagerConfig() == null)
            localSearchPhaseConfig.setForagerConfig(new LocalSearchForagerConfig());

        getStyleClass().add("local-search");
        acceptorGridPane.getStyleClass().add("local-search");
        add(new Label("Local Search"), 0,0);
        add(save, 1, 0);
        add(new Label("Typ"), 0,1);
        add(localSearchType(), 1, 1);
        add(new Label("Forager"), 0,2);
        add(new Label("pickEarlyType"), 0,3);
        add(pickEarlyType(), 1,3);
        add(new Label("acceptedCountLimit      "), 0,4);
        add(acceptedCountLimit(), 1,4);
        add(new Label("finalistPodiumType"), 0,5);
        add(finalistPodiumType(), 1,5);
        add(new Label("breakTieRandomly"), 0,6);
        add(breakTieRandomly(), 1,6);

        acceptorGridPane.add(new Label("Acceptor"), 0,0);
        acceptorGridPane.add(new Label("Acceptor type"), 0,1);
        acceptorGridPane.add(acceptorType(), 1, 1);
        acceptorGridPane.add(new Label("entityTabuSize/Ratio"), 0,2);
        acceptorGridPane.add(new HBox(entityTabuSize(), new Label("   "), entityTabuRatio()), 1, 2);
        acceptorGridPane.add(new Label("fadingEntityTabuSize/Ratio"), 0,3);
        acceptorGridPane.add(new HBox(fadingEntityTabuSize(), new Label("   "), fadingEntityTabuRatio()), 1, 3);
        acceptorGridPane.add(new Label("valueTabuSize/Ratio"), 0,4);
        acceptorGridPane.add(new HBox(valueTabuSize(), new Label("   "), valueTabuRatio()), 1, 4);
        acceptorGridPane.add(new Label("fadingValueTabuSize/Ratio"), 0,5);
        acceptorGridPane.add(new HBox(fadingValueTabuSize(), new Label("   "), fadingValueTabuRatio()), 1, 5);
        acceptorGridPane.add(new Label("moveTabuSize"), 0,6);
        acceptorGridPane.add(moveTabuSize(), 1, 6);
        acceptorGridPane.add(new Label("fadingMoveTabuSize"), 0,7);
        acceptorGridPane.add(fadingMoveTabuSize(), 1, 7);
        acceptorGridPane.add(new Label("undoMoveTabuSize"), 0,8);
        acceptorGridPane.add(undoMoveTabuSize(), 1, 8);
        acceptorGridPane.add(new Label("fadingUndoMoveTabuSize"), 0,9);
        acceptorGridPane.add(fadingUndoMoveTabuSize(), 1, 9);
        acceptorGridPane.add(new Label("solutionTabuSize"), 0,10);
        acceptorGridPane.add(solutionTabuSize(), 1, 10);
        acceptorGridPane.add(new Label("fadingSolutionTabuSize"), 0,11);
        acceptorGridPane.add(fadingSolutionTabuSize(), 1, 11);
        acceptorGridPane.add(new Label("simulatedAnnealingStartingTemperature"), 0,12);
        acceptorGridPane.add(simulatedAnnealingStartingTemperature(), 1, 12);
        acceptorGridPane.add(new Label("lateAcceptanceSize"), 0,13);
        acceptorGridPane.add(lateAcceptanceSize(), 1, 13);
        acceptorGridPane.add(new Label("greatDelugeWaterLevelIncrementScore/Ratio"), 0,14);
        acceptorGridPane.add(new HBox(greatDelugeWaterLevelIncrementScore(), new Label("   "), greatDelugeWaterLevelIncrementScore()), 1, 14);
        acceptorGridPane.add(new Label("stepCountingHillClimbingSize"), 0,15);
        acceptorGridPane.add(stepCountingHillClimbingSize(), 1, 15);
        acceptorGridPane.add(new Label("stepCountingHillClimbingType"), 0,16);
        acceptorGridPane.add(stepCountingHillClimbingType(), 1, 16);
    }

    private ChoiceBox<LocalSearchType> localSearchType() {
        ChoiceBox<LocalSearchType> choiceBox = new ChoiceBox<>();
        choiceBox.setItems(FXCollections.observableArrayList(LocalSearchType.values()));
        if (localSearchPhaseConfig.getLocalSearchType() != null)
            choiceBox.setValue(localSearchPhaseConfig.getLocalSearchType());
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> localSearchPhaseConfig.setLocalSearchType(choiceBox.getItems().get((Integer) newValue)));
        return choiceBox;
    }

    private ChoiceBox<AcceptorType> acceptorType() {
        ChoiceBox<AcceptorType> choiceBox = new ChoiceBox<>();
        choiceBox.setItems(FXCollections.observableArrayList(AcceptorType.values()));
        if (localSearchPhaseConfig.getAcceptorConfig().getAcceptorTypeList() != null)
            choiceBox.setValue(localSearchPhaseConfig.getAcceptorConfig().getAcceptorTypeList().get(0));
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> localSearchPhaseConfig.getAcceptorConfig().setAcceptorTypeList(
                        Collections.singletonList(choiceBox.getItems().get((Integer) newValue))));
        return choiceBox;
    }

    private TextField entityTabuSize() {
        TextField textField = new TextField();
        textField.getStyleClass().add("small-text-field");
        if(localSearchPhaseConfig.getAcceptorConfig().getEntityTabuSize() != null)
            textField.setText(String.valueOf(localSearchPhaseConfig.getAcceptorConfig().getEntityTabuSize()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                localSearchPhaseConfig.getAcceptorConfig().setEntityTabuSize(!textField.getText().equals("") ? Integer.valueOf(textField.getText()) : null);
            }
        });
        return textField;
    }

    private TextField entityTabuRatio() {
        TextField textField = new TextField();
        textField.getStyleClass().add("small-text-field");
        if(localSearchPhaseConfig.getAcceptorConfig().getEntityTabuRatio() != null)
            textField.setText(String.valueOf(localSearchPhaseConfig.getAcceptorConfig().getEntityTabuRatio()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                localSearchPhaseConfig.getAcceptorConfig().setEntityTabuRatio(!textField.getText().equals("") ? Double.valueOf(textField.getText()) : null);
            }
        });
        return textField;
    }

    private TextField fadingEntityTabuSize() {
        TextField textField = new TextField();
        textField.getStyleClass().add("small-text-field");
        if(localSearchPhaseConfig.getAcceptorConfig().getFadingEntityTabuSize() != null)
            textField.setText(String.valueOf(localSearchPhaseConfig.getAcceptorConfig().getFadingEntityTabuSize()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                localSearchPhaseConfig.getAcceptorConfig().setFadingEntityTabuSize(!textField.getText().equals("") ? Integer.valueOf(textField.getText()) : null);
            }
        });
        return textField;
    }

    private TextField fadingEntityTabuRatio() {
        TextField textField = new TextField();
        textField.getStyleClass().add("small-text-field");
        if(localSearchPhaseConfig.getAcceptorConfig().getFadingEntityTabuRatio() != null)
            textField.setText(String.valueOf(localSearchPhaseConfig.getAcceptorConfig().getFadingEntityTabuRatio()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                localSearchPhaseConfig.getAcceptorConfig().setFadingEntityTabuRatio(!textField.getText().equals("") ? Double.valueOf(textField.getText()) : null);
            }
        });
        return textField;
    }

    private TextField valueTabuSize() {
        TextField textField = new TextField();
        textField.getStyleClass().add("small-text-field");
        if(localSearchPhaseConfig.getAcceptorConfig().getValueTabuSize() != null)
            textField.setText(String.valueOf(localSearchPhaseConfig.getAcceptorConfig().getValueTabuSize()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                localSearchPhaseConfig.getAcceptorConfig().setValueTabuSize(!textField.getText().equals("") ? Integer.valueOf(textField.getText()) : null);
            }
        });
        return textField;
    }

    private TextField valueTabuRatio() {
        TextField textField = new TextField();
        textField.getStyleClass().add("small-text-field");
        if(localSearchPhaseConfig.getAcceptorConfig().getValueTabuRatio() != null)
            textField.setText(String.valueOf(localSearchPhaseConfig.getAcceptorConfig().getValueTabuRatio()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                localSearchPhaseConfig.getAcceptorConfig().setValueTabuRatio(!textField.getText().equals("") ? Double.valueOf(textField.getText()) : null);
            }
        });
        return textField;
    }

    private TextField fadingValueTabuSize() {
        TextField textField = new TextField();
        textField.getStyleClass().add("small-text-field");
        if(localSearchPhaseConfig.getAcceptorConfig().getFadingValueTabuSize() != null)
            textField.setText(String.valueOf(localSearchPhaseConfig.getAcceptorConfig().getFadingValueTabuSize()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                localSearchPhaseConfig.getAcceptorConfig().setFadingValueTabuSize(!textField.getText().equals("") ? Integer.valueOf(textField.getText()) : null);
            }
        });
        return textField;
    }

    private TextField fadingValueTabuRatio() {
        TextField textField = new TextField();
        textField.getStyleClass().add("small-text-field");
        if(localSearchPhaseConfig.getAcceptorConfig().getFadingValueTabuRatio() != null)
            textField.setText(String.valueOf(localSearchPhaseConfig.getAcceptorConfig().getFadingValueTabuRatio()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                localSearchPhaseConfig.getAcceptorConfig().setFadingValueTabuRatio(!textField.getText().equals("") ? Double.valueOf(textField.getText()) : null);
            }
        });
        return textField;
    }

    private TextField moveTabuSize() {
        TextField textField = new TextField();
        if(localSearchPhaseConfig.getAcceptorConfig().getMoveTabuSize() != null)
            textField.setText(String.valueOf(localSearchPhaseConfig.getAcceptorConfig().getMoveTabuSize()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                localSearchPhaseConfig.getAcceptorConfig().setMoveTabuSize(!textField.getText().equals("") ? Integer.valueOf(textField.getText()) : null);
            }
        });
        return textField;
    }

    private TextField fadingMoveTabuSize() {
        TextField textField = new TextField();
        if(localSearchPhaseConfig.getAcceptorConfig().getFadingMoveTabuSize() != null)
            textField.setText(String.valueOf(localSearchPhaseConfig.getAcceptorConfig().getFadingMoveTabuSize()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                localSearchPhaseConfig.getAcceptorConfig().setFadingMoveTabuSize(!textField.getText().equals("") ? Integer.valueOf(textField.getText()) : null);
            }
        });
        return textField;
    }

    private TextField undoMoveTabuSize() {
        TextField textField = new TextField();
        if(localSearchPhaseConfig.getAcceptorConfig().getUndoMoveTabuSize() != null)
            textField.setText(String.valueOf(localSearchPhaseConfig.getAcceptorConfig().getUndoMoveTabuSize()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                localSearchPhaseConfig.getAcceptorConfig().setUndoMoveTabuSize(!textField.getText().equals("") ? Integer.valueOf(textField.getText()) : null);
            }
        });
        return textField;
    }

    private TextField fadingUndoMoveTabuSize() {
        TextField textField = new TextField();
        if(localSearchPhaseConfig.getAcceptorConfig().getFadingUndoMoveTabuSize() != null)
            textField.setText(String.valueOf(localSearchPhaseConfig.getAcceptorConfig().getFadingUndoMoveTabuSize()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                localSearchPhaseConfig.getAcceptorConfig().setFadingUndoMoveTabuSize(!textField.getText().equals("") ? Integer.valueOf(textField.getText()) : null);
            }
        });
        return textField;
    }

    private TextField solutionTabuSize() {
        TextField textField = new TextField();
        if(localSearchPhaseConfig.getAcceptorConfig().getSolutionTabuSize() != null)
            textField.setText(String.valueOf(localSearchPhaseConfig.getAcceptorConfig().getSolutionTabuSize()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                localSearchPhaseConfig.getAcceptorConfig().setSolutionTabuSize(!textField.getText().equals("") ? Integer.valueOf(textField.getText()) : null);
            }
        });
        return textField;
    }

    private TextField fadingSolutionTabuSize() {
        TextField textField = new TextField();
        if(localSearchPhaseConfig.getAcceptorConfig().getFadingSolutionTabuSize() != null)
            textField.setText(String.valueOf(localSearchPhaseConfig.getAcceptorConfig().getFadingSolutionTabuSize()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                localSearchPhaseConfig.getAcceptorConfig().setFadingSolutionTabuSize(!textField.getText().equals("") ? Integer.valueOf(textField.getText()) : null);
            }
        });
        return textField;
    }

    private TextField simulatedAnnealingStartingTemperature() {
        TextField textField = new TextField();
        if(localSearchPhaseConfig.getAcceptorConfig().getSimulatedAnnealingStartingTemperature() != null)
            textField.setText(localSearchPhaseConfig.getAcceptorConfig().getSimulatedAnnealingStartingTemperature());
        textField.textProperty().addListener((observable, oldValue, newValue) -> localSearchPhaseConfig.getAcceptorConfig().setSimulatedAnnealingStartingTemperature(!textField.getText().equals("") ? textField.getText() : null));
        return textField;
    }

    private TextField lateAcceptanceSize() {
        TextField textField = new TextField();
        if(localSearchPhaseConfig.getAcceptorConfig().getLateAcceptanceSize() != null)
            textField.setText(String.valueOf(localSearchPhaseConfig.getAcceptorConfig().getLateAcceptanceSize()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                localSearchPhaseConfig.getAcceptorConfig().setLateAcceptanceSize(!textField.getText().equals("") ? Integer.valueOf(textField.getText()) : null);
            }
        });
        return textField;
    }

    private TextField greatDelugeWaterLevelIncrementScore() {
        TextField textField = new TextField();
        textField.getStyleClass().add("small-text-field");
        if(localSearchPhaseConfig.getAcceptorConfig().getGreatDelugeWaterLevelIncrementScore() != null)
            textField.setText(localSearchPhaseConfig.getAcceptorConfig().getGreatDelugeWaterLevelIncrementScore());
        textField.textProperty().addListener((observable, oldValue, newValue) -> localSearchPhaseConfig.getAcceptorConfig().setGreatDelugeWaterLevelIncrementScore(!textField.getText().equals("") ? textField.getText() : null));
        return textField;
    }

    private TextField greatDelugeWaterLevelIncrementRatio() {
        TextField textField = new TextField();
        textField.getStyleClass().add("small-text-field");
        if(localSearchPhaseConfig.getAcceptorConfig().getGreatDelugeWaterLevelIncrementRatio() != null)
            textField.setText(String.valueOf(localSearchPhaseConfig.getAcceptorConfig().getGreatDelugeWaterLevelIncrementRatio()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                localSearchPhaseConfig.getAcceptorConfig().setGreatDelugeWaterLevelIncrementRatio(!textField.getText().equals("") ? Double.valueOf(textField.getText()) : null);
            }
        });
        return textField;
    }

    private TextField stepCountingHillClimbingSize() {
        TextField textField = new TextField();
        if(localSearchPhaseConfig.getAcceptorConfig().getStepCountingHillClimbingSize() != null)
            textField.setText(String.valueOf(localSearchPhaseConfig.getAcceptorConfig().getStepCountingHillClimbingSize()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                localSearchPhaseConfig.getAcceptorConfig().setStepCountingHillClimbingSize(!textField.getText().equals("") ? Integer.valueOf(textField.getText()) : null);
            }
        });
        return textField;
    }

    private ChoiceBox<StepCountingHillClimbingType> stepCountingHillClimbingType() {
        ChoiceBox<StepCountingHillClimbingType> choiceBox = new ChoiceBox<>();
        choiceBox.setItems(FXCollections.observableArrayList(StepCountingHillClimbingType.values()));
        if (localSearchPhaseConfig.getAcceptorConfig().getStepCountingHillClimbingType() != null)
            choiceBox.setValue(localSearchPhaseConfig.getAcceptorConfig().getStepCountingHillClimbingType());
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> localSearchPhaseConfig.getAcceptorConfig().setStepCountingHillClimbingType(choiceBox.getItems().get((Integer) newValue)));
        return choiceBox;
    }

    private ChoiceBox<LocalSearchPickEarlyType> pickEarlyType() {
        ChoiceBox<LocalSearchPickEarlyType> choiceBox = new ChoiceBox<>();
        choiceBox.setItems(FXCollections.observableArrayList(LocalSearchPickEarlyType.values()));
        if (localSearchPhaseConfig.getForagerConfig().getPickEarlyType() != null)
            choiceBox.setValue(localSearchPhaseConfig.getForagerConfig().getPickEarlyType());
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> localSearchPhaseConfig.getForagerConfig().setPickEarlyType(choiceBox.getItems().get((Integer) newValue)));
        return choiceBox;
    }

    private TextField acceptedCountLimit() {
        TextField textField = new TextField();
        if(localSearchPhaseConfig.getForagerConfig().getAcceptedCountLimit() != null)
            textField.setText(String.valueOf(localSearchPhaseConfig.getForagerConfig().getAcceptedCountLimit()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                localSearchPhaseConfig.getForagerConfig().setAcceptedCountLimit(!textField.getText().equals("") ? Integer.valueOf(textField.getText()) : null);
            }
        });
        return textField;
    }

    private ChoiceBox<FinalistPodiumType> finalistPodiumType() {
        ChoiceBox<FinalistPodiumType> choiceBox = new ChoiceBox<>();
        choiceBox.setItems(FXCollections.observableArrayList(FinalistPodiumType.values()));
        if (localSearchPhaseConfig.getForagerConfig().getFinalistPodiumType() != null)
            choiceBox.setValue(localSearchPhaseConfig.getForagerConfig().getFinalistPodiumType());
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> localSearchPhaseConfig.getForagerConfig().setFinalistPodiumType(choiceBox.getItems().get((Integer) newValue)));
        return choiceBox;
    }

    private CheckBox breakTieRandomly() {
        CheckBox checkBox = new CheckBox();
        if(localSearchPhaseConfig.getForagerConfig().getBreakTieRandomly() != null)
            checkBox.setSelected(localSearchPhaseConfig.getForagerConfig().getBreakTieRandomly());

        checkBox.selectedProperty().addListener((ov, old_val, new_val) -> localSearchPhaseConfig.getForagerConfig().setBreakTieRandomly(new_val));
        return checkBox;
    }

    public static boolean isSelected() {
        return save.isSelected();
    }

    public GridPane getAcceptorGridPane() {
        return acceptorGridPane;
    }
}
