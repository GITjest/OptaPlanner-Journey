package journey.constraints;

import journey.constraints.generatorDRL.GeneratorDRL;

import java.io.File;

public class App {

    public static void main(String args[]) {
        GeneratorDRL generatorDRL = new GeneratorDRL(new File("C:/Users/damia/Desktop/Magisterka/Journey/src/main/resources/journey/properties/L-01-travel-benchmark.properties"),
                new File("C:/Users/damia/Desktop/Magisterka/Journey/src/main/resources/journey/properties/L-01-travel-benchmark.drl"));
        generatorDRL.generate();
        System.out.println(generatorDRL.getNotAssignedRules().toString());
    }
}

