
package com.jfixby.scarabei.api.color;

public interface CustomColor extends Color {

	CustomColor setValue (Color other);

	CustomColor setAlpha (float alpha);

	CustomColor setRed (float red);

	CustomColor setGreen (float green);

	CustomColor setBlue (float blue);

	CustomColor setARGB (int argb);

	Color mutliply (float f);

}
