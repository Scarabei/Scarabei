package com.jfixby.red.image;

import com.jfixby.cmns.api.image.ArrayColorMap;
import com.jfixby.cmns.api.image.ArrayColorMapSpecs;
import com.jfixby.cmns.api.image.ColorMap;
import com.jfixby.cmns.api.image.ColorMapSpecs;
import com.jfixby.cmns.api.image.ColoredλImageCache;
import com.jfixby.cmns.api.image.ImageProcessingComponent;

public class RedImageProcessing implements ImageProcessingComponent {

    @Override
    public ArrayColorMap newArrayColorMap(ArrayColorMapSpecs color_function_specs) {
	return new RedArrayColorMap(color_function_specs);
    }

    @Override
    public ArrayColorMapSpecs newArrayColorMapSpecs() {
	return new RedArrayColorMapSpecs();
    }

    @Override
    public ColorMapSpecs newColorMapSpecs() {
	return new RedColorMapSpecs();
    }

    @Override
    public ColoredλImageCache newImageCache(int width, int height) {
	return new RedColoredλImageCache(width, height);
    }

    @Override
    public ColorMap newColorMap(ColorMapSpecs lambda_specs) {
	return new RedColorMap(lambda_specs);
    }

}
