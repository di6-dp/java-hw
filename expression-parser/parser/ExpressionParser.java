package expression.parser;

import expression.*;


import java.util.Map;

public class ExpressionParser extends BaseParser implements Parser {
    private final Map<Character, Integer> priorities = Map.of(
            '+', 1,
            '-', 1,
            '*', 2,
            '/', 2
    );

    private AbstractExpression parseArgument() {
        if (test('-')) {
            return parseNegate();
        }
        if (isLetter()) {
            return new Variable(parseVariable());
        }
        if (isDigit()) {
            return new Const(parseConst(false));
        }
        if (test('(')) {
            return  parseOperation();
        }
        return null;
    }

    private AbstractExpression parseNegate() {
        skipWhitespace();
        if (test('-')) {
            return new Negate(parseNegate());
        }
        if (isDigit()) {
            return new Const(parseConst(true));
        }
        if (isLetter()) {
            return new Negate(new Variable(parseVariable()));
        }
        if (test('(')) {
            return  new Negate(parseOperation());
        }
        return null;
    }


    private boolean isLetter() {
        return between('a', 'z') || between('A', 'Z');
    }

    private String parseVariable() {
        StringBuilder sb = new StringBuilder();
        while (isLetter()) {
            sb.append(ch);
            nextChar();
        }
        return sb.toString();
    }

    private boolean isDigit() {
        return between('0', '9');
    }

    private int parseConst(boolean isNegate) {
        StringBuilder sb = new StringBuilder();
        if (isNegate) {
            sb.append('-');
        }
        while (isDigit()) {
            sb.append(ch);
            nextChar();
        }
        return Integer.parseInt(sb.toString());
    }

    private AbstractExpression parseOperation() {
        skipWhitespace();
        AbstractExpression firstArgument = parseArgument();
        skipWhitespace();
        if (test(')') || eof()) {
            return firstArgument;
        }
        skipWhitespace();
        char operation = ch;
        nextChar();
        skipWhitespace();
        AbstractExpression secondArgument = parseArgument();
        skipWhitespace();
        if (test(')') || eof()) {
            return getOperation(operation, firstArgument, secondArgument);
        }

        return parseRest(firstArgument, secondArgument, operation);
    }

    private AbstractExpression getOperation(char operation, AbstractExpression left, AbstractExpression right) {
        return switch (operation) {
            case '+' -> new Add(left, right);
            case '-' -> new Subtract(left, right);
            case '*' -> new Multiply(left, right);
            case '/' -> new Divide(left, right);
            default -> null;
        };
    }

    private AbstractExpression parseRest(AbstractExpression firstArgument, AbstractExpression secondArgument, char firstOperation) {
        skipWhitespace();
        char secondOperation = ch;
        nextChar();
        skipWhitespace();
        AbstractExpression thirdArgument = parseArgument();
        skipWhitespace();
        AbstractExpression first;
        if (comparePriority(firstOperation, secondOperation)) {
            first = getOperation(firstOperation, firstArgument, secondArgument);
            if (test(')') || eof()) {
                return getOperation(secondOperation, first, thirdArgument);

            } else {
                return parseRest(first, thirdArgument, secondOperation);
            }
        } else {
            first = firstArgument;
            if (test(')') || eof()) {
                return getOperation(firstOperation, first, getOperation(secondOperation, secondArgument, thirdArgument));
            } else {
                return getOperation(firstOperation, first, parseRest( secondArgument, thirdArgument, secondOperation));
            }
        }
    }

    private boolean comparePriority(char first, char second) {
        return priorities.get(first) >= priorities.get(second);
    }

    @Override
    public TripleExpression parse(String expression) {
        setSource(new StringSource(expression));
        return parseOperation();
    }
}
