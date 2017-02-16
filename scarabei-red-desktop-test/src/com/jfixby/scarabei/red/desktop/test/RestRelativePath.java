
package com.jfixby.scarabei.red.desktop.test;

import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.util.JUtils;
import com.jfixby.scarabei.api.util.path.RelativePath;

public class RestRelativePath {

	public static final String desktopAssemblerRelativePathString = "tinto-run-desktop/com/jfixby/tinto/run/desktop/TintoDesktopAssembler.java";

	public static void main (final String[] args) {

		ScarabeiDesktop.deploy();

		final RelativePath path = JUtils.newRelativePath(desktopAssemblerRelativePathString);
		L.d("path", path);
		path.steps().print("steps");

		final RelativePath parent = path.parent();
		L.d("parent", parent);
		parent.steps().print("steps");

		final RelativePath rem = parent.removeStep(0);
		L.d("rem", rem);
		rem.steps().print("rem");
	}

}
