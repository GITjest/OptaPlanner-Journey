package journey.guifx.configuration;

import com.thoughtworks.xstream.XStream;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import journey.domain.JourneySolution;
import journey.domain.JourneyStage;
import journey.guifx.Layout;
import org.optaplanner.core.config.solver.SolverConfig;

import java.io.*;
import java.util.Collections;

public class EditConfigurationPanel extends VBox {
    private static SolverConfig solverConfig;
    private Stage primaryStage;

    public EditConfigurationPanel(String solverConfigResource, Stage primaryStage) {
        this.primaryStage = primaryStage;
        solverConfig = new SolverConfig();
        if(solverConfigResource != null) {
            solverConfig = SolverConfig.createFromXmlFile(new File(solverConfigResource));
        }
        solverConfig.setSolutionClass(JourneySolution.class);
        solverConfig.setEntityClassList(Collections.singletonList(JourneyStage.class));
        init();
    }

    public EditConfigurationPanel(SolverConfig config, Stage primaryStage) {
        this.primaryStage = primaryStage;
        if(config == null) {
            solverConfig = new SolverConfig();
        } else {
            solverConfig = config;
        }
        solverConfig.setSolutionClass(JourneySolution.class);
        solverConfig.setEntityClassList(Collections.singletonList(JourneyStage.class));
        init();
    }

    private void init() {
        getStyleClass().add("edit-configuration-panel");
        VBox vBox = new VBox();
        Termination termination = new Termination(solverConfig.getTerminationConfig());
        vBox.getChildren().addAll(new Label("Termination"), termination);

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                //  new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("XML", "*.xml")
        );

        Button fileChooserButton = new Button("Zapisz");
        fileChooserButton.setOnAction(e -> {
            File file = fileChooser.showSaveDialog(primaryStage);
            if (file != null) {
                solverConfig.setTerminationConfig(termination.getTerminationConfig());
                saveConfiguration(file);
            }
        });

        Button closeButton = new Button("Zamknij");
        closeButton.getStyleClass().add("dangerButton");
        closeButton.setOnAction(event -> {
            solverConfig.setTerminationConfig(termination.getTerminationConfig());
            Layout.getLayout().setCenter(null);
        });
        HBox hBox = new HBox();
        hBox.getStyleClass().addAll("spacing", "padding");
        hBox.getChildren().addAll(fileChooserButton, closeButton);

        getChildren().addAll(vBox, hBox);
    }

    private void saveConfiguration(File file) {
        SolverConfig s = new SolverConfig();
        s.setSolutionClass(null);
        s.setEntityClassList(null);
        s.setScoreDirectorFactoryConfig(null);
        s.setTerminationConfig(solverConfig.getTerminationConfig());
        XStream xStream = new XStream();
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
        return solverConfig;
    }

}
