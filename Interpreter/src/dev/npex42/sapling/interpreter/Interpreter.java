package dev.npex42.sapling.interpreter;

import dev.npex42.sapling.InvalidType;
import dev.npex42.sapling.errors.InvalidOperation;
import dev.npex42.sapling.parser.ast.operators.BinaryOp;
import dev.npex42.sapling.parser.ast.operators.UnaryOp;
import dev.npex42.sapling.parser.ast.values.*;

import java.util.HashMap;

public class Interpreter {

    private final HashMap<String, Integer> variables = new HashMap<>();

    public Object Evaluate(Expression expr) {

        if (expr == null) {
            return 0;
        }

        System.out.println("Current Node: " + expr);

        if (expr instanceof IntNode) {
            System.out.println("IntNode");
            return EvaluateInteger((IntNode) expr);
        } else if (expr instanceof StringNode) {
            return EvaluateString((StringNode) expr);
        } else if (expr instanceof IdentNode) {
            return EvaluateIdentifier((IdentNode) expr);
        } else if (expr instanceof UnaryOp) {
            return EvaluateUnary((UnaryOp) expr);
        } else if (expr instanceof BinaryOp) {
            return EvaluateBinary((BinaryOp) expr);
        }


        throw new RuntimeException("Unrecognised Node: " + expr.getClass());
    }

    private Object EvaluateBinary(BinaryOp expr) {
        Object lhs_object = Evaluate(expr.lhs);
        Object rhs_object = Evaluate(expr.lhs);
        if (lhs_object instanceof Integer && rhs_object instanceof Integer) {
            Integer lhs = (Integer) lhs_object;
            Integer rhs = (Integer) rhs_object;

            switch (expr.op) {
                case PLUS -> {
                    return rhs + lhs;
                }
                case MINUS -> {
                    return rhs - lhs;
                }
                case SLASH -> {
                    return rhs / lhs;
                }
                case STAR -> {
                    return rhs * lhs;
                }
                default -> {
                    throw new InvalidOperation("Invalid Operator '" + expr.op + "'");
                }
            }
        } else {
            throw new InvalidType("Cannot Negate Node Of Type " + expr.getClass());
        }
    }

    private int EvaluateUnary(UnaryOp expr) {
        Object result = Evaluate(expr.expr);
        if (result instanceof Integer) {
            return -((Integer) result);
        } else {
            throw new InvalidType("Cannot Negate Node Of Type " + expr.getClass());
        }
    }

    private int EvaluateInteger(ValueNode<Integer> expr) {
        return expr.value();
    }

    private String EvaluateString(ValueNode<String> expr) {
        return expr.value();
    }

    private int EvaluateIdentifier(ValueNode<String> expr) {
        return variables.get(expr.value());
    }


}