package com.jfixby.red.desktop.test;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.jfixby.cmns.api.desktop.ImageAWT;
import com.jfixby.cmns.api.file.ChildrenList;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.LocalFileSystem;
import com.jfixby.cmns.api.image.ColorMap;
import com.jfixby.cmns.api.image.ColorMapSpecs;
import com.jfixby.cmns.api.image.EditableColorMap;
import com.jfixby.cmns.api.image.EditableGrayMap;
import com.jfixby.cmns.api.image.ImageProcessing;
import com.jfixby.red.desktop.DesktopAssembler;
import com.jfixby.red.desktop.image.RedImageAWT;

public class SplitChannelsTest {

    public static void main(String[] args) throws IOException {
	DesktopAssembler.setup();
	

	File home = LocalFileSystem.ApplicationHome();
	File input_folder = home.child("input");
	File output_folder = home.child("output");

	ChildrenList pngs = input_folder.listChildren().filter(file -> file.extensionIs(".png"));
	for (int i = 0; i < pngs.size(); i++) {
	    File file = pngs.getElementAt(i);
	    BufferedImage awtImage = ImageAWT.readFromFile(file);
	    EditableColorMap colorMap = ImageAWT.newAWTColorMap(awtImage);
	    EditableGrayMap alpha = colorMap.getAlpha();

	    ColorMapSpecs spces = ImageProcessing.newLambdaColorMapSpecs();
	    spces.setColorMapDimentions(colorMap.getWidth(), colorMap.getHeight());
	    spces.setRed(alpha);
	    spces.setGreen(alpha);
	    spces.setBlue(alpha);
	    ColorMap outputColorMap = ImageProcessing.newColorMap(spces);
	    BufferedImage resultingImage = ImageAWT.toAWTImage(outputColorMap);
	    File outputFile = output_folder.child(file.nameWithoutExtension() + "-alpha.png");
	    ImageAWT.writeToFile(resultingImage, outputFile, "png");
	}

    }

}
