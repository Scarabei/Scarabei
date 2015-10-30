package com.jfixby.cmns.api.filesystem.cache;

import com.jfixby.cmns.api.components.ComponentInstaller;
import com.jfixby.cmns.api.filesystem.File;

public class FileCache {

	static private ComponentInstaller<FileCacheComponent> componentInstaller = new ComponentInstaller<FileCacheComponent>(
			"FileCache");

	public static final void installComponent(
			FileCacheComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final FileCacheComponent invoke() {
		return componentInstaller.invokeComponent();
	}

	public static final FileCacheComponent component() {
		return componentInstaller.getComponent();
	}

	public static TempFolder createTempFolder(File where) {
		return invoke().createTempFolder(where);
	}

}
