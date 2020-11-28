package journey.guifx;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import journey.domain.JourneySolution;
import journey.domain.JourneyStage;
import journey.guifx.configuration.ConfigurationPanel;
import journey.guifx.configuration.EditConfigurationPanel;
import journey.guifx.logging.GuiAppender;
import journey.guifx.offerfx.OfferFX;
import journey.guifx.planICS.GA;
import journey.guifx.planICS.HSA;
import journey.persistance.JourneyGenerator;
import org.optaplanner.benchmark.api.PlannerBenchmark;
import org.optaplanner.benchmark.api.PlannerBenchmarkFactory;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class Layout {
    private static BorderPane borderPane;
    private static Button startOptaPlannerButton;
    private static Button startBenchmarkButton;
    private static Button startPlanICSButton;
    private ChoiceBox<String> choiceBox;
    private CheckBox checkBox;
    private static final ObservableList<OfferFX> result = FXCollections.observableArrayList();

    private static final Logger LOG = LoggerFactory.getLogger(Layout.class);

    public Layout(Stage primaryStage) {
        borderPane = new BorderPane();
        startOptaPlannerButton = new Button("Start");
        startOptaPlannerButton.setDisable(true);
        startOptaPlannerButton.getStyleClass().add("acceptButton");
        startOptaPlannerButton.setOnAction(event -> startSolver());

        startBenchmarkButton = new Button("Start Benchmark");
        startBenchmarkButton.setDisable(true);
        startBenchmarkButton.getStyleClass().add("acceptButton");
        startBenchmarkButton.setOnAction(event -> startBenchmark());

        startPlanICSButton = new Button("Start");
        startPlanICSButton.setDisable(true);
        startPlanICSButton.getStyleClass().add("acceptButton");
        startPlanICSButton.setOnAction(event -> startPlanICS());

        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        GuiAppender.setTextArea(textArea);

        VBox solverOptaPlanner = new VBox();
        HBox solverOptaPlannerHBox = new HBox();
        solverOptaPlannerHBox.getStyleClass().addAll("spacing", "upper-line");
        solverOptaPlannerHBox.getChildren().addAll(startOptaPlannerButton, startBenchmarkButton);
        solverOptaPlanner.getChildren().addAll(new Label("OptaPlanner"), solverOptaPlannerHBox);

        VBox solverPlanICS = new VBox();
        HBox solverPlanICSHBox = new HBox();
        solverPlanICSHBox.getStyleClass().addAll("spacing", "upper-line");
        checkBox = new CheckBox("csv");
        solverPlanICSHBox.setAlignment(Pos.CENTER_LEFT);

        choiceBox = new ChoiceBox<>(FXCollections.observableArrayList(Arrays.asList("HSA", "GA")));
        choiceBox.setValue("HSA");
        choiceBox.getStyleClass().add("smallButton");
        solverPlanICSHBox.getChildren().addAll(startPlanICSButton, choiceBox, checkBox);
        solverPlanICS.getChildren().addAll(new Label("PlanICS"), solverPlanICSHBox);

        TableView<OfferFX> table = new TableView<>();
        TableColumn nameCol = new TableColumn("Nazwa");
        nameCol.setCellValueFactory(new PropertyValueFactory<OfferFX, String>("name"));
        TableColumn localizationCol = new TableColumn("Lokalizacja");
        localizationCol.setCellValueFactory(new PropertyValueFactory<OfferFX, String>("localization"));
        TableColumn endLocalizationCol = new TableColumn("Koniec");
        endLocalizationCol.setCellValueFactory(new PropertyValueFactory<OfferFX, String>("endLocalization"));
        TableColumn startDateCol = new TableColumn("Data początkowa");
        startDateCol.setCellValueFactory(new PropertyValueFactory<OfferFX, String>("startDate"));
        TableColumn endDateCol = new TableColumn("Data koncowa");
        endDateCol.setCellValueFactory(new PropertyValueFactory<OfferFX, String>("endDate"));
        TableColumn priceCol = new TableColumn("Cena");
        priceCol.setCellValueFactory(new PropertyValueFactory<OfferFX, String>("price"));
        TableColumn comfortCol = new TableColumn("Komfort");
        comfortCol.setCellValueFactory(new PropertyValueFactory<OfferFX, String>("comfort"));
        table.setItems(result);
        table.setPlaceholder(new Label(""));
        table.getColumns().addAll(nameCol, localizationCol, endLocalizationCol, startDateCol, endDateCol, priceCol, comfortCol);

        final VBox vBox = new VBox();
        vBox.getStyleClass().addAll("padding");
        ConfigurationPanel configurationPanel = new ConfigurationPanel(primaryStage);
        vBox.getChildren().addAll(configurationPanel, solverOptaPlanner, solverPlanICS, textArea, table);

        borderPane.setLeft(vBox);
    }

    public static BorderPane getLayout() {
        return borderPane;
    }

    public static Button getStartOptaPlannerButton() {
        return startOptaPlannerButton;
    }

    public static Button getStartBenchmarkButton() {
        return startBenchmarkButton;
    }

    public static Button getStartPlanICSButton() {
        return startPlanICSButton;
    }

    public void startSolver() {
        Thread thread = new Thread(() -> {
            SolverFactory<JourneySolution> solverFactory = SolverFactory.create(EditConfigurationPanel.getSolverConfig());
            Solver<JourneySolution> solver = solverFactory.buildSolver();
            JourneySolution unsolvedJourneySolution = new JourneyGenerator().createJourneySolutionFromFile(ConfigurationPanel.getDataTextField().getText());
            JourneySolution solvedJourneySolution = solver.solve(unsolvedJourneySolution);
            System.out.println(toDisplayString(solvedJourneySolution));
            addData(solvedJourneySolution);
            Platform.runLater(() -> {
                startOptaPlannerButton.setText("Start");
                startOptaPlannerButton.setOnAction(event1 -> startSolver());
                ConfigurationPanel.disableStartButtons();
            });
        });
        thread.start();

        startOptaPlannerButton.setText("Stop");
        startOptaPlannerButton.setOnAction(event -> {
            thread.interrupt();
            startOptaPlannerButton.setText("Start");
            startOptaPlannerButton.setOnAction(event1 -> startSolver());
            ConfigurationPanel.disableStartButtons();
        });
        startBenchmarkButton.setDisable(true);
        startPlanICSButton.setDisable(true);
    }

    public static String toDisplayString(JourneySolution journeySolution) {
        StringBuilder displayString = new StringBuilder();
        for(JourneyStage journeyStage : journeySolution.getJourneyStage()) {
            displayString.append(journeyStage).append("\n");
        }
        return displayString.toString();
    }

    private void addData(JourneySolution journeySolution) {
        result.clear();
        for(JourneyStage journeyStage : journeySolution.getJourneyStage()) {
            result.add(new OfferFX(journeyStage.getName(), journeyStage.getOffer()));
        }
    }

    public void startBenchmark() {
        Thread thread = new Thread(() -> {
            PlannerBenchmarkFactory benchmarkFactory = PlannerBenchmarkFactory.createFromSolverFactory(SolverFactory.create(EditConfigurationPanel.getSolverConfig()));
            PlannerBenchmark benchmark = benchmarkFactory.buildPlannerBenchmark(new JourneyGenerator().createJourneySolutionFromFile(ConfigurationPanel.getDataTextField().getText()));
            benchmark.benchmarkAndShowReportInBrowser();
            Platform.runLater(() -> {
                startBenchmarkButton.setText("Start Benchmark");
                startBenchmarkButton.setOnAction(event1 -> startBenchmark());
                ConfigurationPanel.disableStartButtons();
            });
        });
        thread.start();

        startBenchmarkButton.setText("Stop");
        startBenchmarkButton.setOnAction(event -> {
            thread.interrupt();
            startBenchmarkButton.setText("Start Benchmark");
            startBenchmarkButton.setOnAction(event1 -> startBenchmark());
            ConfigurationPanel.disableStartButtons();
        });
        startOptaPlannerButton.setDisable(true);
        startPlanICSButton.setDisable(true);
    }

    public void startPlanICS() {
        Thread thread = new Thread(() -> {
            switch (choiceBox.getSelectionModel().getSelectedItem()) {
                case "HSA": new HSA(checkBox.isSelected()).start(); break;
                case "GA": new GA(checkBox.isSelected()).start(); break;
            }

            Platform.runLater(() -> {
                startPlanICSButton.setText("Start");
                startPlanICSButton.setOnAction(event1 -> startPlanICS());
                ConfigurationPanel.disableStartButtons();
            });
        });
        thread.start();

        startPlanICSButton.setText("Stop");
        startPlanICSButton.setOnAction(event -> {
            thread.interrupt();
            startPlanICSButton.setText("Start");
            startPlanICSButton.setOnAction(event1 -> startPlanICS());
            ConfigurationPanel.disableStartButtons();
        });
        startOptaPlannerButton.setDisable(true);
        startBenchmarkButton.setDisable(true);
    }

    public static ObservableList<OfferFX> getResult() {
        return result;
    }
}
