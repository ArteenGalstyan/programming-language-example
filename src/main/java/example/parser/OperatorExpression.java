package example.parser;

public class OperatorExpression implements Expression {
    public final Expression e1;
    public final Expression e2;
    public final Op op;
    
    public OperatorExpression( final Expression e1, final Expression e2, final Op op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public boolean equals(final Object other) {
        if(other instanceof OperatorExpression) {
            final OperatorExpression asOp = (OperatorExpression)other;
            return (e1.equals(asOp.e1) && op.equals(asOp.op) && e2.equals(asOp.e2));
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return e1.hashCode() + op.hashCode() + e2.hashCode();
    }

    @Override
    public String toString() {
        return ("(" + e1.toString() + " " + op.toString() + " " + e2.toString() + ")");
    }
} // OperatorExpression
