package com.jfixby.red.desktop.test;

import java.io.IOException;

import com.jfixby.cmns.adopted.gdx.json.GdxJson;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.LocalFileSystem;
import com.jfixby.cmns.api.file.packing.CompressionMethod;
import com.jfixby.cmns.api.file.packing.FileSystemPacker;
import com.jfixby.cmns.api.file.packing.FileSystemUnpackingSpecs;
import com.jfixby.cmns.api.io.OutputStream;
import com.jfixby.cmns.api.json.Json;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.desktop.DesktopAssembler;
import com.jfixby.red.filesystem.archived.R3ArrayCompressionMethod;
import com.jfixby.red.filesystem.archived.RedFileSystemPacker;

public class UnPackFiles {

	public static void main(String[] args) throws IOException {

		DesktopAssembler.setup();
		Json.installComponent(new GdxJson());
		FileSystemPacker.installComponent(new RedFileSystemPacker());
		CompressionMethod schema = new R3ArrayCompressionMethod();
		FileSystemPacker.installCompressionSchema(schema);

		File where_to_unpack = LocalFileSystem.ApplicationHome().child("output");
		File folder_compressed = LocalFileSystem.ApplicationHome().child("compressed");
		FileSystemUnpackingSpecs unpacking_specs = FileSystemPacker.newUnpackingSpecs();

		File archive_file = folder_compressed.child("compressed-file-system.r3dat");
		unpacking_specs.setDataFile(archive_file);

		File files_package = FileSystemPacker.unpack(unpacking_specs).ROOT();
		files_package.getFileSystem().copyFolderContentsToFolder(files_package, where_to_unpack);

	}
}
