package expression;

import java.util.Objects;

public abstract class UnaryOperation extends AbstractExpression {
    protected final int priority;
    protected final String symbol;
    protected final AbstractExpression argument;

    protected static class Builder {
        protected int priority;
        protected String symbol;
        protected AbstractExpression argument;

        protected Builder priority(final int priority) {
            this.priority = priority;
            return this;
        }

        protected Builder symbol(final String symbol) {
            this.symbol = symbol;
            return this;
        }

        protected Builder argument(final AbstractExpression argument) {
            this.argument = argument;
            return this;
        }
    }

    protected UnaryOperation(final Builder builder) {
        priority = builder.priority;
        symbol = builder.symbol;
        argument = builder.argument;
    }

    protected abstract int calculate(int a);

    protected abstract double calculate(double dA);

    @Override
    public int evaluate(int x) {
        return calculate(argument.evaluate(x));
    }

    @Override
    public double evaluate(double x) {
        return calculate(argument.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return calculate(argument.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return symbol + argument.toString();
    }

    @Override
    public String toMiniString() {
        return symbol + argument.toMiniString();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof UnaryOperation) {
            UnaryOperation unaryOperation = (UnaryOperation) o;
            return priority == unaryOperation.priority
                    && symbol.equals(unaryOperation.symbol)
                    && argument.equals(unaryOperation.argument);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 7 * (Integer.hashCode(priority)
            + 9 * Objects.hashCode(symbol)
            + 39 * argument.hashCode());
    }
}
