package expression;

public class Divide extends BinaryOperation{
    public Divide(AbstractExpression left, AbstractExpression right) {
        super(new Builder().symbol("/")
                .arguments(left, right)
                .needLeftBrackets(false).needRightBrackets(true)
                .priority(2));
    }

    @Override
    protected int calculate(int a, int b) {
        return a / b;
    }

    @Override
    protected double calculate(double dA, double dB) {
        return dA / dB;
    }
}
