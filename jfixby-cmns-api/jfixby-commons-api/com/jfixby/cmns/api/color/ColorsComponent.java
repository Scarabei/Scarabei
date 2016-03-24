package com.jfixby.cmns.api.color;

import com.jfixby.cmns.api.image.ColorMap;

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

    CustomColor newColor(int argb);

    ColorConstant DARK_GRAY();

    ColorConstant LIGHT_GRAY();

    ColorSet newColorsSet();

    float limit(float f);

    ColorConstant ORANGE();

    <T> ColorMapping<T> newColorMapping();

    CustomColor newGray(float gray);

    ColorRandomiser newColorRandomizer(long seed);

    Color FUCHSIA();

    Color newColor(String hexstring);

    GraySet newGraySet();

    GraySet newGraySet(float... array);

    GraySet newUniformGraySet(int depth);

    ColorSet newColorsSet(ColorMap image);

    ColorDistance distanceRGB();

    CachedColorProjector colorProjectorCache(ColorProjector input);

}
