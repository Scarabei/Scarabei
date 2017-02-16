
package com.jfixby.scarabei.red.desktop.test;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.desktop.ImageAWT;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FileInputStream;
import com.jfixby.scarabei.api.file.FileOutputStream;
import com.jfixby.scarabei.api.file.FilesList;
import com.jfixby.scarabei.api.file.LocalFileSystem;
import com.jfixby.scarabei.api.image.ColorMap;
import com.jfixby.scarabei.api.image.ColorMapSpecs;
import com.jfixby.scarabei.api.image.EditableColorMap;
import com.jfixby.scarabei.api.image.EditableGrayMap;
import com.jfixby.scarabei.api.image.ImageProcessing;
import com.jfixby.scarabei.api.io.GZipInputStream;
import com.jfixby.scarabei.api.io.GZipOutputStream;
import com.jfixby.scarabei.api.io.IO;
import com.jfixby.scarabei.api.log.L;

public class SplitChannelsTest {

	public static void main (final String[] args) throws IOException {
		ScarabeiDesktop.deploy();

		final File home = LocalFileSystem.ApplicationHome();
		final File input_folder = home.child("input");
		final File output_folder = home.child("output");

		final FilesList pngs = input_folder.listDirectChildren().filter(file -> file.extensionIs("png"));
		for (int i = 0; i < pngs.size(); i++) {
			final File pngFile = pngs.getElementAt(i);

			final File outputFile = output_folder.child(pngFile.nameWithoutExtension() + "-alpha.zipng");

			L.d("writing", outputFile);
			splitAndSaveAlphaChannel(pngFile, outputFile);
		}

	}

	private static void splitAndSaveAlphaChannel (final File inputFile, final File outputFile) throws IOException {
		final BufferedImage awtImage = ImageAWT.readFromFile(inputFile);
		final EditableColorMap colorMap = ImageAWT.newAWTColorMap(awtImage);
		final EditableGrayMap alpha = colorMap.getAlpha();

		final ColorMapSpecs spces = ImageProcessing.newColorMapSpecs();
		spces.setColorMapDimentions(colorMap.getWidth(), colorMap.getHeight());
		spces.setRed(alpha);
		spces.setGreen(alpha);
		spces.setBlue(alpha);
		final ColorMap outputColorMap = ImageProcessing.newColorMap(spces);
		// BufferedImage resultingImage =
		// ImageAWT.toAWTImage(outputColorMap);
		final BufferedImage resultingImage = ImageAWT.toAWTImage(outputColorMap.getRed());

		// L.d("writing", alphaChannelFile);
		final FileOutputStream fileStream = outputFile.newOutputStream();
		final GZipOutputStream zip = IO.newGZipStream(fileStream);

		ImageAWT.writeToStream(resultingImage, zip, "png", BufferedImage.TYPE_BYTE_GRAY);

		final File testFile = outputFile.parent().child(outputFile.getName() + ".png");
		ImageAWT.writeToFile(resultingImage, testFile, "png", BufferedImage.TYPE_BYTE_GRAY);

		zip.flush();
		zip.close();
		fileStream.flush();
		fileStream.close();

		restoreCheck(outputFile);

	}

	private static void restoreCheck (final File outputFile) throws IOException {
		final File restoredFile = outputFile.parent().child(outputFile.getName() + "-restored.png");
		final FileInputStream fileStream = outputFile.newInputStream();
		final GZipInputStream zip = IO.newGZipStream(fileStream);
		final BufferedImage restoredImage = ImageAWT.readFromStream(zip);
		fileStream.close();
		zip.close();
		ImageAWT.writeToFile(restoredImage, restoredFile, "png", BufferedImage.TYPE_BYTE_GRAY);
	}

}
