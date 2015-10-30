package com.jfixby.cmns.api.assets;

public interface AssetID {

	public static final String SEPARATOR = ".";

	AssetID child(String string);

	AssetID parent();

	String getLastStep();

	boolean includes(AssetID other);

	AssetID child(AssetID subpackage);

}
