
package com.jfixby.scarabei.red.desktop.image;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.jfixby.scarabei.api.color.Color;
import com.jfixby.scarabei.api.color.Colors;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.desktop.GifProducer;
import com.jfixby.scarabei.api.desktop.GifProducerSpecs;
import com.jfixby.scarabei.api.desktop.ImageAWT;
import com.jfixby.scarabei.api.desktop.ImageAWTComponent;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FileInputStream;
import com.jfixby.scarabei.api.file.FileOutputStream;
import com.jfixby.scarabei.api.image.ArrayColorMap;
import com.jfixby.scarabei.api.image.ArrayColorMapSpecs;
import com.jfixby.scarabei.api.image.ArrayGrayMap;
import com.jfixby.scarabei.api.image.ArrayGrayMapSpecs;
import com.jfixby.scarabei.api.image.ColorMap;
import com.jfixby.scarabei.api.image.EditableColorMap;
import com.jfixby.scarabei.api.image.GrayMap;
import com.jfixby.scarabei.api.image.ImageProcessing;
import com.jfixby.scarabei.api.io.InputStream;
import com.jfixby.scarabei.api.io.OutputStream;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.math.FloatMath;

public class RedImageAWT implements ImageAWTComponent {

	@Override
	public BufferedImage readFromFile (final File image_file) throws IOException {
		Debug.checkNull("image_file", image_file);
		final FileInputStream is = image_file.newInputStream();
		is.open();
		final BufferedImage bad_image = this.readFromStream(is);
		if (bad_image == null) {
			L.d("Failed to read image", image_file);
			L.d("    exists", image_file.exists());
			L.d("      hash", image_file.calculateHash());
			L.d("      size", image_file.getSize());
			final File parent = image_file.getFileSystem().newFile(image_file.getAbsoluteFilePath().parent());
			parent.listDirectChildren().print("files");
			throw new IOException("Failed to read image: " + image_file);
		}
		is.close();
		return bad_image;
	}

	@Override
	public BufferedImage readFromStream (final InputStream is) throws IOException {
		final java.io.InputStream java_is = is.toJavaInputStream();
		final BufferedImage bad_image = ImageIO.read(java_is);
		return bad_image;
	}

	@Override
	public void writeToFile (final BufferedImage java_image, final File file, final String file_type, final int image_mode)
		throws IOException {
		Debug.checkNull("java_image", java_image);
		Debug.checkNull("file", file);
		Debug.checkNull("file_type", file_type);

		final FileOutputStream os = file.newOutputStream();
		os.open();
		this.writeToStream(java_image, os, file_type, image_mode);
		os.close();
	}

	@Override
	public void writeToFile (final BufferedImage java_image, final File file, final String file_type) throws IOException {
// if (java_image instanceof RenderedImage) {
// ImageIO.write((RenderedImage)java_image, "png", file.toJavaFile());
// } else {
// writeToFile(java_image, file, file_type, BufferedImage.TYPE_INT_ARGB);
// }

		this.writeToFile(java_image, file, file_type, BufferedImage.TYPE_INT_ARGB);
	}

	@Override
	public ArrayColorMap readAWTColorMap (final java.io.InputStream java_is) throws IOException {
		final BufferedImage bad_image = ImageIO.read(java_is);
		if (bad_image == null) {
			L.d("Failed to read image", java_is);

			throw new IOException("Failed to read image: " + java_is);
		}
		return this.newAWTColorMap(bad_image);
	}

	@Override
	public BufferedImage toAWTImage (final ColorMap image_function) {
		final int h = image_function.getHeight();
		final int w = image_function.getWidth();
		final BufferedImage im = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		final int[] data = ((DataBufferInt)im.getRaster().getDataBuffer()).getData();
		for (int j = 0; j < h; j++) {
			for (int i = 0; i < w; i++) {
				final int K = i + j * w;
				final Color color_c = image_function.valueAt(i, j);
				data[K] = color_c.toInteger();
			}
		}
		return im;
	}

