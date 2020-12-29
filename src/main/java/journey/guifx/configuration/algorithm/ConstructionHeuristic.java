package journey.guifx.configuration.algorithm;

import javafx.collections.FXCollections;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.optaplanner.core.config.constructionheuristic.ConstructionHeuristicPhaseConfig;
import org.optaplanner.core.config.constructionheuristic.ConstructionHeuristicType;
import org.optaplanner.core.config.heuristic.selector.entity.EntitySorterManner;
import org.optaplanner.core.config.heuristic.selector.value.ValueSorterManner;

public class ConstructionHeuristic extends GridPane {
    private ConstructionHeuristicPhaseConfig constructionHeuristicPhaseConfig;
    private static CheckBox save = new CheckBox();

    public ConstructionHeuristic(ConstructionHeuristicPhaseConfig constructionHeuristicPhaseConfig) {
        if(constructionHeuristicPhaseConfig == null) this.constructionHeuristicPhaseConfig = new ConstructionHeuristicPhaseConfig();
        else this.constructionHeuristicPhaseConfig = constructionHeuristicPhaseConfig;

        getStyleClass().add("construction-heuristic");
        add(new Label("Construction Heuristic"), 0,0);
        add(save, 1, 0);
        add(new Label("Typ"), 0,1);
        add(constructionHeuristicType(), 1, 1);
        add(new Label("entitySorterManner"), 0,2);
        add(entitySorterManner(), 1, 2);
        add(new Label("valueSorterManner"), 0,3);
        add(valueSorterManner(), 1, 3);
    }

    private ChoiceBox<ConstructionHeuristicType> constructionHeuristicType() {
        ChoiceBox<ConstructionHeuristicType> choiceBox = new ChoiceBox<>();
        choiceBox.setItems(FXCollections.observableArrayList(ConstructionHeuristicType.values()));
        if (constructionHeuristicPhaseConfig.getConstructionHeuristicType() != null)
            choiceBox.setValue(constructionHeuristicPhaseConfig.getConstructionHeuristicType());
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> constructionHeuristicPhaseConfig.setConstructionHeuristicType(choiceBox.getItems().get((Integer) newValue)));
        return choiceBox;
    }

    private ChoiceBox<EntitySorterManner> entitySorterManner() {
        ChoiceBox<EntitySorterManner> choiceBox = new ChoiceBox<>();
        choiceBox.setItems(FXCollections.observableArrayList(EntitySorterManner.values()));
        if (constructionHeuristicPhaseConfig.getEntitySorterManner() != null)
            choiceBox.setValue(constructionHeuristicPhaseConfig.getEntitySorterManner());
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> constructionHeuristicPhaseConfig.setEntitySorterManner(choiceBox.getItems().get((Integer) newValue)));
        return choiceBox;
    }

    private ChoiceBox<ValueSorterManner> valueSorterManner() {
        ChoiceBox<ValueSorterManner> choiceBox = new ChoiceBox<>();
        choiceBox.setItems(FXCollections.observableArrayList(ValueSorterManner.values()));
        if (constructionHeuristicPhaseConfig.getEntitySorterManner() != null)
            choiceBox.setValue(constructionHeuristicPhaseConfig.getValueSorterManner());
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> constructionHeuristicPhaseConfig.setValueSorterManner(choiceBox.getItems().get((Integer) newValue)));
        return choiceBox;
    }

    public ConstructionHeuristicPhaseConfig getConstructionHeuristicPhaseConfig() {
        return constructionHeuristicPhaseConfig;
    }

    public static boolean isSelected() {
        return save.isSelected();
    }
}
