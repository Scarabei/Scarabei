package com.jfixby.cmns.api.color;

public interface CustomColor extends Color {

	CustomColor setValue(Color other);

	CustomColor setAlpha(float alpha);

	CustomColor setRed(float red);

	CustomColor setGreen(float green);

	CustomColor setBlue(float blue);

	CustomColor setARGB(int argb);

	Color mutliply(float f);

	CustomColor mix(Color other, float proportion);

	CustomColor mix(Color other);

}
