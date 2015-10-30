package com.jfixby.cmns.api.color;

public interface ColorsComponent {

	ColorConstant BLACK();

	ColorConstant RED();

	ColorConstant BLUE();

	ColorConstant GREEN();

	ColorConstant YELLOW();

	ColorConstant BROWN();

	ColorConstant GRAY();

	ColorConstant WHITE();

	ColorConstant PURPLE();

	ColorConstant NO();

	CustomColor newColor(float a, float r, float g, float b);

	CustomColor newColor();

	CustomColor newRandomColor();

	CustomColor newRandomColor(float alpha);

	CustomColor newColor(float r, float g, float b);

	double distance(Color x, Color y);

	CustomColor newColor(int argb);

	ColorConstant DARK_GRAY();

	ColorConstant LIGHT_GRAY();

	ColorsSet newColorsSet();

	float limit(float f);

	ColorConstant ORANGE();

	<T> ColorMapping<T> newColorMapping();

}
