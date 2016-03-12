
package com.jfixby.red.image.argb;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.jfixby.cmns.api.color.Color;
import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.desktop.ImageAWT;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.image.ArrayColorMap;
import com.jfixby.cmns.api.image.argb.ChannelDeserealizator;
import com.jfixby.cmns.api.image.argb.ChannelExtractor;
import com.jfixby.cmns.api.image.argb.ChannelsList;
import com.jfixby.cmns.api.image.argb.ExtractionResult;
import com.jfixby.cmns.api.image.argb.ExtractionSettings;
import com.jfixby.cmns.api.image.argb.ExtractorSpecs;
import com.jfixby.cmns.api.io.IO;

public class RedAlphaChannelExtractor implements ChannelExtractor, ChannelDeserealizator {

    private Color transparentColor;
    final RedAlphaPages pages = new RedAlphaPages();
    private boolean zip;

    public RedAlphaChannelExtractor(ExtractorSpecs alphaExtractorSpecs) {
	zip = alphaExtractorSpecs.useZIPCompression();

    }

    public RedAlphaChannelExtractor() {
    }

    private static void extractAlphaChannel(File file, RedAlphaChannelExtractor alphaInfo, String newPageFileName)
	    throws IOException {
	ArrayColorMap pngImage = ImageAWT.readAWTColorMap(file);
	int W = pngImage.getWidth();
	int H = pngImage.getHeight();
	alphaInfo.beginFile(newPageFileName, W, H);
	for (int x = 0; x < W; x++) {
	    for (int y = 0; y < H; y++) {
		float alpha = pngImage.getValue(x, y).alpha();
		alphaInfo.addAlphaValue((int) (alpha * 255), x, y);
	    }
	}
	alphaInfo.endFile(newPageFileName);
    }

    public void setTransparentColor(Color transparentColor) {
	this.transparentColor = transparentColor;
    }

    public void beginFile(String newPageFileName, int w, int h) {
	RedAlphaPage newPage = new RedAlphaPage(newPageFileName, w, h);
	pages.add(newPage);
    }

    public void addAlphaValue(int alpha, int x, int y) {
	pages.get(pages.size() - 1).addAlphaValue(alpha, x, y);
    }

    public void endFile(String newPageFileName) {
	pages.get(pages.size() - 1).checkValid(newPageFileName);
    }

    @Override
    public ExtractionResult process(ExtractionSettings settings) throws IOException {

	RedAlphaChannelExtractionResult result = new RedAlphaChannelExtractionResult();

	File input_png = settings.getInputFile();
	input_png = Debug.checkNull("input png", input_png);

	String tag = settings.getNameTag();
	tag = Debug.checkNull("name tag", tag);
	tag = Debug.checkEmpty("name tag", tag);

	extractAlphaChannel(input_png, this, tag);

	return result;
    }

    @Override
    public ExtractionSettings newExtractionSettings() {
	return new RedAlphaChannelExtractionSettings();
    }

    @Override
    public byte[] serialize() throws IOException {
	OutputStream os = null;
	ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	os = buffer;
	if (zip) {
	    os = new GZIPOutputStream(os);
	}
	IO.writeInt(os, pages.size());
	for (int i = 0; i < pages.size(); i++) {
	    RedAlphaPage.writePage(os, pages.get(i));
	}
	os.close();
	buffer.close();
	return buffer.toByteArray();
    }

    public RedAlphaPages deserialize(byte[] alphas_bytes, boolean zip) throws IOException {

	InputStream is = null;

	ByteArrayInputStream input = new ByteArrayInputStream(alphas_bytes);
	is = input;

	if (zip) {
	    is = new GZIPInputStream(is);
	}

	RedAlphaPages pages = new RedAlphaPages();
	int pagesNumber = IO.readInt(is);
	for (int i = 0; i < pagesNumber; i++) {
	    RedAlphaPage page = RedAlphaPage.readPage(is);
	    pages.add(page);
	}
	return pages;

    }

    @Override
    public void saveAsPng(File folder) throws IOException {
	savePagesAsPNG(folder, this.pages);
    }

    public static void savePagesAsPNG(File folder, ChannelsList pages) throws IOException {
	for (int i = 0; i < pages.size(); i++) {
	    RedAlphaPage page = (RedAlphaPage) pages.get(i);
	    File png_file = folder.child(page.getName() + "-alpha.png");
	    page.saveAsPng(png_file);
	}
    }

}
