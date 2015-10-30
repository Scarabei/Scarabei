package com.jfixby.cmns.api.image;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.jfixby.cmns.api.components.ComponentInstaller;
import com.jfixby.cmns.api.filesystem.File;
import com.jfixby.cmns.api.io.Buffer;

public class ImageProcessing {

	static private ComponentInstaller<ImageProcessingComponent> componentInstaller = new ComponentInstaller<ImageProcessingComponent>(
			"ImageProcessing");

	public static final void installComponent(
			ImageProcessingComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final ImageProcessingComponent invoke() {
		return componentInstaller.invokeComponent();
	}

	public static final ImageProcessingComponent component() {
		return componentInstaller.getComponent();
	}

	public static ColorFunction newColorFunction(
			ColorFunctionSpecs color_function_specs) {
		return invoke().newColorFunction(color_function_specs);
	}

	public static ColorFunctionSpecs newColorFunctionSpecs() {
		return invoke().newColorFunctionSpecs();
	}

	public static ColorFunction newColorFunction(Buffer buffer) {
		return invoke().newColorFunction(buffer);
	}

	public static BufferedImage readJavaImage(File image_file)
			throws IOException {
		return invoke().readJavaImage(image_file);
	}

	public static void writeJavaFile(java.awt.Image javaImage, File image_file,
			String file_type) throws IOException {
		invoke().writeJavaFile(javaImage, image_file, file_type);
	}

}
