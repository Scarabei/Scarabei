
package com.jfixby.cmns.ver;

public enum VERSION_STAGE {

	ALPHA("a"), BETTA("b"), RELEASE_CANDIDATE("rc");

	public final String tagName;

	VERSION_STAGE (final String tagName) {
		this.tagName = tagName;
	}

}
