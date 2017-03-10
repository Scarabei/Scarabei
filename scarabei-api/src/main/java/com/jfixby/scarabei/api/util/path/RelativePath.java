
package com.jfixby.scarabei.api.util.path;

import com.jfixby.scarabei.api.collections.Collection;

public interface RelativePath {

	String getPathString ();

	// String getNativePathString();

	public static final String SEPARATOR = "/";

	RelativePath parent ();

	String nameWithoutExtension ();

	public String getExtension ();

	public boolean isRoot ();

	RelativePath child (String name);

	public Collection<String> steps ();

	String getLastStep ();

	boolean beginsWith (RelativePath other);

	RelativePath proceed (RelativePath value);

	int size ();

	RelativePath splitAt (int step);

	RelativePath removeStep (int index);

	String getStep (int index);

}
