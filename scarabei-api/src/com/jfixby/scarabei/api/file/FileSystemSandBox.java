
package com.jfixby.scarabei.api.file;

import java.io.IOException;

import com.jfixby.scarabei.api.ComponentInstaller;

public class FileSystemSandBox {

	static private ComponentInstaller<FileSystemSandBoxComponent> componentInstaller = new ComponentInstaller<FileSystemSandBoxComponent>(
		"FileSystemSandBox");

	public static final void installComponent (final FileSystemSandBoxComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final FileSystemSandBoxComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final FileSystemSandBoxComponent component () {
		return componentInstaller.getComponent();
	}

	public static FileSystem wrap (final String sandbox_name, final File content_folder) throws IOException {
		return invoke().wrap(sandbox_name, content_folder);
	}

}
