package com.jfixby.cmns.api.color;


public interface Color {

	public float alpha();

	public float red();

	public float green();

	public float blue();

	public float getGrayscaleValue();

	public int toInteger();

	public String toFullHexString();

	public String toShortHexString();

//	public CustomColor multiply(float multiplier);
	
	public CustomColor customize();

	

}
