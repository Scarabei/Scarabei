
package com.jfixby.scarabei.red.desktop.test;

import java.io.IOException;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.LocalFileSystem;
import com.jfixby.scarabei.api.file.packing.CompressionMethod;
import com.jfixby.scarabei.api.file.packing.FileSystemPacker;
import com.jfixby.scarabei.api.file.packing.FileSystemPackingSpecs;
import com.jfixby.scarabei.api.io.OutputStream;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.red.filesystem.archived.R3ArrayCompressionMethod;
import com.jfixby.scarabei.red.filesystem.archived.RedFileSystemPacker;

public class IOTest {

	public static void main (String[] args) throws IOException {

		ScarabeiDesktop.deploy();
		Json.installComponent("com.jfixby.scarabei.adopted.gdx.json.RedJson");
		FileSystemPacker.installComponent(new RedFileSystemPacker());
		CompressionMethod schema = new R3ArrayCompressionMethod();
		FileSystemPacker.installCompressionSchema(schema);

		File folder_to_pack = LocalFileSystem.ApplicationHome().child("input");
		File folder_compressed = LocalFileSystem.ApplicationHome().child("compressed");
		FileSystemPackingSpecs packing_specs = FileSystemPacker.newPackingSpecs();
		packing_specs.setFilesList(Collections.newList(folder_to_pack));
		File archive_file = folder_compressed.child("compressed-file-system.r3dat");
		OutputStream os = archive_file.newOutputStream();
		packing_specs.setOutputStream(os);
		packing_specs.setCompressionSchemaName(R3ArrayCompressionMethod.SCHEMA_NAME);

		L.d("Packing", folder_to_pack);
		L.d("     to", archive_file);

		FileSystemPacker.pack(packing_specs);

		os.close();

	}
}
