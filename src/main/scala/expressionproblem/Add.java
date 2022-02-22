package expressionproblem;

public class Add implements Expr {
    public final Expr left;
    public final Expr right;

    public Add(Expr left, Expr rigth) {
        this.left = left;
        this.right = rigth;
    }
    public static Add add(Expr left, Expr right) {
        return new Add(left, right);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
