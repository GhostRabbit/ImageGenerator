import se.fredsberg.functions.FunctionTree;
import se.fredsberg.image.BWPainter;
import se.fredsberg.image.ImageWriter;
import se.fredsberg.image.Painter;
import se.fredsberg.image.Pixels;

public class Recolor {

	public static void main(String[] args) {
		long seed = Long.parseLong(args[0]);
		int width = Integer.parseInt(args[1]);
		int height = Integer.parseInt(args[2]);

		makeImage(seed, 6, width, height);
	}

	private static void makeImage(long seed, int depth, int width, int height) {
		Factory factory = new Factory(seed, depth);
		Painter painters[] = factory.painters();
		FunctionTree functions = factory.functions();

		System.out.println(functions);

		Painter bw[] = new Painter[] { new BWPainter(), new BWPainter(),
				new BWPainter() };

		Pixels pixels = new Pixels(width, height);
		pixels.apply(functions);

		// Feed pixels into functions for a number of iterations.
		paintAndSave(seed, depth, painters, pixels, 0);
		paintAndSave(seed, depth, bw, pixels, 1);

		for (int i = 0; i < 100; i++) {
			paintAndSave(seed, depth, factory.newPainter(), pixels, i + 2);
		}
	}

	private static String paintAndSave(long seed, int depth,
			Painter[] painters, Pixels pixels, int index) {
		System.out.println("R: " + painters[0]);
		System.out.println("G: " + painters[1]);
		System.out.println("B: " + painters[2]);
		pixels.apply(painters);

		// Name of file : seed_widthxheight.png
		String name = seed + "_d" + depth + "_w" + pixels.width() + "_h"
				+ pixels.height() + "_i" + index + ".png";

		if (pixels.isAllBlack()) {
			System.out.println("Not saving " + name + " due to all black.");
		} else {
			new ImageWriter().save(pixels, name);
		}
		return name;
	}
}
