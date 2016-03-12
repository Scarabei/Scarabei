package com.jfixby.cmns.api.image;

public interface ColorMapSpecs {

    void setLambdaColoredImage(ColoredλImage base);

    void setColorMapWidth(int w);

    void setColorMapHeight(int h);

    void setColorMapDimentions(int w, int h);

    // ------------

    public int getColorMapWidth();

    public int getColorMapHeight();

    ColoredλImage getLambdaColoredImage();

}
