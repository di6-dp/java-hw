package expression;

import java.util.Objects;

public abstract class BinaryOperation extends AbstractExpression {
    protected final int priority;
    protected final String symbol;
    protected final AbstractExpression leftArgument;
    protected final AbstractExpression rightArgument;
    protected final boolean needLeftBrackets;
    protected final boolean needRightBrackets;

    protected static class Builder {
        protected String symbol;
        protected int priority;
        protected AbstractExpression leftArgument;
        protected AbstractExpression rightArgument;
        protected boolean needLeftBrackets;
        protected boolean needRightBrackets;

        protected Builder symbol(final String symbol) {
            this.symbol = symbol;
            return this;
        }

        protected Builder arguments(final AbstractExpression left, final AbstractExpression right) {
            leftArgument = left;
            rightArgument = right;
            return this;
        }

        protected Builder needLeftBrackets(final boolean need) {
            needLeftBrackets = need;
            return this;
        }

        protected Builder needRightBrackets(final boolean need) {
            needRightBrackets = need;
            return this;
        }

        protected Builder priority(final int priority) {
            this.priority = priority;
            return this;
        }
    }

    protected BinaryOperation(final Builder builder) {
        priority = builder.priority;
        symbol = builder.symbol;
        leftArgument = builder.leftArgument;
        rightArgument = builder.rightArgument;
        needLeftBrackets = builder.needLeftBrackets;
        needRightBrackets = builder.needRightBrackets;
    }

    protected abstract int calculate(int a, int b);

    protected abstract double calculate(double dA, double dB);

    @Override
    public int evaluate(int x) {
        return calculate(leftArgument.evaluate(x), rightArgument.evaluate(x));
    }

    @Override
    public double evaluate(double x) {
        return calculate(leftArgument.evaluate(x), rightArgument.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return calculate(leftArgument.evaluate(x, y, z), rightArgument.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return "(" + leftArgument.toString()
                + " " + symbol + " "
                + rightArgument.toString() + ")";

    }

    private String addOperationBrackets(BinaryOperation operation, boolean needBrackets) {
        return needBrackets ? "(" + operation.toMiniString() + ")" : operation.toMiniString();
    }

    private String addArgumentBrackets(AbstractExpression expression, boolean needBrackets) {
        if (expression instanceof BinaryOperation) {
            BinaryOperation operation = (BinaryOperation) expression;
            if (priority == ((BinaryOperation) expression).priority) {
                return addOperationBrackets(operation, needBrackets);
            } else if (priority > ((BinaryOperation) expression).priority) {
                return addOperationBrackets(operation, true);
            } else {
                return expression.toMiniString();
            }
        }
        return expression.toMiniString();
    }

    @Override
    public String toMiniString() {
        return addArgumentBrackets(leftArgument, needLeftBrackets) +
                " " + symbol + " "
                + addArgumentBrackets(rightArgument, needRightBrackets);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof BinaryOperation) {
            BinaryOperation binaryOperation = (BinaryOperation) o;
            return priority == binaryOperation.priority
                    && symbol.equals(binaryOperation.symbol)
                    && leftArgument.equals(binaryOperation.leftArgument) && rightArgument.equals(binaryOperation.rightArgument)
                    && needLeftBrackets == binaryOperation.needLeftBrackets
                    && needRightBrackets == binaryOperation.needRightBrackets;
        }
        return false;

    }

    @Override
    public int hashCode() {
        return 7 * (Integer.hashCode(priority)
                + 9 * Objects.hashCode(symbol)
                + 31 * leftArgument.hashCode() + 37 * rightArgument.hashCode()
                + 11 * Boolean.hashCode(needLeftBrackets)
                + 13 * Boolean.hashCode(needRightBrackets));
    }
}
