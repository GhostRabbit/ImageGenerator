package se.fredsberg.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageWriter {

	public Pixels createImageBuffer(int width, int height) {
		// TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed
		// into integer pixels
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		Graphics2D ig2 = bi.createGraphics();

		Font font = new Font("TimesRoman", Font.BOLD, 20);
		ig2.setFont(font);
		String message = "www.java2s.com!";
		FontMetrics fontMetrics = ig2.getFontMetrics();
		int stringWidth = fontMetrics.stringWidth(message);
		int stringHeight = fontMetrics.getAscent();
		ig2.setPaint(Color.black);
		ig2.drawString(message, (width - stringWidth) / 2, height / 2 + stringHeight / 4);

		int[] data = ((DataBufferInt) bi.getData().getDataBuffer()).getData();
		Pixels pixels = new Pixels(width, height);
		for (int i = 0; i < width * height; i++) {
			pixels.buffer()[i] = data[i];
		}

		return pixels;
	}

	public RenderedImage createImage(Pixels pixels) {
		BufferedImage image = new BufferedImage(pixels.width(), pixels.height(), BufferedImage.TYPE_INT_ARGB);
		WritableRaster raster = (WritableRaster) image.getData();
		raster.setDataElements(0, 0, pixels.width(), pixels.height(), pixels.buffer());
		image.setData(raster);
		return image;
	}

	public void save(Pixels pixels, String name) {
		try {
			ImageIO.write(createImage(pixels), "PNG", new File(name));
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		
	}
}
