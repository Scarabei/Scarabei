
package com.jfixby.scarabei.api.geometry;

public enum ORIGIN_RELATIVE_VERTICAL {

	TOP(0d), CENTER(0.5d), BOTTOM(1d);

	public double relative_value;

	ORIGIN_RELATIVE_VERTICAL (double relative) {
		this.relative_value = relative;
	}

}
