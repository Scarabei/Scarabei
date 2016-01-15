package com.jfixby.cmns.api.geometry;

public enum ORIGIN_RELATIVE_HORIZONTAL {

	LEFT(0d), CENTER(0.5d), RIGHT(1d);

	public double relative_value;

	ORIGIN_RELATIVE_HORIZONTAL(double relative) {
		this.relative_value = relative;
	}

}
