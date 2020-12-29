package journey.guifx.configuration.algorithm;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.optaplanner.core.config.constructionheuristic.ConstructionHeuristicPhaseConfig;
import org.optaplanner.core.config.exhaustivesearch.ExhaustiveSearchPhaseConfig;
import org.optaplanner.core.config.localsearch.LocalSearchPhaseConfig;
import org.optaplanner.core.config.phase.PhaseConfig;

import java.util.ArrayList;
import java.util.List;

public class Algorithm extends HBox {
    private List<PhaseConfig> phaseConfigs;
    private ExhaustiveSearchPhaseConfig exhaustiveSearchPhaseConfig;
    private ConstructionHeuristicPhaseConfig constructionHeuristicPhaseConfig;
    private LocalSearchPhaseConfig localSearchPhaseConfig;

    public Algorithm(List<PhaseConfig> phaseConfigs) {
        if(phaseConfigs == null) this.phaseConfigs = new ArrayList<>();
        else this.phaseConfigs = phaseConfigs;

        setPhaseConfigs();
        getStyleClass().add("algorithm");

        LocalSearch localSearch = new LocalSearch(localSearchPhaseConfig);
        getChildren().addAll(
                new VBox(new ExhaustiveSearch(exhaustiveSearchPhaseConfig),
                        new ConstructionHeuristic(constructionHeuristicPhaseConfig),
                        localSearch),
                localSearch.getAcceptorGridPane());
    }

    private void setPhaseConfigs() {
        for(PhaseConfig phaseConfig : phaseConfigs) {
            if(phaseConfig instanceof ExhaustiveSearchPhaseConfig)
                exhaustiveSearchPhaseConfig = (ExhaustiveSearchPhaseConfig) phaseConfig;

            if(phaseConfig instanceof ConstructionHeuristicPhaseConfig)
                constructionHeuristicPhaseConfig = (ConstructionHeuristicPhaseConfig) phaseConfig;

            if(phaseConfig instanceof LocalSearchPhaseConfig)
                localSearchPhaseConfig = (LocalSearchPhaseConfig) phaseConfig;
        }

        if(exhaustiveSearchPhaseConfig == null)
            exhaustiveSearchPhaseConfig = new ExhaustiveSearchPhaseConfig();

        if(constructionHeuristicPhaseConfig == null)
            constructionHeuristicPhaseConfig = new ConstructionHeuristicPhaseConfig();

        if(localSearchPhaseConfig == null)
            localSearchPhaseConfig = new LocalSearchPhaseConfig();
    }

    public List<PhaseConfig> getPhaseConfigs() {
        List<PhaseConfig> phaseConfigs = new ArrayList<>();
        if(ConstructionHeuristic.isSelected())
            phaseConfigs.add(constructionHeuristicPhaseConfig);
        if(LocalSearch.isSelected())
            phaseConfigs.add(localSearchPhaseConfig);
        if(ExhaustiveSearch.isSelected())
            phaseConfigs.add(exhaustiveSearchPhaseConfig);
        return phaseConfigs;
    }
}
