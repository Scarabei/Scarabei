package com.jfixby.cmns.api.image;

import com.jfixby.cmns.api.ComponentInstaller;

public class ImageProcessing {

	static private ComponentInstaller<ImageProcessingComponent> componentInstaller = new ComponentInstaller<ImageProcessingComponent>("ImageProcessing");

	public static final void installComponent(ImageProcessingComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final ImageProcessingComponent invoke() {
		return componentInstaller.invokeComponent();
	}

	public static final ImageProcessingComponent component() {
		return componentInstaller.getComponent();
	}

	public static ArrayColorMap newArrayColorMap(ArrayColorMapSpecs color_function_specs) {
		return invoke().newArrayColorMap(color_function_specs);
	}

	public static ArrayColorMapSpecs newArrayColorMapSpecs() {
		return invoke().newColorMapSpecs();
	}

	public static LambdaColorMapSpecs newLambdaColorMapSpecs() {
		return invoke().newLambdaColorMapSpecs();
	}

	public static LambdaColorMap newLambdaColorMap(LambdaColorMapSpecs lambda_specs) {
		return invoke().newLambdaColorMap(lambda_specs);
	}

}
