package journey.constraints;

import journey.constraints.generatorDRL.GeneratorDRL;

public class App {

    public static void main(String args[]) {
        GeneratorDRL generatorDRL = new GeneratorDRL("src/main/resources/journey/properties/L-07-travel-benchmark.properties",
                "src/main/resources/journey/solver/journeyBalancingConstraints.drl");
        generatorDRL.generate();
    }
}
