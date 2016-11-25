
package com.jfixby.cmns.api.assets;

public interface ID {

	public static final String SEPARATOR = ".";

	ID child (String string);

	ID parent ();

	String getLastStep ();

	boolean includes (ID other);

	ID child (ID subpackage);

}
