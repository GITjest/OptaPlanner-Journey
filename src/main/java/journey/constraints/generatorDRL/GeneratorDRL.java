package journey.constraints.generatorDRL;

import journey.constraints.generatorDRL.constraintDRL.ConstraintDRL;
import journey.constraints.generatorDRL.constraintDRL.HardConstraintDRL;
import journey.constraints.generatorDRL.constraintDRL.SoftConstraintDRL;
import org.springframework.expression.spel.ast.Operator;
import pl.planics.concrete.constraints.ConcreteConstraint;
import pl.planics.concrete.constraints.ConcreteExpr;
import pl.planics.concrete.constraints.ReadConstraints;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GeneratorDRL {
    private final String SCORE_HOLDER = "global HardSoftDoubleScoreHolder scoreHolder;";
    private final String[] IMPORTS =
            {"import org.optaplanner.core.api.score.buildin.hardsoftdouble.HardSoftDoubleScoreHolder;",
                    "import journey.domain.JourneySolution;",
                    "import journey.domain.Offer;",
                    "import journey.domain.JourneyStage;"};

    private File drlFile;
    private ReadConstraints readConstraints;
    private Map<String, String> rules = new LinkedHashMap<>();
    private List<String> notAssignedRules = new ArrayList<>();

    public GeneratorDRL(File propertiesFile, File drlFile) {
        this.drlFile = drlFile;

        readConstraints = new ReadConstraints(propertiesFile.getAbsolutePath());
        for (ConcreteConstraint concreteConstraint : readConstraints.getConstraints()) {
            ConstraintDRL hardConstraintDRL = new HardConstraintDRL((Operator) concreteConstraint.getAstNode());
            rules.putAll(hardConstraintDRL.getRules());
            notAssignedRules.addAll(hardConstraintDRL.getNotAssignedRules());
        }
        for (ConcreteExpr<Double> concreteExpr : readConstraints.getQuality().getFormList()) {
            ConstraintDRL softConstraintDRL = new SoftConstraintDRL((Operator) concreteExpr.getAstNode());
            rules.putAll(softConstraintDRL.getRules());
            notAssignedRules.addAll(softConstraintDRL.getNotAssignedRules());
        }
    }

    public void generate() {
        try {
            FileWriter fileWriter = new FileWriter(drlFile);
            for (String i : IMPORTS) {
                fileWriter.write(i + "\n");
            }
            fileWriter.write(SCORE_HOLDER + "\n");

            for(String rule : rules.values()) {
                fileWriter.write(rule);
            }

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getRules() {
        return rules;
    }

    public List<String> getNotAssignedRules() {
        return notAssignedRules;
    }

    public String getDataFile() {
        return readConstraints.getDataFile();
    }
}
