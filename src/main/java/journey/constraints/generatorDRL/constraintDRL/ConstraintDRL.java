package journey.constraints.generatorDRL.constraintDRL;

import org.springframework.expression.spel.ast.Operator;
import org.springframework.expression.spel.ast.SpelNodeImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ConstraintDRL {
    protected Map<String, String> rules = new HashMap<>();
    protected List<String> notAssignedRules = new ArrayList<>();
    protected Operator operator;

    public ConstraintDRL(Operator operator) {
        this.operator = operator;
    }

    protected String getObjectId(SpelNodeImpl node) {
        return node.getChild(1).toStringAST().replaceAll("\\[", "").replaceAll("]", "");
    }

    protected String getAttribute(SpelNodeImpl node) {
        String attr = node.getChild(2).toStringAST().equals("i") ? "integers" : "reals";
        return attr + node.getChild(3).toStringAST();
    }

    public Map<String, String> getRules() {
        return rules;
    }

    public List<String> getNotAssignedRules() {
        return notAssignedRules;
    }
}
