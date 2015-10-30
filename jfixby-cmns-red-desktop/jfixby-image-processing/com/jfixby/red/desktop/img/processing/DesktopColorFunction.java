package com.jfixby.red.desktop.img.processing;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Vector;

import com.jfixby.cmns.api.color.Color;
import com.jfixby.cmns.api.color.ColorConstant;
import com.jfixby.cmns.api.color.Colors;
import com.jfixby.cmns.api.color.CustomColor;
import com.jfixby.cmns.api.image.ColorFunction;
import com.jfixby.cmns.api.image.ColorFunctionSpecs;
import com.jfixby.red.color.RedColor;

public class DesktopColorFunction implements ColorFunction {

	@Override
	public String toString() {
		return "ColorFunction[" + w + "x" + h + "] ";
	}

	// final private BufferedImage bi;
	private int h;
	private int w;

	CustomColor[][] surface;

	public DesktopColorFunction(ColorFunctionSpecs color_function_specs) {
		BufferedImage image = color_function_specs.getJavaImage();
		this.default_color = color_function_specs.getDefaultColor();
		if (this.default_color == null) {
			this.default_color = Colors.WHITE();
		}

		if (image == null) {
			h = color_function_specs.getHeight();
			w = color_function_specs.getWidth();
			surface = new CustomColor[w][h];
			for (int i = 0; i < w; i++) {
				for (int j = 0; j < h; j++) {
					surface[i][j] = Colors.newColor().setValue(default_color);
				}
			}
		} else {
			h = image.getHeight();
			w = image.getWidth();
			surface = new CustomColor[w][h];
			for (int i = 0; i < w; i++) {
				for (int j = 0; j < h; j++) {
					int argb = image.getRGB(i, j);
					surface[i][j] = Colors.newColor().setValue(default_color);
					surface[i][j].setARGB(argb);
				}
			}
		}

	}

	@Override
	public int getWidth() {

		return w;
	}

	@Override
	public int getHeight() {

		return h;
	}

	final RedColor color = new RedColor();
	private Color default_color;

	@Override
	public Color getValue(int x, int y) {
		if (out_of_the_scope(x, y)) {
			return default_color;
		}
		return this.surface[x][y];

	}

	@Override
	public void setValue(int x, int y, Color color_value) {
		if (out_of_the_scope(x, y)) {
			return;
		}
		this.surface[x][y].setValue(color_value);
	}

	private boolean out_of_the_scope(int x, int y) {
		if (x < 0) {
			return true;
		}
		if (y < 0) {
			return true;
		}
		if (x >= w) {
			return true;
		}
		if (y >= h) {
			return true;
		}
		return false;
	}

	static final Vector<String> palette = new Vector<String>();
	static String next_line_L = "\n";
	static float delta;
	static {
		boolean use_grayscale_symbols = true;
		if (use_grayscale_symbols) {
			// String color0 = "█";
			// String color1 = "▓";
			// String color2 = "▒";
			// String color3 = "░";
			// String color4 = " ";
			palette.add("█");
			palette.add("▓");
			palette.add("▒");
			palette.add("░");
			palette.add(" ");
		} else {
			// int N = ASCI_palette.length();
			// for (int i = 0; i < N; i++) {
			// palette.add(ASCI_palette.charAt(i) + "");
			// }
			// palette.add("█");
			// palette.add("▓");
			// palette.add("▓");
			// palette.add("▒");
			// palette.add("▒");
			// palette.add("░");
			// palette.add("░");
			// palette.add(" ");
			// palette.add(" ");

		}

		delta = 1f / palette.size();

	}

	private static String palette(float gray) {
		int index = (int) ((gray) * (1f / delta));
		if (index == palette.size()) {
			index--;
		}
		if (index < 0) {
			index = 0;
		}
		String val = palette.get(index);
		return val;
	}

	public static String toString(ColorFunction argb) {
		String result = "[" + argb.getWidth() + ";" + argb.getHeight() + "]"
				+ next_line_L;

		// Log.d("delta", delta);

		for (int j = -1; j < argb.getHeight() + 1; j++) {
			String line = "";
			for (int i = -1; i < argb.getWidth() + 1; i++) {

				Color color = argb.getValue(i, j);

				// Log.d("gray", gray);

				String val = palette(color.getGrayscaleValue());

				// line = line + "[" + val + "]";
				line = line + val + val;
			}
			result = result + line + next_line_L;
		}
		return result;
	}

	@Override
	public void setDefaultColor(ColorConstant blue) {
		this.default_color = blue;
		if (this.default_color == null) {
			this.default_color = Colors.WHITE();
		}
	}

	@Override
	public BufferedImage toJavaImage() {
		BufferedImage im = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
//		Graphics g = im.getGraphics();
//		java.awt.Color c = new java.awt.Color(0, 1, 0, 0.5f);
//		g.setColor(c);
//		g.fillRect(0, 0, w, h);
//		g.dispose();
		int[] data = ((DataBufferInt) im.getRaster().getDataBuffer()).getData();

		for (int j = 0; j < h; j++) {
			for (int i = 0; i < w; i++) {

				int K = i + j * w;
				CustomColor color_c = surface[i][j];
				data[K] = color_c.toInteger();
//				data[K] = 0x00ff0000;
			}
		}
		return im;
	}
}
