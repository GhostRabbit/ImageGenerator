package se.fredsberg.image;

import java.awt.Color;

import se.fredsberg.functions.FunctionTree;

public class Pixels {

	private final int width;
	private final int height;
	private final int[] buffer;
	private double[] values;
	private double[][] channels;

	public Pixels(int width, int height) {
		this.width = width;
		this.height = height;
		this.buffer = new int[width * height];
		this.values = new double[width * height];
		this.channels = new double[3][width * height];
	}

	public int width() {
		return width;
	}

	public int height() {
		return height;
	}

	public int[] buffer() {
		return buffer;
	}

	public double[] values() {
		return values;
	}

	public double[][] colorChannels() {
		return channels;
	}

	public void generateColors() {
		normalize(channels[0]);
		normalize(channels[1]);
		normalize(channels[2]);
		colorsFromChannels();
	}

	// Should return a new buffer really
	private void colorsFromChannels() {
		for (int i = 0; i < buffer.length; i++) {
			try {
				buffer[i] = new Color((float) channels[0][i],
						(float) channels[1][i], (float) channels[2][i])
						.getRGB();
			} catch (IllegalArgumentException e) {
				System.out
						.println(i + " " + (float) channels[0][i] + " "
								+ (float) channels[1][i] + " "
								+ (float) channels[2][i]);
				throw e;
			}
		}
	}

	private void normalize(double[] channel) {
		double max = Double.MIN_VALUE;
		double min = Double.MAX_VALUE;
		for (int i = 0; i < channel.length; i++) {
			max = Math.max(max, channel[i]);
			min = Math.min(min, channel[i]);
		}
		double range = max - min;
		for (int i = 0; i < channel.length; i++) {
			channel[i] = (channel[i] - min) / range;
		}
	}

	public boolean isAllBlack() {
		int black = Color.black.getRGB();
		for (int p : buffer) {
			if (p != black) {
				return false;
			}
		}
		return true;
	}

	public void apply(FunctionTree functions) {
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				double value = functions.evaluate(w / (double) width, h / (double) height);
				values[w + h * width] = value;
			}
		}
	}

	public void apply(Painter[] painters) {
		for (int i = 0; i< values.length;  i++) {
			channels[0][i] = painters[0].paint(values[i]);
			channels[1][i] = painters[1].paint(values[i]);
			channels[2][i] = painters[2].paint(values[i]);
		}
		generateColors();
	}

	// private double variance(double[] values, double mean) {
	// double variance = 0.0;
	// for (int i = 0; i < values.length; i++) {
	// variance += (mean - values[i]) * (mean - values[i]);
	// }
	// variance /= values.length;
	// return variance;
	// }

}
