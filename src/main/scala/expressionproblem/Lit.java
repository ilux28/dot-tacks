package expressionproblem;

public class Lit implements Expr{
    public final int value;

    public Lit(int value) {
        this.value = value;
    }
    public static Lit lit(int value) {
        return new Lit(value);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
