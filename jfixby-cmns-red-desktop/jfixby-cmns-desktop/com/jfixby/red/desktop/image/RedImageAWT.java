package com.jfixby.red.desktop.image;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

import javax.imageio.ImageIO;

import com.jfixby.cmns.api.color.Color;
import com.jfixby.cmns.api.color.Colors;
import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.desktop.ImageAWTComponent;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.cmns.api.image.ArrayColorMap;
import com.jfixby.cmns.api.image.ArrayColorMapSpecs;
import com.jfixby.cmns.api.image.ColorMap;
import com.jfixby.cmns.api.image.EditableColorMap;
import com.jfixby.cmns.api.image.ImageProcessing;
import com.jfixby.cmns.api.log.L;

public class RedImageAWT implements ImageAWTComponent {

    @Override
    public BufferedImage readFromFile(File image_file) throws IOException {
	Debug.checkNull("image_file", image_file);
	FileInputStream is = image_file.newInputStream();
	InputStream java_is = is.toJavaInputStream();
	BufferedImage bad_image = ImageIO.read(java_is);
	if (bad_image == null) {
	    L.d("Failed to read image", image_file);
	    L.d("    exists", image_file.exists());
	    L.d("      hash", image_file.calculateHash());
	    L.d("      size", image_file.getSize());
	    File parent = image_file.getFileSystem().newFile(image_file.getAbsoluteFilePath().parent());
	    parent.listChildren().print();
	    throw new IOException("Failed to read image: " + image_file);
	}
	is.close();
	return bad_image;
    }

    @Override
    public void writeToFile(java.awt.Image java_image, File file, String file_type) throws IOException {
	Debug.checkNull("java_image", java_image);
	Debug.checkNull("file", file);
	Debug.checkNull("file_type", file_type);
	int width = java_image.getWidth(null);
	int height = java_image.getHeight(null);
	BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	Graphics2D g2 = out.createGraphics();
	g2.drawImage(java_image, 0, 0, null);
	FileOutputStream os = file.newOutputStream();
	OutputStream java_os = os.toJavaOutputStream();
	ImageIO.write(out, file_type, java_os);
	os.flush();
	os.close();
    }

    @Override
    public ArrayColorMap newAWTColorMap(BufferedImage img) {
	Debug.checkNull(img);

	ArrayColorMapSpecs specs = ImageProcessing.newArrayColorMapSpecs();
	specs.setWidth(img.getWidth());
	specs.setHeight(img.getHeight());
	specs.setDefaultColor(Colors.BLACK());

	ArrayColorMap array = ImageProcessing.newArrayColorMap(specs);

	for (int j = 0; j < array.getHeight(); j++) {
	    for (int i = 0; i < array.getWidth(); i++) {
		int rgb = img.getRGB(i, j);
		array.setValue(i, j, Colors.newColor(rgb));
	    }
	}

	return array;
    }

    @Override
    public ArrayColorMap newAWTColorMap(InputStream java_is) throws IOException {
	BufferedImage bad_image = ImageIO.read(java_is);
	if (bad_image == null) {
	    L.d("Failed to read image", java_is);

	    throw new IOException("Failed to read image: " + java_is);
	}
	return this.newAWTColorMap(bad_image);
    }

    @Override
    public BufferedImage toAWTImage(ColorMap image_function) {
	int h = image_function.getHeight();
	int w = image_function.getWidth();
	BufferedImage im = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	int[] data = ((DataBufferInt) im.getRaster().getDataBuffer()).getData();
	for (int j = 0; j < h; j++) {
	    for (int i = 0; i < w; i++) {
		int K = i + j * w;
		Color color_c = image_function.valueAt(i, j);
		data[K] = color_c.toInteger();
	    }
	}
	return im;
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

    public static String toString(EditableColorMap argb) {
	String result = "[" + argb.getWidth() + ";" + argb.getHeight() + "]" + next_line_L;

	// Log.d("delta", delta);

	for (int j = -1; j < argb.getHeight() + 1; j++) {
	    String line = "";
	    for (int i = -1; i < argb.getWidth() + 1; i++) {

		Color color = argb.valueAt(i, j);

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
    public ArrayColorMap readAWTColorMap(File image_file) throws IOException {
	FileInputStream is = image_file.newInputStream();
	InputStream java_is = is.toJavaInputStream();
	ArrayColorMap map = this.newAWTColorMap(java_is);
	java_is.close();
	is.close();
	return map;
    }

}