	static final ArrayList<String> palette = new ArrayList<String>();
	static String next_line_L = "\n";
	static float delta;
	static {
		final boolean use_grayscale_symbols = true;
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

	private static String palette (final float gray) {
		int index = (int)((gray) * (1f / delta));
		if (index == palette.size()) {
			index--;
		}
		if (index < 0) {
			index = 0;
		}
		final String val = palette.get(index);
		return val;
	}

	public static String toString (final EditableColorMap argb) {
		String result = "[" + argb.getWidth() + ";" + argb.getHeight() + "]" + next_line_L;

		// Log.d("delta", delta);

		for (int j = -1; j < argb.getHeight() + 1; j++) {
			String line = "";
			for (int i = -1; i < argb.getWidth() + 1; i++) {

				final Color color = argb.valueAt(i, j);

				// Log.d("gray", gray);

				final String val = palette(color.gray());

				// line = line + "[" + val + "]";
				line = line + val + val;
			}
			result = result + line + next_line_L;
		}
		return result;
	}

	@Override
	public ArrayColorMap readAWTColorMap (final File image_file) throws IOException {
		final FileInputStream is = image_file.newInputStream();
		is.open();
		final java.io.InputStream java_is = is.toJavaInputStream();
		final ArrayColorMap map = this.readAWTColorMap(java_is);
		java_is.close();
		is.close();
		return map;
	}

	@Override
	public ArrayColorMap newAWTColorMap (final BufferedImage img) {
		Debug.checkNull(img);

		final ArrayColorMapSpecs specs = ImageProcessing.newArrayColorMapSpecs();
		specs.setWidth(img.getWidth());
		specs.setHeight(img.getHeight());
		specs.setDefaultColor(Colors.BLACK());

		final ArrayColorMap array = ImageProcessing.newArrayColorMap(specs);

		for (int j = 0; j < array.getHeight(); j++) {
			for (int i = 0; i < array.getWidth(); i++) {
				final int rgb = img.getRGB(i, j);
				array.setValue(i, j, Colors.newColor(rgb));
			}
		}

		return array;
	}

	@Override
	public void writeToFile (final ColorMap image, final File image_file, final String file_type) throws IOException {
		this.writeToFile(this.toAWTImage(image), image_file, file_type);
	}

	@Override
	public BufferedImage toAWTImage (final GrayMap image_function) {
		final int h = image_function.getHeight();
		final int w = image_function.getWidth();
		final BufferedImage im = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
		final byte[] data = ((DataBufferByte)im.getRaster().getDataBuffer()).getData();
		for (int j = 0; j < h; j++) {
			for (int i = 0; i < w; i++) {
				final int K = i + j * w;
				final float color_c = image_function.valueAt(i, j);
				data[K] = (byte)(255 * color_c);
				// im.setRGB(i, j, rgb);
			}
		}
		return im;
	}

	@Override
	public void writeToStream (final BufferedImage java_image, final OutputStream outputStream, final String file_type,
		final int awtImageMode) throws IOException {

		final java.io.OutputStream java_os = outputStream.toJavaOutputStream();
		ImageIO.write(java_image, file_type, java_os);
		outputStream.flush();

	}

	@Override
	public void writeToFile (final GrayMap image, final File image_file, final String file_type) throws IOException {
		final BufferedImage awt = this.toAWTImage(image);
		this.writeToFile(awt, image_file, file_type, BufferedImage.TYPE_BYTE_GRAY);
	}

	@Override
	public ArrayGrayMap readAWTGrayMap (final File image_file) throws IOException {
		final BufferedImage image = this.readFromFile(image_file);
		if (image.getType() != BufferedImage.TYPE_BYTE_GRAY) {
			Err.reportError("Not implemented yet!");
		}

		return this.newAWTGrayMap(image);
	}

	@Override
	public ArrayGrayMap newAWTGrayMap (final BufferedImage img) {
		Debug.checkNull(img);

		final ArrayGrayMapSpecs specs = ImageProcessing.newArrayGrayMapSpecs();
		specs.setWidth(img.getWidth());
		specs.setHeight(img.getHeight());

		final ArrayGrayMap array = ImageProcessing.newArrayGrayMap(specs);
		final byte[] data = ((DataBufferByte)img.getRaster().getDataBuffer()).getData();
		final int w = array.getWidth();
		for (int j = 0; j < array.getHeight(); j++) {
			for (int i = 0; i < w; i++) {
				final int K = i + j * w;
				final int gray = data[K];
				// int rgb = img.getRGB(i, j);
				final float value = gray / 255f;
				array.setValue(i, j, value);
			}
		}

		return array;
	}

	@Override
	public GrayMap awtScaleTo (final GrayMap image, final int width, final int height) {
		final BufferedImage reduced_awt = ImageAWT.toAWTImage(image);
		final BufferedImage expanded_awt = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		final Graphics2D g2 = expanded_awt.createGraphics();
		g2.drawImage(reduced_awt, 0, 0, width, height, null);
		g2.dispose();
		final GrayMap expanded = ImageAWT.newAWTGrayMap(expanded_awt);
		return expanded;
	}

	@Override
	public BufferedImage awtScaleTo (final BufferedImage javaImage, final int width, final int height) {
		final int type = javaImage.getType();
		final BufferedImage output = new BufferedImage(width, height, type);
		final Graphics2D g2 = output.createGraphics();
		g2.drawImage(javaImage, 0, 0, width, height, null);
		g2.dispose();
		return output;
	}

	@Override
	public BufferedImage linearMix (final BufferedImage a, final float aWeight, final BufferedImage b, final float bWeight) {
		final int type = a.getType();
		final int width = a.getWidth();
		final int height = a.getHeight();
		final BufferedImage output = new BufferedImage(width, height, type);
		if (type == BufferedImage.TYPE_BYTE_GRAY) {
			final byte[] data = ((DataBufferByte)output.getRaster().getDataBuffer()).getData();
			final byte[] A = ((DataBufferByte)a.getRaster().getDataBuffer()).getData();
			final byte[] B = ((DataBufferByte)b.getRaster().getDataBuffer()).getData();
			for (int j = 0; j < height; j++) {
				for (int i = 0; i < width; i++) {
					final int K = i + j * width;
					final float value_A = A[K] / 255f;
					final float value_B = B[K] / 255f;
					final float mix = value_A * aWeight + bWeight * value_B;
					data[K] = (byte)(255 * mix);
				}
			}
		} else {
			Err.reportError("Not implemented yet!");
		}
		return output;
	}

	@Override
	public BufferedImage toBufferedImage (final Image image) {
		if (image instanceof BufferedImage) {
			return (BufferedImage)image;
		}
		final int width = image.getWidth(null);
		final int height = image.getHeight(null);
		final BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		final Graphics2D g2 = out.createGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
		return out;

	}

	@Override
	public Image awtScale (final Image java_image, final float scaleFactor) {
		int width = (int)FloatMath.round(java_image.getWidth(null) * scaleFactor);
		if (width == 0) {
			width = 1;
		}
		int height = (int)FloatMath.round(java_image.getHeight(null) * scaleFactor);
		if (height == 0) {
			height = 1;
		}
		final Image tmp = java_image.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
		return tmp;
	}

	@Override
	public GifProducerSpecs newGifProducerSpecs () {
		return new RedGifProducerSpecs();
	}

	@Override
	public GifProducer newGifProducer (final GifProducerSpecs producerSpecs) {
		return new RedGifProducer(producerSpecs);
	}

}
