package expression;

public class Add extends BinaryOperation{

    public Add(AbstractExpression left, AbstractExpression right) {
        super(new Builder().symbol("+")
                .arguments(left, right)
                .needLeftBrackets(false).needRightBrackets(false)
                .priority(1));
    }

    @Override
    protected int calculate(int a, int b) {
        return a + b;
    }

    @Override
    protected double calculate(double dA, double dB) {
        return dA + dB;
    }
}
