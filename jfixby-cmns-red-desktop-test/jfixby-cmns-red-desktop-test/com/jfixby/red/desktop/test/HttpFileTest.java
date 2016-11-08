
package com.jfixby.red.desktop.test;

import java.io.IOException;

import com.jfixby.cmns.adopted.gdx.json.RedJson;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.LocalFileSystem;
import com.jfixby.cmns.api.json.Json;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.net.http.Http;
import com.jfixby.cmns.api.net.http.HttpURL;
import com.jfixby.red.desktop.DesktopSetup;
import com.jfixby.red.filesystem.http.HttpFileSystem;
import com.jfixby.red.filesystem.http.HttpFileSystemSpecs;

public class HttpFileTest {

	public static void main (final String[] args) throws IOException {
		DesktopSetup.deploy();
		Json.installComponent(new RedJson());
		final HttpFileSystemSpecs specs = new HttpFileSystemSpecs();

		final String urlString = "http://localhost:8080/tinto-assets/assets";

		final HttpURL url = Http.newURL(urlString);

		L.d("url", url);

// final HTTPFileInfo info = HTTPOperator.getFileInfo(url);
// L.d("info", info);

		specs.setRootUrl(url);

		final String filename = "3-file.txt";
		//
		final HttpFileSystem fs = new HttpFileSystem(specs);
		//
		final File root = fs.ROOT();
		root.isFolder();
		//
		root.listAllChildren().print("list");

		final File download = LocalFileSystem.ApplicationHome().child("download");
		download.makeFolder();

		LocalFileSystem.copyFolderContentsToFolder(root, download);

	}

}
