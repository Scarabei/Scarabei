
package com.jfixby.scarabei.red.desktop.test;

import java.io.IOException;

import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.LocalFileSystem;
import com.jfixby.scarabei.api.file.packing.CompressionMethod;
import com.jfixby.scarabei.api.file.packing.FileSystemPacker;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.red.filesystem.archived.R3ArrayCompressionMethod;
import com.jfixby.scarabei.red.filesystem.archived.RedFileSystemPacker;

public class CopyFilesTest {

	public static void main (final String[] args) throws IOException {

		ScarabeiDesktop.deploy();
		Json.installComponent("com.jfixby.cmns.adopted.gdx.json.RedJson");
		FileSystemPacker.installComponent(new RedFileSystemPacker());
		final CompressionMethod schema = new R3ArrayCompressionMethod();
		FileSystemPacker.installCompressionSchema(schema);

		final File folder_to_pack = LocalFileSystem.ApplicationHome().child("input");
		folder_to_pack.copyTo("input.copy");
		{
			final String ext = folder_to_pack.getExtension();
			L.d("ext", "<" + ext + ">");
		}
		final File file = folder_to_pack.child("sprite5.png");
		{
			final String ext = file.getExtension();
			L.d("ext", "<" + ext + ">");
		}
	}
}
