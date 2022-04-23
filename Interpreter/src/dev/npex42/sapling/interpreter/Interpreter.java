package dev.npex42.sapling.interpreter;

import dev.npex42.sapling.InvalidType;
import dev.npex42.sapling.SyntaxNode;
import dev.npex42.sapling.errors.InvalidOperation;
import dev.npex42.sapling.parser.ast.operators.*;
import dev.npex42.sapling.parser.ast.statements.*;
import dev.npex42.sapling.parser.ast.values.*;
import dev.npex42.sapling.tokens.*;

import java.util.HashMap;

public class Interpreter {

    private final HashMap<String, Object> variables = new HashMap<>();


    public Object EvaluateLookup(IdentNode lookup) {
        if (!variables.containsKey(lookup.stringValue())) {
            throw new UndefinedVariable(lookup.stringValue());
        }

        return variables.get(lookup.stringValue());
    }

    public Object Evaluate(SyntaxNode node) {
        if (node instanceof Print) {
            return EvaluatePrint((Print) node);
        } else if (node instanceof Expression) {
            return EvaluateExpression((Expression) node);
        } else if (node instanceof Variable) {
            return EvaluateAssignment((Variable) node);
        } else if (node instanceof Block) {
            return EvalBlock((Block) node);
        } else {
            throw new InvalidType("Unrecognised Node Type.");
        }
    }

    public Object EvalBlock(Block block) {
        Object blockResult = null;
        for (SyntaxNode statement : block.statements) {
            blockResult = Evaluate(statement);
        }
        return blockResult;
    }

    public Object EvaluateAssignment(Variable var) {
        if (var.init != null) {
            variables.put(var.name.stringValue(), EvaluateExpression(var.init));
        } else {
            variables.put(var.name.stringValue(), 0);
        }

        return null;
    }

    public Object EvaluatePrint(Print print) {
        System.out.println(EvaluateExpression(print.expr));
        return null;
    }

    public Object EvaluateExpression(Expression expr) {
        if (expr instanceof BinaryOp) {
            return EvaluateBinary((BinaryOp) expr);
        } else if (expr instanceof UnaryOp) {
            return EvaluateUnary((UnaryOp) expr);
        } else if (expr instanceof IntNode) {
            return ((IntNode) expr).intValue();
        } else if (expr instanceof StringNode) {
            return ((StringNode) expr).value();
        } else if (expr instanceof IdentNode) {
            return EvaluateLookup((IdentNode) expr);
        }

        throw new InvalidOperation("Unrecognised Node: " + expr);
    }

    private Object EvaluateUnary(UnaryOp expr) {
        Object rhs = EvaluateExpression(expr.expr);
        if (rhs instanceof Integer) {
            return -((Integer) rhs);
        } else {
            throw new InvalidOperation("Can't Negate A " + rhs);
        }
    }

    private Object EvaluateBinary(BinaryOp expr) {
        Object lhs = EvaluateExpression(expr.lhs);
        Object rhs = EvaluateExpression(expr.rhs);

        if (rhs instanceof Integer && lhs instanceof Integer) {
            int lh = ((Integer) lhs).intValue();
            int rh = ((Integer) rhs).intValue();
            TokenType operator = expr.op;
            switch (operator) {
                case PLUS -> {
                    return lh + rh;
                }

                case MINUS -> {
                    return lh - rh;
                }
                case SLASH -> {
                    return rh / lh;
                }

                case STAR -> {
                    return lh * rh;
                }

                default -> {
                    throw new InvalidOperation("Invalid Operator: " + operator);
                }
            }
        } else if (rhs instanceof Integer && lhs instanceof String) {
            String lh = (String) lhs;
            int rh = ((Integer) rhs).intValue();
            TokenType operator = expr.op;
            switch (operator) {
                case PLUS -> {
                    return lh + rh;
                }


                case STAR -> {
                    return lh.repeat(rh);
                }

                default -> {
                    throw new InvalidOperation("Invalid Operator: " + operator);
                }
            }
        } else if (rhs instanceof String && lhs instanceof Integer) {
            int lh = ((Integer) lhs).intValue();
            String rh = (String) rhs;
            TokenType operator = expr.op;
            switch (operator) {
                case PLUS -> {
                    return lh + rh;
                }


                default -> {
                    throw new InvalidOperation("Invalid Operator: " + operator);
                }
            }
        } else {
            throw new InvalidOperation("Can't Operator " + lhs + " to " + rhs);
        }


    }


    public HashMap<String, Object> variables() {
        return variables;
    }
}