
package com.jfixby.scarabei.red.desktop.test;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.jfixby.scarabei.api.desktop.DesktopSetup;
import com.jfixby.scarabei.api.desktop.ImageAWT;
import com.jfixby.scarabei.api.file.ChildrenList;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FileInputStream;
import com.jfixby.scarabei.api.file.FileOutputStream;
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

	public static void main (String[] args) throws IOException {
		DesktopSetup.deploy();

		File home = LocalFileSystem.ApplicationHome();
		File input_folder = home.child("input");
		File output_folder = home.child("output");

		ChildrenList pngs = input_folder.listDirectChildren().filterFiles(file -> file.extensionIs("png"));
		for (int i = 0; i < pngs.size(); i++) {
			File pngFile = pngs.getElementAt(i);

			File outputFile = output_folder.child(pngFile.nameWithoutExtension() + "-alpha.zipng");

			L.d("writing", outputFile);
			splitAndSaveAlphaChannel(pngFile, outputFile);
		}

	}

	private static void splitAndSaveAlphaChannel (File inputFile, File outputFile) throws IOException {
		BufferedImage awtImage = ImageAWT.readFromFile(inputFile);
		EditableColorMap colorMap = ImageAWT.newAWTColorMap(awtImage);
		EditableGrayMap alpha = colorMap.getAlpha();

		ColorMapSpecs spces = ImageProcessing.newColorMapSpecs();
		spces.setColorMapDimentions(colorMap.getWidth(), colorMap.getHeight());
		spces.setRed(alpha);
		spces.setGreen(alpha);
		spces.setBlue(alpha);
		ColorMap outputColorMap = ImageProcessing.newColorMap(spces);
		// BufferedImage resultingImage =
		// ImageAWT.toAWTImage(outputColorMap);
		BufferedImage resultingImage = ImageAWT.toAWTImage(outputColorMap.getRed());

		// L.d("writing", alphaChannelFile);
		FileOutputStream fileStream = outputFile.newOutputStream();
		GZipOutputStream zip = IO.newGZipStream(fileStream);

		ImageAWT.writeToStream(resultingImage, zip, "png", BufferedImage.TYPE_BYTE_GRAY);

		File testFile = outputFile.parent().child(outputFile.getName() + ".png");
		ImageAWT.writeToFile(resultingImage, testFile, "png", BufferedImage.TYPE_BYTE_GRAY);

		zip.flush();
		zip.close();
		fileStream.flush();
		fileStream.close();

		restoreCheck(outputFile);

	}

	private static void restoreCheck (File outputFile) throws IOException {
		File restoredFile = outputFile.parent().child(outputFile.getName() + "-restored.png");
		FileInputStream fileStream = outputFile.newInputStream();
		GZipInputStream zip = IO.newGZipStream(fileStream);
		BufferedImage restoredImage = ImageAWT.readFromStream(zip);
		fileStream.close();
		zip.close();
		ImageAWT.writeToFile(restoredImage, restoredFile, "png", BufferedImage.TYPE_BYTE_GRAY);
	}

}
