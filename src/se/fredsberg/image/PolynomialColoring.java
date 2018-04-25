package se.fredsberg.image;

import java.util.Random;

public class PolynomialColoring implements Painter {

	private double n1;
	private double n2;
	private double n3;

	public PolynomialColoring(Random rand) {
		this.n1 = rand.nextDouble();
		this.n2 = rand.nextDouble();
		this.n3 = rand.nextDouble();
	}
	
	public double paint(double input) {
		double sum = n3;
		sum *= input;
		sum += n2;
		sum *= input;
		sum += n1;
		return sum;
	}
	
	@Override
	public String toString() {
		return n3 + "^3 + " + n2 + "^2 + " + n1; 
	}
}
