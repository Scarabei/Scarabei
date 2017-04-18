
package com.jfixby.scarabei.api.assets;

import com.jfixby.scarabei.api.collections.List;

public interface ID {

	public static final String SEPARATOR = ".";

	ID child (String string);

	ID parent ();

	String getLastStep ();

	boolean includes (ID other);

	ID child (ID subpackage);

	List<String> steps ();

}
