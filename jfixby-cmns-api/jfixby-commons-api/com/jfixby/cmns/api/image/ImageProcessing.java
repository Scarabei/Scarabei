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

	public static ColorFunction newColorFunction(ColorFunctionSpecs color_function_specs) {
		return invoke().newColorFunction(color_function_specs);
	}

	public static ColorFunctionSpecs newColorFunctionSpecs() {
		return invoke().newColorFunctionSpecs();
	}

}
