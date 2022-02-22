package expressionproblem;

public interface Visitor<T> {
    void visit(Add add);
    void visit(Lit add);
    T result();
}
