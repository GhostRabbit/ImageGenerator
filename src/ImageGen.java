import se.fredsberg.functions.FunctionTree;
import se.fredsberg.image.BWPainter;
import se.fredsberg.image.ImageWriter;
import se.fredsberg.image.Painter;
import se.fredsberg.image.Pixels;

public class ImageGen {

	// Nice example: java ImageGen 1443774109132 1200 800

	public static void main(String[] args) {
		if (args.length > 0) {
			makeImage(Long.parseLong(args[0]), 6, Integer.parseInt(args[1]),
					Integer.parseInt(args[2]));
			return;
		}

		long start = System.currentTimeMillis();
		for (int i = 0; i < 50; i++) {
			long innerStart = System.currentTimeMillis();
			String name = makeImage(System.currentTimeMillis(), 6, 256, 256);
			System.out.println("Done generating " + name + " in "
					+ (System.currentTimeMillis() - innerStart) + " ms");
		}
		System.out.println("Done generating in "
				+ (System.currentTimeMillis() - start) + " ms");
	}

	private static String makeImage(long seed, int depth, int width, int height) {
		Factory factory = new Factory(seed, depth);
		Painter painters[] = factory.painters();
		FunctionTree functions = factory.functions();

		System.out.println(functions);

		Painter bw[] = new Painter[] { new BWPainter(), new BWPainter(),
				new BWPainter() };

		Pixels pixels = new Pixels(width, height);

		// Feed pixels into functions for a number of iterations.
		pixels.apply(functions);
		paintAndSave(seed, depth, painters, pixels, 1);
		return paintAndSave(seed, depth, bw, pixels, 2);
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
