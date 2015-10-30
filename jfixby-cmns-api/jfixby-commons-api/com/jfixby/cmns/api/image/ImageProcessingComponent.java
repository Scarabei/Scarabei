package com.jfixby.cmns.api.image;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.jfixby.cmns.api.filesystem.File;
import com.jfixby.cmns.api.io.Buffer;

public interface ImageProcessingComponent  {

	ColorFunction newColorFunction(Buffer buffer);

	ColorFunctionSpecs newColorFunctionSpecs();

	ColorFunction newColorFunction(ColorFunctionSpecs color_function_specs);

	BufferedImage readJavaImage(File image_file) throws IOException;

	void writeJavaFile(java.awt.Image java_img_icon, File file, String file_type) throws IOException;

}
