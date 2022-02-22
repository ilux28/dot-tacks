package expressionproblem;

public interface Expr {
    void accept(Visitor v);
}
