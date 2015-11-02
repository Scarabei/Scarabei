package com.jfixby.cmns.api.image;

import com.jfixby.cmns.api.components.ComponentInstaller;

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

	public static ColorMap newArrayColorMap(ColorMapSpecs color_function_specs) {
		return invoke().newArrayColorMap(color_function_specs);
	}

	public static ColorMapSpecs newColorMapSpecs() {
		return invoke().newColorMapSpecs();
	}

}
