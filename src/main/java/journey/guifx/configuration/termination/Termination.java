package journey.guifx.configuration.termination;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.optaplanner.core.config.solver.termination.TerminationCompositionStyle;
import org.optaplanner.core.config.solver.termination.TerminationConfig;

public class Termination extends GridPane {
    private TerminationConfig terminationConfig;

    public Termination(TerminationConfig terminationConfig) {
        if(terminationConfig == null) this.terminationConfig = new TerminationConfig();
        else this.terminationConfig = terminationConfig;
        getStyleClass().add("termination");
        add(new Label("MillisecondsSpentLimit"), 0, 0);
        add(millisecondsSpentLimit(), 1, 0);
        add(new Label("SecondsSpentLimit"), 0, 1);
        add(secondsSpentLimit(), 1, 1);
        add(new Label("MinutesSpentLimit"), 0, 2);
        add(minutesSpentLimit(), 1, 2);
        add(new Label("HoursSpentLimit"), 0, 3);
        add(hoursSpentLimit(), 1, 3);
        add(new Label("DaysSpentLimit"), 0, 4);
        add(daysSpentLimit(), 1, 4);
        add(new Label("UnimprovedMillisecondsSpentLimit"), 0, 5);
        add(unimprovedMillisecondsSpentLimit(), 1, 5);
        add(new Label("UnimprovedSecondsSpentLimit"), 0, 6);
        add(unimprovedSecondsSpentLimit(), 1, 6);
        add(new Label("UnimprovedMinutesSpentLimit"), 0, 7);
        add(unimprovedMinutesSpentLimit(), 1, 7);
        add(new Label("UnimprovedHoursSpentLimit"), 0, 8);
        add(unimprovedHoursSpentLimit(), 1, 8);
        add(new Label("UnimprovedDaysSpentLimit"), 0, 9);
        add(unimprovedDaysSpentLimit(), 1, 9);
        add(new Label("BestScoreLimit"), 0, 10);
        add(bestScoreLimit(), 1, 10);
        add(new Label("BestScoreFeasible"), 0, 11);
        add(bestScoreFeasible(), 1, 11);
        add(new Label("StepCountLimit"), 0, 12);
        add(stepCountLimit(), 1, 12);
        add(new Label("UnimprovedStepCountLimit"), 0, 13);
        add(unimprovedStepCountLimit(), 1, 13);
        add(new Label("ScoreCalculationCountLimit"), 0, 14);
        add(scoreCalculationCountLimit(), 1, 14);
        add(new Label("TerminationCompositionStyle"), 0, 15);
        add(terminationCompositionStyle(), 1, 15);
    }

