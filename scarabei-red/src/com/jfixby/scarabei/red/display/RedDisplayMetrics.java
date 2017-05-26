
package com.jfixby.scarabei.red.display;

import com.jfixby.scarabei.api.display.DisplayMetrics;

public class RedDisplayMetrics implements DisplayMetrics {

	private double width;
	private double height;

	public void set (final double width, final double height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public double getHeight () {
		return this.height;
	}

	@Override
	public double getWidth () {
		return this.width;
	}

}
