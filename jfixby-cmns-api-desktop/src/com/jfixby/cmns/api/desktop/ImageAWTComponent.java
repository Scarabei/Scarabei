package com.jfixby.cmns.api.desktop;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.image.ArrayColorMap;
import com.jfixby.cmns.api.image.ColorMap;

public interface ImageAWTComponent {

    BufferedImage readFromFile(File image_file) throws IOException;

    void writeToFile(java.awt.Image java_img_icon, File file, String file_type) throws IOException;

    ArrayColorMap newAWTColorMap(BufferedImage image);

    BufferedImage toAWTImage(ColorMap image_function);

    ArrayColorMap readAWTColorMap(java.io.InputStream java_is) throws IOException;

    ArrayColorMap readAWTColorMap(File image_file) throws IOException;

    void writeToFile(ColorMap image, File image_file, String file_type) throws IOException;

}
