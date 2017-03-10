
package com.jfixby.scarabei.api.color;

import com.jfixby.scarabei.api.ComponentInstaller;
import com.jfixby.scarabei.api.image.ColorMap;
import com.jfixby.scarabei.api.image.GrayMap;

public class Colors {

	static private ComponentInstaller<ColorsComponent> componentInstaller = new ComponentInstaller<ColorsComponent>("Colors");

	public static final void installComponent (final ColorsComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static final ColorsComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final ColorsComponent component () {
		return componentInstaller.getComponent();
	}

	public static ColorConstant WHITE () {
		return invoke().WHITE();
	}

	public static ColorConstant GRAY () {
		return invoke().GRAY();
	}

	public static ColorConstant DARK_GRAY () {
		return invoke().DARK_GRAY();
	}

	public static ColorConstant LIGHT_GRAY () {
		return invoke().LIGHT_GRAY();
	}

	public static ColorConstant GREEN () {
		return invoke().GREEN();
	}

	public static ColorConstant BLUE () {
		return invoke().BLUE();
	}

	public static CustomColor newColor (final float R, final float G, final float B) {
		return invoke().newColor(R, G, B);
	}

	public static ColorConstant RED () {
		return invoke().RED();
	}

	public static ColorConstant BLACK () {
		return invoke().BLACK();
	}

	public static ColorConstant PURPLE () {
		return invoke().PURPLE();
	}//

	public static ColorConstant NO () {
		return invoke().NO();
	}

	public static ColorConstant YELLOW () {
		return invoke().YELLOW();
	}

	public static ColorConstant ORANGE () {
		return invoke().ORANGE();
	}

	public static Color FUCHSIA () {
		return invoke().FUCHSIA();
	}

	public static ColorConstant BROWN () {
		return invoke().BROWN();
	}

	public static ColorSet newColorSet () {
		return invoke().newColorSet();
	}

	public static CustomColor newRandomColor (final float alpha) {
		return invoke().newRandomColor(alpha);
	}

	public static CustomColor newColor (final float a, final float r, final float g, final float b) {
		return invoke().newColor(a, r, g, b);
	}

	public static CustomColor newColor () {
		return invoke().newColor();
	}

	public static float limit (final float f) {
		return invoke().limit(f);
	}

	public static <T> ColorMapping<T> newColorMapping () {
		return invoke().newColorMapping();
	}

	public static CustomColor newGray (final float gray) {
		return invoke().newGray(gray);
	}

	public static CustomColor newColor (final int argb) {
		return invoke().newColor(argb);
	}

	public static ColorRandomiser newColorRandomizer (final long seed) {
		return invoke().newColorRandomizer(seed);
	}

	public static Color newColor (final String hexstring) {
		return invoke().newColor(hexstring);
	}

	public static GraySet newGraySet () {
		return invoke().newGraySet();
	}

	public static GraySet newGraySet (final float... array) {
		return invoke().newGraySet(array);
	}

	public static GraySet newUniformGraySet (final int depth) {
		return invoke().newUniformGraySet(depth);
	}

	public static ColorSet newColorsSet (final ColorMap image) {
		return invoke().newColorsSet(image);
	}

	public static ColorDistance distanceRGB () {
		return invoke().distanceRGB();
	}

	public static CachedColorProjector colorProjectorCache (final ColorProjector input) {
		return invoke().colorProjectorCache(input);
	}

	public static GraySet newGraySet (final GrayMap image) {
		return invoke().newGraySet(image);
	}

}
