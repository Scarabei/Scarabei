
package com.jfixby.scarabei.api.desktop;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import com.jfixby.scarabei.api.ComponentInstaller;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.image.ArrayColorMap;
import com.jfixby.scarabei.api.image.ArrayGrayMap;
import com.jfixby.scarabei.api.image.ColorMap;
import com.jfixby.scarabei.api.image.EditableColorMap;
import com.jfixby.scarabei.api.image.GrayMap;
import com.jfixby.scarabei.api.io.InputStream;
import com.jfixby.scarabei.api.io.OutputStream;

public class ImageAWT {

	static private ComponentInstaller<ImageAWTComponent> componentInstaller = new ComponentInstaller<>("ImageAWT");

	public static final void installComponent (final ImageAWTComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final ImageAWTComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static final ImageAWTComponent component () {
		return componentInstaller.getComponent();
	}

	public static BufferedImage readFromFile (final File image_file) throws IOException {
		return invoke().readFromFile(image_file);
	}

	public static void writeToFile (final BufferedImage javaImage, final File image_file, final String file_type)
		throws IOException {
		invoke().writeToFile(javaImage, image_file, file_type);
	}

	public static void writeToFile (final BufferedImage javaImage, final File image_file, final String file_type,
		final int awtImageMode) throws IOException {
		invoke().writeToFile(javaImage, image_file, file_type, awtImageMode);
	}

	public static EditableColorMap newAWTColorMap (final BufferedImage image) {
		return invoke().newAWTColorMap(image);
	}

	public static EditableColorMap newAWTColorMap (final java.io.InputStream java_input_stream) throws IOException {
		return invoke().readAWTColorMap(java_input_stream);
	}

	public static BufferedImage toAWTImage (final ColorMap image_function) {
		return invoke().toAWTImage(image_function);
	}

	public static ArrayColorMap readAWTColorMap (final File image_file) throws IOException {
		return invoke().readAWTColorMap(image_file);
	}

	public static void writeToFile (final ColorMap image, final File outputFile, final String file_type) throws IOException {
		invoke().writeToFile(image, outputFile, file_type);
	}

	public static void writeToFile (final GrayMap image, final File restoredFile, final String file_type) throws IOException {
		invoke().writeToFile(image, restoredFile, file_type);
	}

	public static BufferedImage toAWTImage (final GrayMap grayImage) {
		return invoke().toAWTImage(grayImage);
	}

	public static void writeToStream (final BufferedImage javaImage, final OutputStream outputStream, final String file_type,
		final int awtImageMode) throws IOException {
		invoke().writeToStream(javaImage, outputStream, file_type, awtImageMode);
	}

	public static BufferedImage readFromStream (final InputStream is) throws IOException {
		return invoke().readFromStream(is);
	}

	public static ArrayGrayMap readAWTGrayMap (final File image_file) throws IOException {
		return invoke().readAWTGrayMap(image_file);
	}

	public static GrayMap newAWTGrayMap (final BufferedImage awt_image) {
		return invoke().newAWTGrayMap(awt_image);
	}

	public static GrayMap awtScaleTo (final GrayMap image, final int width, final int height) {
		return invoke().awtScaleTo(image, width, height);
	}

	public static BufferedImage awtScaleTo (final BufferedImage javaImage, final int width, final int height) {
		return invoke().awtScaleTo(javaImage, width, height);
	}

	public static BufferedImage linearMix (final BufferedImage a, final float aWeight, final BufferedImage b,
		final float bWeight) {
		return invoke().linearMix(a, aWeight, b, bWeight);
	}

	public static BufferedImage toBufferedImage (final Image image) {
		return invoke().toBufferedImage(image);
	}

	public static Image awtScale (final Image java_image, final float scaleFactor) {
		return invoke().awtScale(java_image, scaleFactor);
	}

	public static GifProducerSpecs newGifProducerSpecs () {
		return invoke().newGifProducerSpecs();
	}

	public static GifProducer newGifProducer (final GifProducerSpecs producerSpecs) {
		return invoke().newGifProducer(producerSpecs);
	}

}
