
package com.jfixby.r3.fokker.fs;

import java.io.Serializable;
import java.util.ArrayList;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.util.path.RelativePath;

public class GdxAssetsFileSystemIndex implements Serializable {

	public static final String INDEX_FILE_NAME = "gdx-filesystem.index";
	private static final String SALT = "";
	public ArrayList<GdxAssetsFileSystemIndexEntry> index = new ArrayList<GdxAssetsFileSystemIndexEntry>();

	public void print () {
		Collections.newList(index).print("index");
	}

	public static String internalFileName (RelativePath path) {
		// if (path.isRoot()) {
		// return MD5.md5String(path + SALT);
		// }
		// return MD5.md5String(path + SALT) + "." + path.getLastStep();
		// return MD5.md5String(path + SALT);
		// return path.toString().replaceAll("/", "#");
		return path.toString();
	}

	public void registerFile (RelativePath relativePath) {
		GdxAssetsFileSystemIndexEntry element = new GdxAssetsFileSystemIndexEntry();
		element.is_file = true;
		element.path = relativePath.toString();
		index.add(element);
	}

	public void registerFolder (RelativePath relativePath) {
		GdxAssetsFileSystemIndexEntry element = new GdxAssetsFileSystemIndexEntry();
		element.is_file = false;
		element.path = relativePath.toString();
		index.add(element);
	}
}
