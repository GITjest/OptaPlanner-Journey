package journey.guifx.configuration.algorithm;

import javafx.collections.FXCollections;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.optaplanner.core.config.exhaustivesearch.ExhaustiveSearchPhaseConfig;
import org.optaplanner.core.config.exhaustivesearch.ExhaustiveSearchType;
import org.optaplanner.core.config.exhaustivesearch.NodeExplorationType;
import org.optaplanner.core.config.heuristic.selector.entity.EntitySorterManner;
import org.optaplanner.core.config.heuristic.selector.value.ValueSorterManner;

public class ExhaustiveSearch extends GridPane {
    private ExhaustiveSearchPhaseConfig exhaustiveSearchPhaseConfig;
    private static CheckBox save = new CheckBox();

    public ExhaustiveSearch(ExhaustiveSearchPhaseConfig exhaustiveSearchPhaseConfig) {
        if(exhaustiveSearchPhaseConfig == null) this.exhaustiveSearchPhaseConfig = new ExhaustiveSearchPhaseConfig();
        else this.exhaustiveSearchPhaseConfig = exhaustiveSearchPhaseConfig;

        getStyleClass().add("exhaustive-search");
        add(new Label("Exhaustive Search"), 0,0);
        add(save, 1, 0);
        add(new Label("Typ"), 0,1);
        add(exhaustiveSearchType(), 1, 1);
        add(new Label("nodeExplorationType  "), 0,2);
        add(nodeExplorationType(), 1, 2);
        add(new Label("entitySorterManner"), 0,3);
        add(entitySorterManner(), 1, 3);
        add(new Label("valueSorterManner"), 0,4);
        add(valueSorterManner(), 1, 4);
    }

    private ChoiceBox<ExhaustiveSearchType> exhaustiveSearchType() {
        ChoiceBox<ExhaustiveSearchType> choiceBox = new ChoiceBox<>();
        choiceBox.setItems(FXCollections.observableArrayList(ExhaustiveSearchType.values()));
        if (exhaustiveSearchPhaseConfig.getExhaustiveSearchType() != null)
            choiceBox.setValue(exhaustiveSearchPhaseConfig.getExhaustiveSearchType());
        choiceBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> exhaustiveSearchPhaseConfig.setExhaustiveSearchType(choiceBox.getItems().get((Integer) newValue)));
        return choiceBox;
    }

    private ChoiceBox<NodeExplorationType> nodeExplorationType() {
        ChoiceBox<NodeExplorationType> choiceBox = new ChoiceBox<>();
        choiceBox.setItems(FXCollections.observableArrayList(NodeExplorationType.values()));
        if (exhaustiveSearchPhaseConfig.getNodeExplorationType() != null)
            choiceBox.setValue(exhaustiveSearchPhaseConfig.getNodeExplorationType());
        choiceBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> exhaustiveSearchPhaseConfig.setNodeExplorationType(choiceBox.getItems().get((Integer) newValue)));
        return choiceBox;
    }

    private ChoiceBox<EntitySorterManner> entitySorterManner() {
        ChoiceBox<EntitySorterManner> choiceBox = new ChoiceBox<>();
        choiceBox.setItems(FXCollections.observableArrayList(EntitySorterManner.values()));
        if (exhaustiveSearchPhaseConfig.getEntitySorterManner() != null)
            choiceBox.setValue(exhaustiveSearchPhaseConfig.getEntitySorterManner());
        choiceBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> exhaustiveSearchPhaseConfig.setEntitySorterManner(choiceBox.getItems().get((Integer) newValue)));
        return choiceBox;
    }

    private ChoiceBox<ValueSorterManner> valueSorterManner() {
        ChoiceBox<ValueSorterManner> choiceBox = new ChoiceBox<>();
        choiceBox.setItems(FXCollections.observableArrayList(ValueSorterManner.values()));
        if (exhaustiveSearchPhaseConfig.getEntitySorterManner() != null)
            choiceBox.setValue(exhaustiveSearchPhaseConfig.getValueSorterManner());
        choiceBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> exhaustiveSearchPhaseConfig.setValueSorterManner(choiceBox.getItems().get((Integer) newValue)));
        return choiceBox;
    }

    public ExhaustiveSearchPhaseConfig getExhaustiveSearchPhaseConfig() {
        return exhaustiveSearchPhaseConfig;
    }

    public static boolean isSelected() {
        return save.isSelected();
    }
}
