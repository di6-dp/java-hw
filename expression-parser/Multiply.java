package expression;

public class Multiply extends BinaryOperation{
    public Multiply(AbstractExpression left, AbstractExpression right) {
        super(new Builder().symbol("*")
                .arguments(left, right)
                .needLeftBrackets(false).needRightBrackets(false)
                .priority(2));
    }

    @Override
    protected int calculate(int a, int b) {
        return a * b;
    }

    @Override
    protected double calculate(double dA, double dB) {
        return dA * dB;
    }
}
