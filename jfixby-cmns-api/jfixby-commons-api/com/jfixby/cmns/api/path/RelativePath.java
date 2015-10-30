package com.jfixby.cmns.api.path;

import com.jfixby.cmns.api.collections.List;

public interface RelativePath {

	String getPathString();

	// String getNativePathString();

	public static final String SEPARATOR = "/";

	RelativePath parent();

	public boolean isRoot();

	RelativePath child(String name);

	public List<String> steps();

	String getLastStep();

	boolean beginsWith(RelativePath other);

	RelativePath proceed(RelativePath value);

}
