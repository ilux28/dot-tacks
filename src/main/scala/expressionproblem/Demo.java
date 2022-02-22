package expressionproblem;

public class Demo {
    public static void main(String[] args) {
        // 0 + (1 + 3)
        Expr expr = new Add(new Lit(0), new Add(new Lit(1), new Lit(3)));
    }
}
