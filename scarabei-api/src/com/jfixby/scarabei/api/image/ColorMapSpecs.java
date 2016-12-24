
package com.jfixby.scarabei.api.image;

public interface ColorMapSpecs {

	void setLambdaColoredImage (ColoredλImage base);

	void setColorMapWidth (int w);

	void setColorMapHeight (int h);

	void setColorMapDimentions (int w, int h);

	// ------------

	public int getColorMapWidth ();

	public int getColorMapHeight ();

	ColoredλImage getLambdaColoredImage ();

	void setRed (GrayλImage alpha);

	void setGreen (GrayλImage alpha);

	void setBlue (GrayλImage alpha);

	void setAlpha (GrayλImage alpha);

	GrayλImage getGreen ();

	GrayλImage getRed ();

	GrayλImage getBlue ();

	GrayλImage getAlpha ();

}
