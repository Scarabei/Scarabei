
package com.jfixby.scarabei.api.display;

public class DisplayMetrics {

	private double width;
	private double height;

	public void set (final double width, final double height) {
		this.width = width;
		this.height = height;
	}

	public double getHeight () {
		return this.height;
	}

	public double getWidth () {
		return this.width;
	}

}
