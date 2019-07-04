
package com.jfixby.scarabei.api.image;

public interface ColorMap extends ColoredLambdaImage {

	int getWidth ();

	int getHeight ();

	ColoredLambdaImage getLambdaImage ();

	GrayMap getAlpha ();

	GrayMap getRed ();

	GrayMap getGreen ();

	GrayMap getBlue ();

}
