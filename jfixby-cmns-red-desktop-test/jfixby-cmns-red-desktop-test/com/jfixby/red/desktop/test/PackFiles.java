
package com.jfixby.red.desktop.test;

import java.io.IOException;

import com.jfixby.cmns.adopted.gdx.json.RedJson;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.LocalFileSystem;
import com.jfixby.cmns.api.file.packing.CompressionMethod;
import com.jfixby.cmns.api.file.packing.FileSystemPacker;
import com.jfixby.cmns.api.file.packing.FileSystemPackingSpecs;
import com.jfixby.cmns.api.io.OutputStream;
import com.jfixby.cmns.api.json.Json;
import com.jfixby.cmns.api.log.L;
import com.jfixby.red.desktop.DesktopSetup;
import com.jfixby.red.filesystem.archived.R3ArrayCompressionMethod;
import com.jfixby.red.filesystem.archived.RedFileSystemPacker;

public class PackFiles {

	public static void main (final String[] args) throws IOException {

		DesktopSetup.deploy();
		Json.installComponent(new RedJson());
		FileSystemPacker.installComponent(new RedFileSystemPacker());
		final CompressionMethod schema = new R3ArrayCompressionMethod();
		FileSystemPacker.installCompressionSchema(schema);

		final File folder_to_pack = LocalFileSystem.ApplicationHome().child("input");
		final File folder_compressed = LocalFileSystem.ApplicationHome().child("compressed");
		final FileSystemPackingSpecs packing_specs = FileSystemPacker.newPackingSpecs();
		packing_specs.setFilesList(folder_to_pack.listChildren());
		final File archive_file = folder_compressed.child("compressed-file-system.r3dat");
		final OutputStream os = archive_file.newOutputStream();
		packing_specs.setOutputStream(os);
		packing_specs.setCompressionSchemaName(R3ArrayCompressionMethod.SCHEMA_NAME);

		L.d("Packing", folder_to_pack);
		L.d("     to", archive_file);

		os.open();
		FileSystemPacker.pack(packing_specs);
		os.close();

	}
}
