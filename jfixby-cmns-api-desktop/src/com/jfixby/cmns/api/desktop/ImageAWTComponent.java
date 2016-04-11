
package com.jfixby.cmns.api.desktop;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.image.ArrayColorMap;
import com.jfixby.cmns.api.image.ArrayGrayMap;
import com.jfixby.cmns.api.image.ColorMap;
import com.jfixby.cmns.api.image.GrayMap;
import com.jfixby.cmns.api.io.InputStream;
import com.jfixby.cmns.api.io.OutputStream;

public interface ImageAWTComponent {

	BufferedImage readFromFile (File image_file) throws IOException;

	void writeToFile (BufferedImage java_img_icon, File file, String file_type) throws IOException;

	ArrayColorMap newAWTColorMap (BufferedImage image);

	BufferedImage toAWTImage (ColorMap image_function);

	ArrayColorMap readAWTColorMap (java.io.InputStream java_is) throws IOException;

	ArrayColorMap readAWTColorMap (File image_file) throws IOException;

	void writeToFile (ColorMap image, File image_file, String file_type) throws IOException;

	void writeToFile (GrayMap image, File image_file, String file_type) throws IOException;

	BufferedImage toAWTImage (GrayMap grayImage);

	void writeToFile (BufferedImage java_image, File file, String file_type, int image_mode) throws IOException;

	void writeToStream (BufferedImage javaImage, OutputStream outputStream, String file_type, int awtImageMode) throws IOException;

	BufferedImage readFromStream (InputStream is) throws IOException;

	ArrayGrayMap readAWTGrayMap (File image_file) throws IOException;

	ArrayGrayMap newAWTGrayMap (BufferedImage img);

	GrayMap awtScaleTo (GrayMap image, int width, int height);

	BufferedImage awtScaleTo (BufferedImage javaImage, int width, int height);

	BufferedImage linearMix (BufferedImage a, float aWeight, BufferedImage b, float bWeight);

	BufferedImage toBufferedImage (Image image);

}
