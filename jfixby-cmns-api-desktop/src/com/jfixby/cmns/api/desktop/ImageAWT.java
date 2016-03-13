package com.jfixby.cmns.api.desktop;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.jfixby.cmns.api.ComponentInstaller;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.image.ArrayColorMap;
import com.jfixby.cmns.api.image.ColorMap;
import com.jfixby.cmns.api.image.EditableColorMap;
import com.jfixby.cmns.api.image.GrayMap;
import com.jfixby.cmns.api.io.InputStream;
import com.jfixby.cmns.api.io.OutputStream;

public class ImageAWT {

    static private ComponentInstaller<ImageAWTComponent> componentInstaller = new ComponentInstaller<ImageAWTComponent>(
	    "ImageAWT");

    public static final void installComponent(ImageAWTComponent component_to_install) {
	componentInstaller.installComponent(component_to_install);
    }

    public static final ImageAWTComponent invoke() {
	return componentInstaller.invokeComponent();
    }

    public static final ImageAWTComponent component() {
	return componentInstaller.getComponent();
    }

    public static BufferedImage readFromFile(File image_file) throws IOException {
	return invoke().readFromFile(image_file);
    }

    public static void writeToFile(java.awt.Image javaImage, File image_file, String file_type) throws IOException {
	invoke().writeToFile(javaImage, image_file, file_type);
    }

    public static void writeToFile(java.awt.Image javaImage, File image_file, String file_type, int awtImageMode)
	    throws IOException {
	invoke().writeToFile(javaImage, image_file, file_type, awtImageMode);
    }

    public static EditableColorMap newAWTColorMap(BufferedImage image) {
	return invoke().newAWTColorMap(image);
    }

    public static EditableColorMap newAWTColorMap(java.io.InputStream java_input_stream) throws IOException {
	return invoke().readAWTColorMap(java_input_stream);
    }

    public static BufferedImage toAWTImage(ColorMap image_function) {
	return invoke().toAWTImage(image_function);

    }

    public static ArrayColorMap readAWTColorMap(File image_file) throws IOException {
	return invoke().readAWTColorMap(image_file);
    }

    public static void writeToFile(ColorMap image, File restoredFile, String file_type) throws IOException {
	invoke().writeToFile(image, restoredFile, file_type);
    }

    public static BufferedImage toAWTImage(GrayMap grayImage) {
	return invoke().toAWTImage(grayImage);

    }

    public static void writeToStream(java.awt.Image javaImage, OutputStream outputStream, String file_type,
	    int awtImageMode) throws IOException {
	invoke().writeToStream(javaImage, outputStream, file_type, awtImageMode);
    }

    public static BufferedImage readFromStream(InputStream is) throws IOException {
	return invoke().readFromStream(is);
    }

}
