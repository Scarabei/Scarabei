
package com.jfixby.scarabei.api.image;

public interface ColorMapSpecs {

	void setLambdaColoredImage (ColoredLambdaImage base);

	void setColorMapWidth (int w);

	void setColorMapHeight (int h);

	void setColorMapDimentions (int w, int h);

	// ------------

	public int getColorMapWidth ();

	public int getColorMapHeight ();

	ColoredLambdaImage getLambdaColoredImage ();

	void setRed (GrayLambdaImage alpha);

	void setGreen (GrayLambdaImage alpha);

	void setBlue (GrayLambdaImage alpha);

	void setAlpha (GrayLambdaImage alpha);

	GrayLambdaImage getGreen ();

	GrayLambdaImage getRed ();

	GrayLambdaImage getBlue ();

	GrayLambdaImage getAlpha ();

}
