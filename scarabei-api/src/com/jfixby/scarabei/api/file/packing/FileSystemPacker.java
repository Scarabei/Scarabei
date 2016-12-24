
package com.jfixby.scarabei.api.file.packing;

import java.io.IOException;

import com.jfixby.scarabei.api.ComponentInstaller;

public class FileSystemPacker {

	static private ComponentInstaller<FileSystemPackerComponent> componentInstaller = new ComponentInstaller<FileSystemPackerComponent>(
		"FileSystemPacker");

	public static final void installComponent (FileSystemPackerComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final FileSystemPackerComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final FileSystemPackerComponent component () {
		return componentInstaller.getComponent();
	}

	public static FileSystemUnpackingSpecs newUnpackingSpecs () {
		return invoke().newUnpackingSpecs();
	}

	public static PackedFileSystem unpack (FileSystemUnpackingSpecs unpacking_spec) throws IOException {
		return invoke().unpack(unpacking_spec);
	}

	public static FileSystemPackingSpecs newPackingSpecs () {
		return invoke().newPackingSpecs();
	}

	public static void pack (FileSystemPackingSpecs packing_spec) throws IOException {
		invoke().pack(packing_spec);
	}

	public static void installCompressionSchema (CompressionMethod schema) {
		invoke().installCompressionMethod(schema);
	}

}
