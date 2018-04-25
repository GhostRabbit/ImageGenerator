package se.fredsberg.image;

public class BWPainter implements Painter {

	@Override
	public double paint(double input) {
		return input;
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

}
