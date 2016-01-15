package com.jfixby.red.desktop.test;

import java.io.IOException;

import com.jfixby.cmns.adopted.gdx.json.GdxJson;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.LocalFileSystem;
import com.jfixby.cmns.api.file.packing.CompressionSchema;
import com.jfixby.cmns.api.file.packing.FileSystemPacker;
import com.jfixby.cmns.api.file.packing.FileSystemPackingSpecs;
import com.jfixby.cmns.api.io.OutputStream;
import com.jfixby.cmns.api.json.Json;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.desktop.DesktopAssembler;
import com.jfixby.red.filesystem.archived.R3ArrayCompressionSchema;
import com.jfixby.red.filesystem.archived.RedFileSystemPacker;

public class CompressFileSystem {

	public static void main(String[] args) throws IOException {

		DesktopAssembler.setup();
		Json.installComponent(new GdxJson());
		FileSystemPacker.installComponent(new RedFileSystemPacker());
		CompressionSchema schema = new R3ArrayCompressionSchema();
		FileSystemPacker.installCompressionSchema(schema);

		File folder_to_pack = LocalFileSystem.ApplicationHome().child("input");
		File folder_compressed = LocalFileSystem.ApplicationHome().child("compressed");
		FileSystemPackingSpecs packing_specs = FileSystemPacker.newPackingSpecs();
		packing_specs.setFilesList(Collections.newList(folder_to_pack));
		File archive_file = folder_compressed.child("compressed-file-system.r3dat");
		OutputStream os = archive_file.newOutputStream();
		packing_specs.setOutputStream(os);
		packing_specs.setCompressionSchemaName(R3ArrayCompressionSchema.SCHEMA_NAME);

		L.d("Packing", folder_to_pack);
		L.d("     to", archive_file);

		FileSystemPacker.pack(packing_specs);

		os.close();

	}
}
