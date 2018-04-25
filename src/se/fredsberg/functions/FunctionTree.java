package se.fredsberg.functions;

import java.util.Random;

public class FunctionTree {
	public final Function data;
	public final FunctionTree lhs;
	public final FunctionTree rhs;

	public FunctionTree(Function data, FunctionTree lhs, FunctionTree rhs) {
		this.data = data;
		this.lhs = lhs;
		this.rhs = rhs;
	}

	public FunctionTree(Function data) {
		this(data, new TerminalX(), new TerminalY());
	}

	public static class TerminalX extends FunctionTree {
		public TerminalX() {
			super(null, null, null);
		}

		@Override
		public double evaluate(double x, double y) {
			return x;
		}

		@Override
		public String toString() {
			return "x";
		}
	}

	public static class TerminalY extends FunctionTree {
		public TerminalY() {
			super(null, null, null);
		}

		@Override
		public double evaluate(double x, double y) {
			return y;
		}

		@Override
		public String toString() {
			return "y";
		}
	}

	public static class Factory {
		public static FunctionTree generate(Random rand, int depth) {
			int r = rand.nextInt(100);
			if (depth < 2) {
				if (r < 10) {
					return new FunctionTree(new Function.Fix(rand.nextDouble()));
				}
				if (r % 2 == 0) {
					return new TerminalX();
				}
				return new TerminalY();
			}
			else if (r < 2)
		      {
				if (r % 2 == 0) {
					return new TerminalX();
				}
				return new TerminalY();
		      }
		      else if(r > 50 && depth < 4 )
		      {
		    	  return new FunctionTree(Function.randomUnary(rand), generate(rand, depth-1), new TerminalY());
		      }
		      else
		      {
		    	  return new  FunctionTree(Function.randomBinary(rand), generate(rand, depth-1), generate(rand, depth-1));
		      }
			
//			return new FunctionTree(new Function.MultSin(), generate(rand, depth - 1), generate(rand, depth - 1));
		}
	}

	@Override
	public String toString() {
		return data.toString(lhs, rhs);
	}

	public double evaluate(double x, double y) {

		return data.evaluate(lhs.evaluate(x, y), rhs.evaluate(x, y));
	}
}