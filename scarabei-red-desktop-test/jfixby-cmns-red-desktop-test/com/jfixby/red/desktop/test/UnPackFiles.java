
package com.jfixby.red.desktop.test;

import java.io.IOException;

import com.jfixby.cmns.api.desktop.DesktopSetup;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.LocalFileSystem;
import com.jfixby.cmns.api.file.packing.CompressionMethod;
import com.jfixby.cmns.api.file.packing.FileSystemPacker;
import com.jfixby.cmns.api.file.packing.FileSystemUnpackingSpecs;
import com.jfixby.cmns.api.json.Json;
import com.jfixby.red.filesystem.archived.R3ArrayCompressionMethod;
import com.jfixby.red.filesystem.archived.RedFileSystemPacker;

public class UnPackFiles {

	public static void main (final String[] args) throws IOException {

		DesktopSetup.deploy();
		Json.installComponent("com.jfixby.cmns.adopted.gdx.json.RedJson");
		FileSystemPacker.installComponent(new RedFileSystemPacker());
		final CompressionMethod schema = new R3ArrayCompressionMethod();
		FileSystemPacker.installCompressionSchema(schema);

		final File where_to_unpack = LocalFileSystem.ApplicationHome().child("output");
		final File folder_compressed = LocalFileSystem.ApplicationHome().child("compressed");
		final FileSystemUnpackingSpecs unpacking_specs = FileSystemPacker.newUnpackingSpecs();

		final File archive_file = folder_compressed.child("compressed-file-system.r3dat");
		unpacking_specs.setDataFile(archive_file);

		final File files_package = FileSystemPacker.unpack(unpacking_specs).ROOT();
		files_package.getFileSystem().copyFolderContentsToFolder(files_package, where_to_unpack);

	}
}
