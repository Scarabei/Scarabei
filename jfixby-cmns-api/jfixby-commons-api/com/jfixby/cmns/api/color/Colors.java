package com.jfixby.cmns.api.color;

import com.jfixby.cmns.api.ComponentInstaller;

public class Colors {

    static private ComponentInstaller<ColorsComponent> componentInstaller = new ComponentInstaller<ColorsComponent>(
	    "Colors");

    public static final void installComponent(ColorsComponent component_to_install) {
	componentInstaller.installComponent(component_to_install);
    }

    public static final ColorsComponent invoke() {
	return componentInstaller.invokeComponent();
    }

    public static final ColorsComponent component() {
	return componentInstaller.getComponent();
    }

    public static ColorConstant WHITE() {
	return invoke().WHITE();
    }

    public static ColorConstant GRAY() {
	return invoke().GRAY();
    }

    public static ColorConstant DARK_GRAY() {
	return invoke().DARK_GRAY();
    }

    public static ColorConstant LIGHT_GRAY() {
	return invoke().LIGHT_GRAY();
    }

    public static ColorConstant GREEN() {
	return invoke().GREEN();
    }

    public static ColorConstant BLUE() {
	return invoke().BLUE();
    }

    public static CustomColor newColor(float R, float G, float B) {
	return invoke().newColor(R, G, B);
    }

    public static ColorConstant RED() {
	return invoke().RED();
    }

    public static ColorConstant BLACK() {
	return invoke().BLACK();
    }

    public static ColorConstant PURPLE() {
	return invoke().PURPLE();
    }//

    public static ColorConstant NO() {
	return invoke().NO();
    }

    public static ColorConstant YELLOW() {
	return invoke().YELLOW();
    }

    public static ColorConstant ORANGE() {
	return invoke().ORANGE();
    }

    public static Color FUCHSIA() {
	return invoke().FUCHSIA();
    }

    public static ColorConstant BROWN() {
	return invoke().BROWN();
    }

    public static ColorsSet newColorsSet() {
	return invoke().newColorsSet();
    }

    public static CustomColor newRandomColor(float alpha) {
	return invoke().newRandomColor(alpha);
    }

    public static CustomColor newColor(float a, float r, float g, float b) {
	return invoke().newColor(a, r, g, b);
    }

    public static double distance(Color A, Color B) {
	return invoke().distance(A, B);
    }

    public static CustomColor newColor() {
	return invoke().newColor();
    }

    public static float limit(float f) {
	return invoke().limit(f);
    }

    public static <T> ColorMapping<T> newColorMapping() {
	return invoke().newColorMapping();
    }

    public static CustomColor newGray(float gray) {
	return invoke().newGray(gray);
    }

    public static CustomColor newColor(int argb) {
	return invoke().newColor(argb);
    }

    public static ColorRandomiser newColorRandomizer(long seed) {
	return invoke().newColorRandomizer(seed);
    }

}