    private TextField millisecondsSpentLimit() {
        TextField textField = new TextField();
        if(terminationConfig.getMillisecondsSpentLimit() != null)
        textField.setText(String.valueOf(terminationConfig.getMillisecondsSpentLimit()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                terminationConfig.setMillisecondsSpentLimit(!textField.getText().equals("") ? Long.valueOf(textField.getText()) : null);
            }
        });
        return textField;
    }

    private TextField secondsSpentLimit() {
        TextField textField = new TextField();
        if(terminationConfig.getSecondsSpentLimit() != null)
        textField.setText(String.valueOf(terminationConfig.getSecondsSpentLimit()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                terminationConfig.setSecondsSpentLimit(!textField.getText().equals("") ? Long.valueOf(textField.getText()) : null);
            }
        });
        return textField;
    }

    private TextField minutesSpentLimit() {
        TextField textField = new TextField();
        if(terminationConfig.getMinutesSpentLimit() != null)
            textField.setText(String.valueOf(terminationConfig.getMinutesSpentLimit()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                terminationConfig.setMinutesSpentLimit(!textField.getText().equals("") ? Long.valueOf(textField.getText()) : null);
            }
        });
        return textField;
    }

    private TextField hoursSpentLimit() {
        TextField textField = new TextField();
        if(terminationConfig.getHoursSpentLimit() != null)
            textField.setText(String.valueOf(terminationConfig.getHoursSpentLimit()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                terminationConfig.setHoursSpentLimit(!textField.getText().equals("") ? Long.valueOf(textField.getText()) : null);
            }
        });
        return textField;
    }

    private TextField daysSpentLimit() {
        TextField textField = new TextField();
        if(terminationConfig.getDaysSpentLimit() != null)
            textField.setText(String.valueOf(terminationConfig.getDaysSpentLimit()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                terminationConfig.setDaysSpentLimit(!textField.getText().equals("") ? Long.valueOf(textField.getText()) : null);
            }
        });
        return textField;
    }

    private TextField unimprovedMillisecondsSpentLimit() {
        TextField textField = new TextField();
        if(terminationConfig.getUnimprovedMillisecondsSpentLimit() != null)
            textField.setText(String.valueOf(terminationConfig.getUnimprovedMillisecondsSpentLimit()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                terminationConfig.setUnimprovedMillisecondsSpentLimit(!textField.getText().equals("") ? Long.valueOf(textField.getText()) : null);
            }
        });
        return textField;
    }

    private TextField unimprovedSecondsSpentLimit() {
        TextField textField = new TextField();
        if(terminationConfig.getUnimprovedSecondsSpentLimit() != null)
            textField.setText(String.valueOf(terminationConfig.getUnimprovedSecondsSpentLimit()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                terminationConfig.setUnimprovedSecondsSpentLimit(!textField.getText().equals("") ? Long.valueOf(textField.getText()) : null);
            }
        });
        return textField;
    }

    private TextField unimprovedMinutesSpentLimit() {
        TextField textField = new TextField();
        if(terminationConfig.getUnimprovedMinutesSpentLimit() != null)
            textField.setText(String.valueOf(terminationConfig.getUnimprovedMinutesSpentLimit()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                terminationConfig.setUnimprovedMinutesSpentLimit(!textField.getText().equals("") ? Long.valueOf(textField.getText()) : null);
            }
        });
        return textField;
    }

    private TextField unimprovedHoursSpentLimit() {
        TextField textField = new TextField();
        if(terminationConfig.getUnimprovedHoursSpentLimit() != null)
            textField.setText(String.valueOf(terminationConfig.getUnimprovedHoursSpentLimit()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                terminationConfig.setUnimprovedHoursSpentLimit(!textField.getText().equals("") ? Long.valueOf(textField.getText()) : null);
            }
        });
        return textField;
    }

    private TextField unimprovedDaysSpentLimit() {
        TextField textField = new TextField();
        if(terminationConfig.getUnimprovedDaysSpentLimit() != null)
            textField.setText(String.valueOf(terminationConfig.getUnimprovedDaysSpentLimit()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                terminationConfig.setUnimprovedDaysSpentLimit(!textField.getText().equals("") ? Long.valueOf(textField.getText()) : null);
            }
        });
        return textField;
    }

    private TextField bestScoreLimit() {
        TextField textField = new TextField();
        if(terminationConfig.getBestScoreLimit() != null)
            textField.setText(terminationConfig.getBestScoreLimit());
        textField.setTooltip(new Tooltip("0hard/-5000soft"));
        textField.textProperty().addListener((observable, oldValue, newValue) -> terminationConfig.setBestScoreLimit(!textField.getText().equals("") ? textField.getText() : null));
        return textField;
    }

    private CheckBox bestScoreFeasible() {
        CheckBox checkBox = new CheckBox();
        if(terminationConfig.getBestScoreFeasible() != null)
            checkBox.setSelected(terminationConfig.getBestScoreFeasible());

        checkBox.selectedProperty().addListener((ov, old_val, new_val) -> terminationConfig.setBestScoreFeasible(new_val));
        return checkBox;
    }

    private TextField stepCountLimit() {
        TextField textField = new TextField();
        if(terminationConfig.getStepCountLimit() != null)
            textField.setText(String.valueOf(terminationConfig.getStepCountLimit()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                terminationConfig.setStepCountLimit(!textField.getText().equals("") ? Integer.valueOf(textField.getText()) : null);
            }
        });
        return textField;
    }

    private TextField unimprovedStepCountLimit() {
        TextField textField = new TextField();
        if(terminationConfig.getUnimprovedStepCountLimit() != null)
            textField.setText(String.valueOf(terminationConfig.getUnimprovedStepCountLimit()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                terminationConfig.setUnimprovedStepCountLimit(!textField.getText().equals("") ? Integer.valueOf(textField.getText()) : null);
            }
        });
        return textField;
    }

    private TextField scoreCalculationCountLimit() {
        TextField textField = new TextField();
        if(terminationConfig.getCalculateCountLimit() != null)
            textField.setText(String.valueOf(terminationConfig.getCalculateCountLimit()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                terminationConfig.setCalculateCountLimit(!textField.getText().equals("") ? Long.valueOf(textField.getText()) : null);
            }
        });
        return textField;
    }

    private ChoiceBox<TerminationCompositionStyle> terminationCompositionStyle() {
        ChoiceBox<TerminationCompositionStyle> choiceBox = new ChoiceBox<>();
        choiceBox.setItems(FXCollections.observableArrayList(TerminationCompositionStyle.values()));
        if(terminationConfig.getTerminationCompositionStyle() != null)
            choiceBox.setValue(terminationConfig.getTerminationCompositionStyle());
        choiceBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> terminationConfig.setTerminationCompositionStyle(choiceBox.getItems().get((Integer) newValue)));
        return choiceBox;
    }

    public TerminationConfig getTerminationConfig() {
        return terminationConfig;
    }
}
