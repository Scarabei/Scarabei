package com.jfixby.red.desktop.img.processing;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.jfixby.cmns.api.image.ColorFunction;
import com.jfixby.cmns.api.image.ColorFunctionSpecs;
import com.jfixby.cmns.api.image.ImageProcessingComponent;
import com.jfixby.cmns.api.io.Buffer;

public class DesktopImageProcessing implements ImageProcessingComponent {

	

	@Override
	public ColorFunctionSpecs newColorFunctionSpecs() {
		return new DesktopColorFunctionSpecs();
	}

	@Override
	public ColorFunction newColorFunction(ColorFunctionSpecs color_function_specs) {
		return new DesktopColorFunction(color_function_specs);
	}

}
