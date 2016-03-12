package com.jfixby.cv.argb.test;

import java.io.IOException;

import com.jfixby.cmns.api.desktop.ImageAWT;
import com.jfixby.cmns.api.file.ChildrenList;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.LocalFileSystem;
import com.jfixby.cmns.api.image.ArrayColorMap;
import com.jfixby.cmns.api.image.ImageProcessing;
import com.jfixby.cmns.api.log.L;
import com.jfixby.red.desktop.DesktopAssembler;
import com.jfixby.red.desktop.image.RedImageAWT;

public class AlphaExtractorTest {

    public static void main(String[] args) throws IOException {

	DesktopAssembler.setup();
	ImageProcessing.installComponent(null);
	ImageAWT.installComponent(new RedImageAWT());

	File home = LocalFileSystem.ApplicationHome();
	File input_folder = home.child("sprites");
	File output_folder = home.child("output");

	ChildrenList textures = input_folder.listChildren().filter(file -> file.extensionIs(".png"));

	ExtractorSpecs alphaExtractorSpecs = ImageProcessing.newAlphaChannelExtractorSpecs();
	boolean zip = !false;
	alphaExtractorSpecs.setUseZIPCompression(zip);

	ChannelExtractor alphaExtractor = ImageProcessing.newAlphaChannelExtractor(alphaExtractorSpecs);

	for (int i = 0; i < textures.size(); i++) {
	    File input_png = textures.getElementAt(i);

	    ArrayColorMap image = ImageAWT.readAWTColorMap(input_png);
	    // image.getLambdaImage()
	    // ExtractionSettings settings =
	    // alphaExtractor.newExtractionSettings();
	    // settings.setInputFile(input_png);
	    // String name = input_png.getName();
	    // settings.setNameTag(name);
	    // ExtractionResult result = alphaExtractor.process(settings);
	    // result.print();
	}

	byte[] bytes = alphaExtractor.serialize();
	File output_file = output_folder
		.child("AlphaExtractorTest.file" + ImageProcessing.IMAGE_CHANNEL_FILE_EXTENTION);
	output_file.writeBytes(bytes);
	L.d("writing " + bytes.length / 1024 + " kBytes", output_file);

	alphaExtractor.saveAsPng(output_file.parent());

	RedAlphaChannelExtractor desealizator = new RedAlphaChannelExtractor();

	ChannelsList deserialized = desealizator.deserialize(bytes, zip);
	File out2 = output_folder.child("second");
	out2.makeFolder();
	RedAlphaChannelExtractor.savePagesAsPNG(out2, deserialized);

    }

}
