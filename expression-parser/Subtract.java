package expression;

public class Subtract extends BinaryOperation{
    public Subtract(AbstractExpression left, AbstractExpression right) {
        super(new Builder().symbol("-")
                .arguments(left, right)
                .needLeftBrackets(false).needRightBrackets(true)
                .priority(1));
    }

    @Override
    protected int calculate(int a, int b) {
        return a - b;
    }

    @Override
    protected double calculate(double dA, double dB) {
        return dA - dB;
    }
}
