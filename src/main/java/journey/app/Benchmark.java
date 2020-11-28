package journey.app;

import journey.constraints.generatorDRL.GeneratorDRL;
import journey.persistance.JourneyGenerator;

import org.optaplanner.benchmark.api.PlannerBenchmark;
import org.optaplanner.benchmark.api.PlannerBenchmarkFactory;

public class Benchmark {

    public static void main(String[] args) {
//        GeneratorDRL generatorDRL = new GeneratorDRL("src/main/resources/journey/properties/L-01-travel-benchmark.properties",
//                "src/main/resources/journey/solver/journeyBalancingConstraints.drl");
//        generatorDRL.generate();
//        PlannerBenchmarkFactory benchmarkFactory = PlannerBenchmarkFactory.createFromSolverConfigXmlResource("journey/solver/journeySolverConfig.xml");
//
//        JourneyGenerator generator = new JourneyGenerator();
//        PlannerBenchmark benchmark = benchmarkFactory.buildPlannerBenchmark(
//                generator.createJourneySolutionFromFile("src/main/resources/journey/properties/L-01-travel-benchmark.datx"),
//                generator.createJourneySolutionFromFile("src/main/resources/journey/properties/L-02-travel-benchmark.datx"),
//                generator.createJourneySolutionFromFile("src/main/resources/journey/properties/L-03-travel-benchmark.datx"));
//
//        benchmark.benchmarkAndShowReportInBrowser();
    }
}
