package journey.guifx.configuration;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import journey.constraints.generatorDRL.GeneratorDRL;
import journey.guifx.Layout;
import org.optaplanner.core.config.constructionheuristic.ConstructionHeuristicPhaseConfig;
import org.optaplanner.core.config.localsearch.LocalSearchPhaseConfig;
import org.optaplanner.core.config.solver.SolverConfig;

import java.io.File;

public class ConfigurationPanel extends VBox {
    private static TextField dataTextField = new TextField();
    private static TextField propertyTextField = new TextField();
    private static TextField configurationTextField = new TextField();
    private Stage primaryStage;

    public ConfigurationPanel(Stage primaryStage) {
        this.primaryStage = primaryStage;
        init();
    }

    private void init() {
        Label dataLabel = new Label("Dane");
        dataTextField.getStyleClass().add("fileNameTextField");
        dataTextField.textProperty().addListener((observable, oldValue, newValue) -> disableStartButtons());
        FileChooser dataFileChooser = new FileChooser();
        dataFileChooser.getExtensionFilters().addAll(
                //  new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("DATX", "*.datx")
        );

        Button dataFileChooserButton = new Button("Wybierz plik");
        dataFileChooserButton.setOnAction(e -> {
            File file = dataFileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                dataTextField.setText(file.getPath());
            }
        });

        HBox dataHBox = new HBox();
        dataHBox.getStyleClass().add("spacing");
        dataHBox.getChildren().addAll(dataTextField, dataFileChooserButton);

        Label propertyLabel = new Label("Ograniczenia");
        propertyTextField.getStyleClass().add("fileNameTextField");
        propertyTextField.textProperty().addListener((observable, oldValue, newValue) -> disableStartButtons());
        FileChooser propertyFileChooser = new FileChooser();
        propertyFileChooser.getExtensionFilters().addAll(
                //  new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("PROPERTIES", "*.properties")
        );

        Button propertyFileChooserButton = new Button("Wybierz plik");
        propertyFileChooserButton.setOnAction(e -> {
            File file = propertyFileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                propertyTextField.setText(file.getPath());
                GeneratorDRL drl = new GeneratorDRL(file, new File(file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(".")) + ".drl"));
                drl.generate();

                File data = new File(file.getParentFile() + "/" + drl.getDataFile());
                if(data.exists() && dataTextField.getText().equals("")) {
                    dataTextField.setText(data.getPath());
                }
            }
        });

        HBox propertyHBox = new HBox();
        propertyHBox.getStyleClass().add("spacing");
        propertyHBox.getChildren().addAll(propertyTextField, propertyFileChooserButton);

        Label configurationLabel = new Label("Plik konfiguracyjny");
        configurationTextField.getStyleClass().add("fileNameTextField");
        configurationTextField.textProperty().addListener((observable, oldValue, newValue) -> disableStartButtons());
        FileChooser configurationFileChooser = new FileChooser();
        configurationFileChooser.getExtensionFilters().addAll(
                //  new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("XML", "*.xml")
        );

        Button configurationFileChooserButton = new Button("Wybierz plik");
        configurationFileChooserButton.getStyleClass().add("smallButton");
        configurationFileChooserButton.setOnAction(e -> {
            File file = configurationFileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                configurationTextField.setText(file.getPath());
                Layout.getLayout().setCenter(new EditConfigurationPanel(file.getPath(), primaryStage));
            }
        });
        Button createButton = new Button("Stwórz plik");
        createButton.setOnAction(event -> {
            Layout.getLayout().setCenter(new EditConfigurationPanel(EditConfigurationPanel.getSolverConfig(), primaryStage));
        });
        createButton.getStyleClass().add("smallButton");
        HBox configurationHBox = new HBox();
        configurationHBox.getStyleClass().add("spacing");
        configurationHBox.getChildren().addAll(configurationTextField, configurationFileChooserButton, createButton);

        getChildren().addAll(propertyLabel, propertyHBox, dataLabel, dataHBox, configurationLabel, configurationHBox);
    }

    public static void disableStartButtons() {
        boolean disable = propertyTextField.getText().equals("") || dataTextField.getText().equals("") || configurationTextField.getText().equals("");
        Layout.getStartOptaPlannerButton().setDisable(disable);
        Layout.getStartBenchmarkButton().setDisable(disable);
        Layout.getStartPlanICSButton().setDisable(propertyTextField.getText().equals("") || dataTextField.getText().equals(""));
    }

    public static TextField getDataTextField() {
        return dataTextField;
    }

    public static TextField getPropertyTextField() {
        return propertyTextField;
    }

    public static TextField getConfigurationTextField() {
        return configurationTextField;
    }






    private void create() {
        SolverConfig solverConfig = SolverConfig.createFromXmlFile(new File("C:/Users/damia/Desktop/Magisterka/Journey/src/main/resources/journey/solver/journeySolverConfig.xml"));

        System.out.println(solverConfig.getPhaseConfigList());
        ConstructionHeuristicPhaseConfig c = (ConstructionHeuristicPhaseConfig) solverConfig.getPhaseConfigList().get(0);
        LocalSearchPhaseConfig c1 = (LocalSearchPhaseConfig) solverConfig.getPhaseConfigList().get(1);
        System.out.println(c1.getForagerConfig().getAcceptedCountLimit());
    }


}
