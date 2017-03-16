
package com.jfixby.scarabei.red.desktop.test;

import java.io.IOException;

import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.LocalFileSystem;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.net.http.Http;
import com.jfixby.scarabei.api.net.http.HttpFileSystem;
import com.jfixby.scarabei.api.net.http.HttpFileSystemSpecs;
import com.jfixby.scarabei.api.net.http.HttpURL;

public class HttpFileTest {

	public static void main (final String[] args) throws IOException {
		ScarabeiDesktop.deploy();
		Json.installComponent("com.jfixby.scarabei.adopted.gdx.json.RedJson");
		final HttpFileSystemSpecs http_specs = Http.newHttpFileSystemSpecs();

		final String urlString = "http://localhost:8080/tinto-assets/assets";

		final HttpURL url = Http.newURL(urlString);

		L.d("url", url);

// final HTTPFileInfo info = HTTPOperator.getFileInfo(url);
// L.d("info", info);

		http_specs.setRootUrl(url);
		final HttpFileSystem fs = Http.newHttpFileSystem(http_specs);

		final String filename = "3-file.txt";
		//
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
