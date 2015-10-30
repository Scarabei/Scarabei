package com.jfixby.cmns.api.filesystem;

import com.jfixby.cmns.api.components.ComponentInstaller;

public class FileSystemSandBox {

	static private ComponentInstaller<FileSystemSandBoxComponent> componentInstaller = new ComponentInstaller<FileSystemSandBoxComponent>(
			"FileSystemSandBox");

	public static final void installComponent(
			FileSystemSandBoxComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final FileSystemSandBoxComponent invoke() {
		return componentInstaller.invokeComponent();
	}

	public static final FileSystemSandBoxComponent component() {
		return componentInstaller.getComponent();
	}

	public static FileSystem wrap(String sandbox_name,
			File content_folder) {
		return invoke().wrap(sandbox_name, content_folder);
	}

}
