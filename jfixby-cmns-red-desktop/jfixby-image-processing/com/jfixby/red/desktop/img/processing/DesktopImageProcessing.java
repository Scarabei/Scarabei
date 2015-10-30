package com.jfixby.red.desktop.img.processing;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.filesystem.File;
import com.jfixby.cmns.api.filesystem.FileInputStream;
import com.jfixby.cmns.api.filesystem.FileOutputStream;
import com.jfixby.cmns.api.image.ColorFunction;
import com.jfixby.cmns.api.image.ColorFunctionSpecs;
import com.jfixby.cmns.api.image.ImageProcessingComponent;
import com.jfixby.cmns.api.io.Buffer;
import com.jfixby.cmns.api.log.L;

public class DesktopImageProcessing implements ImageProcessingComponent {

	@Override
	public ColorFunction newColorFunction(Buffer buffer) {

		BufferedImage img = null;
		try {
			img = ImageIO.read(new ByteArrayInputStream(buffer.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}

		ColorFunctionSpecs specs = new DesktopColorFunctionSpecs();
		specs.setJavaImage(img);

		return new DesktopColorFunction(specs);
	}

	@Override
	public ColorFunctionSpecs newColorFunctionSpecs() {
		return new DesktopColorFunctionSpecs();
	}

	@Override
	public ColorFunction newColorFunction(
			ColorFunctionSpecs color_function_specs) {
		return new DesktopColorFunction(color_function_specs);
	}

	@Override
	public BufferedImage readJavaImage(File image_file) throws IOException {
		JUtils.checkNull("image_file", image_file);
		FileInputStream is = image_file.newInputStream();
		InputStream java_is = is.toJavaInputStream();
		BufferedImage bad_image = ImageIO.read(java_is);
		if (bad_image == null) {
			L.d("Failed to read image", image_file);
			L.d("    exists", image_file.exists());
			L.d("      hash", image_file.calculateHash());
			L.d("      size", image_file.getSize());
			File parent = image_file.getFileSystem().newFile(
					image_file.getAbsoluteFilePath().parent());
			parent.listChildren().print();
			throw new IOException("Failed to read image: " + image_file);
		}
		is.close();
		return bad_image;
	}

	@Override
	public void writeJavaFile(java.awt.Image java_image, File file,
			String file_type) throws IOException {
		int width = java_image.getWidth(null);
		int height = java_image.getHeight(null);
		BufferedImage out = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = out.createGraphics();
		g2.drawImage(java_image, 0, 0, null);
		FileOutputStream os = file.newOutputStream();
		OutputStream java_os = os.toJavaOutputStream();
		ImageIO.write(out, file_type, java_os);
		os.flush();
		os.close();
	}

}
