
package com.jfixby.scarabei.api.file.cache;

import java.io.IOException;

import com.jfixby.scarabei.api.ComponentInstaller;
import com.jfixby.scarabei.api.file.File;

public class FileCache {

	static private ComponentInstaller<FileCacheComponent> componentInstaller = new ComponentInstaller<FileCacheComponent>(
		"FileCache");

	public static final void installComponent (final FileCacheComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static final FileCacheComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final FileCacheComponent component () {
		return componentInstaller.getComponent();
	}

	public static TempFolder createTempFolder (final File where) throws IOException {
		return invoke().createTempFolder(where);
	}

	public static TempFolder createTempFolder () throws IOException {
		return invoke().createTempFolder();
	}

}
