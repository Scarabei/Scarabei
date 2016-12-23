package com.jfixby.cmns.adopted.gdx.io;

import com.jfixby.cmns.adopted.gdx.json.red.JsonOutputType;

public class PrettyPrintSettings {
	public JsonOutputType outputType;

	/** If an object on a single line fits this many columns, it won't wrap. */
	public int singleLineColumns;

	/** Arrays of floats won't wrap. */
	public boolean wrapNumericArrays;
}