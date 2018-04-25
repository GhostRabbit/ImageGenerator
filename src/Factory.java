import java.util.Random;

import se.fredsberg.functions.FunctionTree;
import se.fredsberg.image.Painter;
import se.fredsberg.image.PolynomialColoring;

public class Factory {

	private final Random rand;
	private Painter painters[];
	private FunctionTree functions;

	public Factory(long seed, int depth) {
		rand = new Random(seed);

		// Making components in specific order
		painters = newPainter();

		functions = FunctionTree.Factory.generate(rand, depth);
	}

	public Painter[] painters() {
		return painters;
	}

	public FunctionTree functions() {
		return functions;
	}

	public Painter[] newPainter() {
		return new Painter[] { new PolynomialColoring(rand),
				new PolynomialColoring(rand), new PolynomialColoring(rand) };
	}

}
