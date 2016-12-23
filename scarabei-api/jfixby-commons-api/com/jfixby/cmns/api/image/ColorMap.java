
package com.jfixby.cmns.api.image;

public interface ColorMap extends ColoredλImage {

	int getWidth ();

	int getHeight ();

	ColoredλImage getLambdaImage ();

	GrayMap getAlpha ();

	GrayMap getRed ();

	GrayMap getGreen ();

	GrayMap getBlue ();

}
