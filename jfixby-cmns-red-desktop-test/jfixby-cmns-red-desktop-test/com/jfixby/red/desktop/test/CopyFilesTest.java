
package com.jfixby.red.desktop.test;

import java.io.IOException;

import com.jfixby.cmns.api.desktop.DesktopSetup;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.LocalFileSystem;
import com.jfixby.cmns.api.file.packing.CompressionMethod;
import com.jfixby.cmns.api.file.packing.FileSystemPacker;
import com.jfixby.cmns.api.json.Json;
import com.jfixby.cmns.api.log.L;
import com.jfixby.red.filesystem.archived.R3ArrayCompressionMethod;
import com.jfixby.red.filesystem.archived.RedFileSystemPacker;

public class CopyFilesTest {

	public static void main (final String[] args) throws IOException {

		DesktopSetup.deploy();
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
