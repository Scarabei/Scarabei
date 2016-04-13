
package com.jfixby.cmns.api.file.cache;

import com.jfixby.cmns.api.ComponentInstaller;
import com.jfixby.cmns.api.file.File;

public class FileCache {

	static private ComponentInstaller<FileCacheComponent> componentInstaller = new ComponentInstaller<FileCacheComponent>(
		"FileCache");

	public static final void installComponent (final FileCacheComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final FileCacheComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final FileCacheComponent component () {
		return componentInstaller.getComponent();
	}

	public static TempFolder createTempFolder (final File where) {
		return invoke().createTempFolder(where);
	}

	public static TempFolder createTempFolder () {
		return invoke().createTempFolder();
	}

}
