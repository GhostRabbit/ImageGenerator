package se.fredsberg.functions;

import java.util.Random;

public abstract class Function {
	public abstract double evaluate(double x, double y);

	public static Function randomUnary(Random rand) {
		return unaryFunctions[rand.nextInt(unaryFunctions.length)];
	}

	public static Function randomBinary(Random rand) {
		return binaryFunctions[rand.nextInt(binaryFunctions.length)];
	}

	private static final Function[] unaryFunctions = new Function[] { new Sin(), new Cos(), new Exp(), new Log(),
			new Sinh(), new Cosh(), new Tanh(), new Abs(), new Neg(), new Twice(), new Trice() };

	private static final Function[] binaryFunctions = new Function[] { new Add(), new Sub(), new Mult(),
			new MultSin() };

	public String toString(FunctionTree lhs, FunctionTree rhs) {
		return getClass().getSimpleName() + "(" + lhs + ", " + rhs + ")";
	}

	public static class Fix extends Function {
		private double value;

		public Fix(double value) {
			this.value = value;
		}

		@Override
		public double evaluate(double x, double y) {
			return value;
		}

		@Override
		public String toString(FunctionTree lhs, FunctionTree rhs) {
			return "" + value;
		}
	}

	public static class Sin extends UnaryFunction {

		@Override
		public double evaluate(double x, double y) {
			return Math.sin(x);
		}
	}

	public static class Cos extends UnaryFunction {

		@Override
		public double evaluate(double x, double y) {
			return Math.cos(x);
		}
	}

	public static class Exp extends UnaryFunction {

		@Override
		public double evaluate(double x, double y) {
			return Math.exp(x);
		}
	}

	public static class Log extends UnaryFunction {

		@Override
		public double evaluate(double x, double y) {
			return Math.log(x);
		}
	}

	public static class Sinh extends UnaryFunction {

		@Override
		public double evaluate(double x, double y) {
			return Math.sinh(x);
		}
	}

	public static class Cosh extends UnaryFunction {

		@Override
		public double evaluate(double x, double y) {
			return Math.cosh(x);
		}
	}

	public static class Tanh extends UnaryFunction {

		@Override
		public double evaluate(double x, double y) {
			return Math.tanh(x);
		}
	}

	public static class Neg extends Function {

		@Override
		public double evaluate(double x, double y) {
			return -x;
		}

		@Override
		public String toString(FunctionTree lhs, FunctionTree rhs) {
			return "-" + lhs;
		}
	}

	public static class Twice extends Function {

		@Override
		public double evaluate(double x, double y) {
			return x * x;
		}

		@Override
		public String toString(FunctionTree lhs, FunctionTree rhs) {
			return lhs + " * " + lhs;
		}
	}

	public static class Trice extends Function {

		@Override
		public double evaluate(double x, double y) {
			return x * x * x;
		}

		@Override
		public String toString(FunctionTree lhs, FunctionTree rhs) {
			return lhs + " * " + lhs + " * " + lhs;
		}
	}

	public static class Abs extends Function {

		@Override
		public double evaluate(double x, double y) {
			return Math.abs(x);
		}

		@Override
		public String toString(FunctionTree lhs, FunctionTree rhs) {
			return "|" + lhs + "|";
		}
	}

	public static class Add extends Function {

		@Override
		public double evaluate(double x, double y) {
			return x + y;
		}

		@Override
		public String toString(FunctionTree lhs, FunctionTree rhs) {
			return lhs + " + " + rhs;
		}
	}

	public static class Sub extends Function {

		@Override
		public double evaluate(double x, double y) {
			return x - y;
		}

		@Override
		public String toString(FunctionTree lhs, FunctionTree rhs) {
			return lhs + " - " + rhs;
		}
	}

	public static class Mult extends Function {

		@Override
		public double evaluate(double x, double y) {
			return x * y;
		}

		@Override
		public String toString(FunctionTree lhs, FunctionTree rhs) {
			return lhs + " * " + rhs;
		}
	}

	// Strange, this is two other functions...
	public static class MultSin extends Function {

		@Override
		public double evaluate(double x, double y) {
			return Math.sin(x * y);
		}
		
		@Override
		public String toString(FunctionTree lhs, FunctionTree rhs) {
			return "Sin(" + lhs + " * " + rhs + ")";
		}
	}
}
