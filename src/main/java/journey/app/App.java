package journey.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import journey.constraints.generatorDRL.GeneratorDRL;
import journey.domain.*;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import journey.persistance.JourneyGenerator;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class App{

    public static void main(String[] args) {

//        GeneratorDRL generatorDRL = new GeneratorDRL("src/main/resources/journey/properties/L-01-travel-benchmark.properties",
//                "src/main/resources/journey/solver/journeyBalancingConstraints.drl");
//        generatorDRL.generate();
//
//        SolverFactory<JourneySolution> solverFactory = SolverFactory.createFromXmlResource("journey/solver/journeySolverConfig.xml");
//        Solver<JourneySolution> solver = solverFactory.buildSolver();
//        JourneySolution unsolvedJourneySolution = new JourneyGenerator().createJourneySolutionFromFile("src/main/resources/journey/properties/" + generatorDRL.getDataFile());
//        JourneySolution solvedJourneySolution = solver.solve(unsolvedJourneySolution);
//
//        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
//        System.out.println(threadMXBean.getCurrentThreadCpuTime() + " - " + threadMXBean.getCurrentThreadCpuTime()/1000000000);
//
//        System.out.println(toDisplayString(solvedJourneySolution));



    }



}
