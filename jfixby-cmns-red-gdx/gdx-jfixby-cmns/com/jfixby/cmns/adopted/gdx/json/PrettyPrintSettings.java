package com.jfixby.cmns.adopted.gdx.json;

public class PrettyPrintSettings {
	public OutputTypeID outputType;

	/** If an object on a single line fits this many columns, it won't wrap. */
	public int singleLineColumns;

	/** Arrays of floats won't wrap. */
	public boolean wrapNumericArrays;
}