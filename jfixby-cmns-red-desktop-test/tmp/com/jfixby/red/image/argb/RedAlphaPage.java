
package com.jfixby.red.image.argb;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import com.jfixby.cmns.api.color.Colors;
import com.jfixby.cmns.api.desktop.ImageAWT;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.image.ArrayColorMap;
import com.jfixby.cmns.api.image.ArrayColorMapSpecs;
import com.jfixby.cmns.api.image.ImageProcessing;
import com.jfixby.cmns.api.image.argb.Channel;

class RedAlphaPage implements Channel {

    @Override
    public String toString() {
	return "Page(" + newPageFileName + ") " + w + " x " + h + "";
    }

    int[] bytes;
    int pointer = 0;
    private final String newPageFileName;
    private int w;
    private int h;

    final public float getAlphaValue(final int x, final int y) {
	// this.pointer = x + this.w * y;
	this.pointer = y + this.h * x;
	return this.bytes[this.pointer] / 255f;
    }

    public RedAlphaPage(String newPageFileName, int w, int h) {
	bytes = new int[w * h];
	this.w = w;
	this.h = h;
	this.newPageFileName = newPageFileName;
    }

    public void addAlphaValue(int alpha, final int x, final int y) {
	this.pointer = y + this.h * x;
	bytes[pointer] = alpha;
    }

    public void writeTo(OutputStream buffer) throws IOException {
	checkValid(newPageFileName);
	ObjectOutputStream obj = new ObjectOutputStream(buffer);
	obj.writeObject(newPageFileName);
	obj.writeInt(w);
	obj.writeInt(h);
	obj.writeObject(bytes);
	obj.flush();
	// obj.close();
	// obj.close();
    }

    public static void writePage(OutputStream buffer, RedAlphaPage page) throws IOException {
	page.writeTo(buffer);
    }

    public static RedAlphaPage readPage(InputStream input) throws IOException {

	ObjectInputStream is = new ObjectInputStream(input);
	String newPageFileName;
	try {
	    newPageFileName = (String) is.readObject();

	    int w = is.readInt();
	    int h = is.readInt();
	    int[] bytes = (int[]) is.readObject();
	    RedAlphaPage page = new RedAlphaPage(newPageFileName, w, h);
	    page.bytes = bytes;
	    return page;
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	    throw new IOException(e);
	}
    }

    public String getName() {
	return this.newPageFileName;
    }

    public void saveAsPng(File png_file) throws IOException {
	ArrayColorMapSpecs image_specs = ImageProcessing.newArrayColorMapSpecs();
	image_specs.setWidth(w);
	image_specs.setHeight(h);

	ArrayColorMap image = ImageProcessing.newArrayColorMap(image_specs);

	for (int x = 0; x < w; x++) {
	    for (int y = 0; y < h; y++) {
		float alpha = this.getAlphaValue(x, y);
		image.setValue(x, y, Colors.newGray(alpha));
	    }
	}

	Image javaImage = ImageAWT.toAWTImage(image);
	ImageAWT.writeToFile(javaImage, png_file, "png");
    }

    public void checkValid(String newPageFileName) {
	if (!this.newPageFileName.equals(newPageFileName)) {
	    throw new Error("AlphaInfoPage<" + this.newPageFileName + "> is corrupted");
	}

    }

    public void checkValid(int w, int h) {
	if (w != this.w) {
	    throw new Error("AlphaInfoPage<" + this.newPageFileName + "> is corrupted");
	}
	if (h != this.h) {
	    throw new Error("AlphaInfoPage<" + this.newPageFileName + "> is corrupted");
	}
    }

}
