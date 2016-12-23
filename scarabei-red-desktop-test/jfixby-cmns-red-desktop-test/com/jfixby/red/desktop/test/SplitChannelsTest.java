
package com.jfixby.red.desktop.test;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.jfixby.cmns.api.desktop.DesktopSetup;
import com.jfixby.cmns.api.desktop.ImageAWT;
import com.jfixby.cmns.api.file.ChildrenList;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.cmns.api.file.LocalFileSystem;
import com.jfixby.cmns.api.image.ColorMap;
import com.jfixby.cmns.api.image.ColorMapSpecs;
import com.jfixby.cmns.api.image.EditableColorMap;
import com.jfixby.cmns.api.image.EditableGrayMap;
import com.jfixby.cmns.api.image.ImageProcessing;
import com.jfixby.cmns.api.io.GZipInputStream;
import com.jfixby.cmns.api.io.GZipOutputStream;
import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.log.L;

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
