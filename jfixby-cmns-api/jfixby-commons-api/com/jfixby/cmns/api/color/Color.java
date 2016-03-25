package com.jfixby.cmns.api.color;

public interface Color  {

    public static final float grayscale_alpha = 1;
    public static final float grayscale_betta = 1;
    public static final float grayscale_gamma = 1;

    public float alpha();

    public float red();

    public float green();

    public float blue();
    
    public float getGrayscaleValue();

    public float getGrayscaleValue(float grayscale_alpha, float grayscale_betta, float grayscale_gamma);

    public int toInteger();

    public String toFullHexString();

    public String toShortHexString();

    // public CustomColor multiply(float multiplier);

    public CustomColor customize();

    CustomColor mix(Color other, float proportion);

    CustomColor mix(Color other);

    public CustomColor invert();

    public CustomColor toGrayscale();

}
