package expression;

public class Negate extends UnaryOperation {

    public Negate (AbstractExpression argument) {
        super(new Builder().symbol("-")
                .argument(argument)
                .priority(1));
    }


    @Override
    protected int calculate(int a) {
        return -a;
    }

    @Override
    protected double calculate(double dA) {
        return -dA;
    }
}

