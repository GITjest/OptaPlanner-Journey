package journey.app;

import journey.constraints.generatorDRL.GeneratorDRL;
import journey.domain.JourneySolution;
import journey.domain.JourneyStage;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import journey.persistance.JourneyGenerator;

import java.io.File;

public class AppOptaPlanner {
    public static void main(String[] args) {
       // BasicConfigurator.configure();
//        GeneratorDRL generatorDRL = new GeneratorDRL("src/main/resources/journey/properties/L-01-travel-benchmark.properties",
//                "src/main/resources/journey/solver/journeyBalancingConstraints.drl");
//        generatorDRL.generate();
//
//        SolverFactory<JourneySolution> solverFactory = SolverFactory.createFromXmlResource("journey/solver/journeySolverConfig.xml");
//        Solver<JourneySolution> solver = solverFactory.buildSolver();
//        JourneySolution unsolvedJourneySolution = new JourneyGenerator().createJourneySolutionFromFile("src/main/resources/journey/properties/" + generatorDRL.getDataFile());
//        JourneySolution solvedJourneySolution = solver.solve(unsolvedJourneySolution);
//
//       // ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
//       // System.out.println(threadMXBean.getCurrentThreadCpuTime() + " - " + threadMXBean.getCurrentThreadCpuTime()/1000000000);
//
//        System.out.println(toDisplayString(solvedJourneySolution));
    }

    public static String toDisplayString(JourneySolution journeySolution) {
        StringBuilder displayString = new StringBuilder();
        for(JourneyStage journeyStage : journeySolution.getJourneyStage()) {
            displayString.append(journeyStage).append("\n");
        }
        return displayString.toString();
    }
}
