import expression.*;
import expression.parser.ExpressionParser;


public class Main {
    public static void main(String[] args) {
        ExpressionParser ep = new ExpressionParser();
        System.out.println(ep.parse("x * (x - 2)*x + 1").toMiniString());
        System.out.println();

        TripleExpression ae = ep.parse("-(-(-\t\t-5 + 16   *x*y) + 1 * z) -(((-11)))");
        System.out.println("Expression " + ae.toString() + " result on variables (1, 1, 1) is { " + ae.evaluate(1, 1, 1) + " } ");
    }
}
