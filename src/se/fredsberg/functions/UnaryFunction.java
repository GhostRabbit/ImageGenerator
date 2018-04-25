package se.fredsberg.functions;

public abstract class UnaryFunction extends Function {
	@Override
	public String toString(FunctionTree lhs, FunctionTree rhs) {
		return getClass().getSimpleName() + "(" + lhs + ")";
	}
}
