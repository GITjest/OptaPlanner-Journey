package journey.constraints.generatorDRL.constraintDRL;

import org.springframework.expression.spel.ast.Operator;
import org.springframework.expression.spel.ast.SpelNodeImpl;

import java.util.*;

public class HardConstraintDRL extends ConstraintDRL {
    public static final String HARD_CONSTRAINT_MATCH = "scoreHolder.addHardConstraintMatch(kcontext, 1);";

    public HardConstraintDRL(Operator operator) {
        super(operator);
        List<Operator> nodes = new ArrayList<>();
        splitNode(operator, nodes);

        for (Operator rule : nodes) {
            String r = createRule(rule);
            if(r != null) {
                rules.put(rule.toStringAST(), r);
            } else {
                notAssignedRules.add(rule.toStringAST() + " - HARD");
            }
        }
    }

    private void splitNode(Operator operator, List<Operator> constraints) {
        if (operator.getOperatorName().equals("or")) {
            notAssignedRules.add(operator.toStringAST() + " - HARD");
            return;
        }
        if(operator.getLeftOperand().getChildCount() == 2) {
            splitNode((Operator) operator.getLeftOperand(), constraints);
        }
        if(operator.getRightOperand().getChildCount() == 2) {
            splitNode((Operator) operator.getRightOperand(), constraints);
        }
        if(operator.getLeftOperand().getChildCount() != 2 || operator.getRightOperand().getChildCount() != 2) {
            constraints.add(operator);
        }
    }

    private String createRule(Operator operator) {
        StringBuilder rule = new StringBuilder();
        SpelNodeImpl leftOperand = operator.getLeftOperand();
        SpelNodeImpl rightOperand = operator.getRightOperand();
        int leftChildCount = leftOperand.getChildCount();
        int rightChildCount = rightOperand.getChildCount();

        rule.append("\nrule \"").append(operator.toStringAST()).append(" - HARD").append("\"").append("\nwhen");

        if(leftChildCount == 4 && rightChildCount == 4) {
            rule.append("\n   ").append("$object1 : Offer(journeyStageId == ").append(getObjectId(leftOperand)).append(", $value1 : ").append(getAttribute(leftOperand)).append(")");
            rule.append("\n   ").append("$object2 : Offer(journeyStageId == ").append(getObjectId(rightOperand))
                    .append(", $value1 ").append(operator.getOperatorName()).append(" ").append(getAttribute(rightOperand)).append(")");
            rule.append("\n   ").append("JourneyStage(id == ").append(getObjectId(leftOperand)).append(", offer == $object1)");
            rule.append("\n   ").append("JourneyStage(id == ").append(getObjectId(rightOperand)).append(", offer == $object2)");
        } else if (leftChildCount == 4 && rightChildCount == 0) {
            rule.append("\n   ").append("$object1 : Offer(journeyStageId == ").append(getObjectId(leftOperand))
                    .append(", ").append(getAttribute(leftOperand)).append(" ").append(operator.getOperatorName()).append(" ").append(rightOperand.toStringAST()).append(")");
            rule.append("\n   ").append("JourneyStage(id == ").append(getObjectId(leftOperand)).append(", offer == $object1)");
        } else if (leftChildCount == 0 && rightChildCount == 4) {
            rule.append("\n   ").append("$object1 : Offer(journeyStageId == ").append(getObjectId(rightOperand))
                    .append(", ").append(leftOperand.toStringAST()).append(" ").append(operator.getOperatorName()).append(" ").append(getAttribute(rightOperand)).append(")");
            rule.append("\n   ").append("JourneyStage(id == ").append(getObjectId(rightOperand)).append(", offer == $object1)");
        } else {
            return null;
        }

        rule.append("\nthen\n").append("   ").append(HARD_CONSTRAINT_MATCH).append("\nend\n");
        return rule.toString();
    }
}
