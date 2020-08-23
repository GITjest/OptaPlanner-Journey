package journey.constraints.generatorDRL.constraintDRL;

import org.springframework.expression.spel.ast.Operator;
import org.springframework.expression.spel.ast.SpelNodeImpl;

import java.util.*;

public class SoftConstraintDRL extends ConstraintDRL {
    public static final String SOFT_CONSTRAINT_MATCH = "scoreHolder.addSoftConstraintMatch(kcontext, (int) (%s));";
    private double multi = 1;

    public SoftConstraintDRL(Operator operator) {
        super(operator);
        List<SpelNodeImpl> nodes = new ArrayList<>();
        splitNode(pullOutMulti(operator), nodes);

        for (SpelNodeImpl spelNode : nodes) {
            String r = creteRule(spelNode);
            if(r != null) {
                rules.put(spelNode.toStringAST(), r);
            } else {
                notAssignedRules.add(spelNode.toStringAST());
            }
        }
    }

    private Operator pullOutMulti(Operator operator) {
        if(operator.getLeftOperand().getChildCount() < 2 && operator.getOperatorName().equals("*")) {
            multi = Double.parseDouble(operator.getLeftOperand().toStringAST());
            return (Operator) operator.getRightOperand();
        }
        return operator;
    }

    private void splitNode(Operator operator, List<SpelNodeImpl> operators) {
        SpelNodeImpl leftOperand = operator.getLeftOperand();
        SpelNodeImpl rightOperand = operator.getRightOperand();
        int leftChildCount = leftOperand.getChildCount();
        int rightChildCount = rightOperand.getChildCount();
        if(leftChildCount == 2 && rightChildCount == 2){
            splitNode((Operator) leftOperand, operators);
            splitNode((Operator) rightOperand, operators);
        }
        if(leftChildCount == 4 && rightChildCount == 2) {
            operators.add(leftOperand);
            splitNode((Operator) rightOperand, operators);
        }
        if(leftChildCount == 2 && rightChildCount == 4) {
            operators.add(rightOperand);
            splitNode((Operator) leftOperand, operators);
        }
        if((leftChildCount == 4 && rightChildCount == 4)) {
            String left = leftOperand.getChild(1).toStringAST();
            String right = rightOperand.getChild(1).toStringAST();
            if(left.equals(right)) {
                operators.add(operator);
            } else {
                operators.add(rightOperand);
                operators.add(leftOperand);
            }
        }
    }

    private String creteRule(SpelNodeImpl node) {
        StringBuilder rule = new StringBuilder();
        String constraint;
        rule.append("\nrule \"").append(node.toStringAST()).append(" - SOFT").append("\"").append("\nwhen");

        if(node.getChildCount() == 2) {
            Operator operator = (Operator) node;
            rule.append("\n   ").append("$object1 : Offer(journeyStageId == ").append(getObjectId(operator.getLeftOperand()))
                    .append(", $value1 : ").append(getAttribute(operator.getLeftOperand()))
                    .append(", $value2 : ").append(getAttribute(operator.getRightOperand())).append(")");
            rule.append("\n   ").append("JourneyStage(id == ").append(getObjectId(operator.getLeftOperand())).append(", offer == $object1)");
            constraint = multi + " * ($value1 " + operator.getOperatorName() + " $value2)";
        } else if(node.getChildCount() == 4) {
            rule.append("\n   ").append("$object1 : Offer(journeyStageId == ").append(getObjectId(node))
                    .append(", $value1 : ").append(getAttribute(node)).append(")");
            rule.append("\n   ").append("JourneyStage(id == ").append(getObjectId(node)).append(", offer == $object1)");
            constraint = multi + " * $value1";
        } else {
            return null;
        }
        rule.append("\nthen\n").append("   ").append(String.format(SOFT_CONSTRAINT_MATCH, constraint)).append("\nend\n");
        return rule.toString();
    }
}
