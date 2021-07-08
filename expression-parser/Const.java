package expression;

public class Const extends AbstractExpression {
    private final double value;
    private final boolean isInteger;

    public Const(final int value) {
        this.value = value;
        isInteger = true;
    }

    public Const(final double value) {
        this.value = value;
        isInteger = false;
    }

    @Override
    public int evaluate(int x) {
        return (int) value;
    }

    @Override
    public double evaluate(double x) {
        return value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return (int) value;
    }

    @Override
    public String toString() {
        return isInteger ? Integer.toString((int) value) : Double.toString(value);
    }

    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Const && ((Const) o).value == value;
    }

    @Override
    public int hashCode() {
        return isInteger ? Integer.hashCode((int) value) : Double.hashCode(value);
    }
}
