package journey.guifx.configuration;

import com.thoughtworks.xstream.XStream;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import journey.domain.JourneySolution;
import journey.domain.JourneyStage;
import journey.guifx.configuration.algorithm.Algorithm;
import journey.guifx.configuration.termination.Termination;
import org.optaplanner.core.config.constructionheuristic.ConstructionHeuristicPhaseConfig;
import org.optaplanner.core.config.exhaustivesearch.ExhaustiveSearchPhaseConfig;
import org.optaplanner.core.config.localsearch.LocalSearchPhaseConfig;
import org.optaplanner.core.config.localsearch.decider.acceptor.AcceptorConfig;
import org.optaplanner.core.config.localsearch.decider.acceptor.AcceptorType;
import org.optaplanner.core.config.localsearch.decider.forager.LocalSearchForagerConfig;
import org.optaplanner.core.config.score.director.ScoreDirectorFactoryConfig;
import org.optaplanner.core.config.solver.SolverConfig;
import org.optaplanner.core.config.solver.termination.TerminationConfig;

import java.io.*;
import java.util.Collections;

public class EditConfigurationPanel extends VBox {
    private static SolverConfig solverConfig = new SolverConfig();
    private Stage window;

    public EditConfigurationPanel(String solverConfigResource) {
        if(solverConfigResource != null) {
            solverConfig = SolverConfig.createFromXmlFile(new File(solverConfigResource));
        }
        init();
    }

    public EditConfigurationPanel(SolverConfig config) {
        if(config != null) {
            solverConfig = config;
        }
        init();
    }

    private void init() {
        solverConfig.setSolutionClass(JourneySolution.class);
        solverConfig.setEntityClassList(Collections.singletonList(JourneyStage.class));

        window = new Stage();
        window.initModality(Modality.WINDOW_MODAL);
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setTitle("Configuration");

        getStyleClass().add("edit-configuration-panel");

        Termination termination = new Termination(solverConfig.getTerminationConfig());
        Algorithm algorithm = new Algorithm(solverConfig.getPhaseConfigList());

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                //  new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("XML", "*.xml")
        );

        Button fileChooserButton = new Button("Zapisz");
        fileChooserButton.setOnAction(e -> {
            File file = fileChooser.showSaveDialog(window);
            if (file != null) {
                solverConfig.setTerminationConfig(termination.getTerminationConfig());
                solverConfig.setPhaseConfigList(algorithm.getPhaseConfigs());
                saveConfiguration(file);
                window.close();
            }
        });

        Button closeButton = new Button("Zamknij");
        closeButton.getStyleClass().add("danger-button");
        closeButton.setOnAction(event -> {
            solverConfig.setTerminationConfig(termination.getTerminationConfig());
            solverConfig.setPhaseConfigList(algorithm.getPhaseConfigs());
            window.close();
        });

        HBox buttons = new HBox();
        buttons.getStyleClass().addAll("spacing", "padding", "configuration-buttons");
        buttons.getChildren().addAll(fileChooserButton, closeButton);

        getChildren().addAll(
                new HBox(new VBox(new Label("Termination"), termination),
                        new VBox(new Label("Algorytmy"), algorithm)),
                buttons);

        Scene scene = new Scene(this);
        scene.getStylesheets().add(this.getClass().getResource("/style/style.css").toExternalForm());
        window.setScene(scene);
        window.show();
    }

    private void saveConfiguration(File file) {
        SolverConfig s = new SolverConfig();
        s.setSolutionClass(null);
        s.setEntityClassList(null);
        s.setScoreDirectorFactoryConfig(null);
        s.setTerminationConfig(solverConfig.getTerminationConfig());
        s.setPhaseConfigList(solverConfig.getPhaseConfigList());
        XStream xStream = new XStream();
        xStream.addImplicitCollection(SolverConfig.class, "phaseConfigList");
        xStream.addImplicitCollection(AcceptorConfig.class, "acceptorTypeList");
        xStream.alias("solver", SolverConfig.class);
        xStream.alias("termination", TerminationConfig.class);
        xStream.alias("exhaustiveSearch", ExhaustiveSearchPhaseConfig.class);
        xStream.alias("constructionHeuristic", ConstructionHeuristicPhaseConfig.class);
        xStream.alias("localSearch", LocalSearchPhaseConfig.class);
        xStream.alias("acceptor", AcceptorConfig.class);
        xStream.alias("acceptorType", AcceptorType.class);
        xStream.alias("forager", LocalSearchForagerConfig.class);

        String dateXml = xStream.toXML(s);
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(dateXml);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SolverConfig getSolverConfig() {
        solverConfig.setScoreDirectorFactoryConfig(new ScoreDirectorFactoryConfig());
        solverConfig.getScoreDirectorFactoryConfig().setScoreDrlFileList(Collections.singletonList(ConfigurationPanel.getDrlFile()));
        return solverConfig;
    }

}
