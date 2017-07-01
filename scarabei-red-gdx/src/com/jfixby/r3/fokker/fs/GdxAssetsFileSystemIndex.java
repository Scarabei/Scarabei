
package com.jfixby.r3.fokker.fs;

import java.io.Serializable;
import java.util.ArrayList;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.md5.MD5;
import com.jfixby.scarabei.api.util.path.RelativePath;

public class GdxAssetsFileSystemIndex implements Serializable {

	private static final long serialVersionUID = -1735720358714267158L;
	public static final String INDEX_FILE_NAME = "gdx-filesystem.index";
	public ArrayList<GdxAssetsFileSystemIndexEntry> index = new ArrayList<GdxAssetsFileSystemIndexEntry>();
	public boolean collapsedFolders;
	public boolean encryptedNames;
	public String salt;

	public void print () {
		L.d("---GdxAssetsFileSystemIndex-------------------------------");
		Collections.newList(this.index).print("index");
		L.d("   collapsed folders", this.collapsedFolders);
		L.d("     encrypted names", this.encryptedNames);
		L.d("                salt", this.salt);
	}

	public static String internalFileName (final RelativePath path, final boolean collapsedFolders, final boolean encryptedNames,
		final String salt) {
		String stringPath = path.toString();
		if (stringPath.equals(INDEX_FILE_NAME)) {
			return stringPath;
		}

		if (collapsedFolders) {
			stringPath = stringPath.replaceAll("/", "#");
		}

		if (!encryptedNames) {
			return stringPath;
		}
		if (path.isRoot()) {
			return MD5.md5String(stringPath + salt).toString();
		}
// return MD5.md5String(stringPath + salt).toString() + "." + path.getLastStep();
		return MD5.md5String(stringPath + salt).toString();

	}

	public void registerFile (final RelativePath relativePath) {
		final GdxAssetsFileSystemIndexEntry element = new GdxAssetsFileSystemIndexEntry();
		element.is_file = true;
		element.path = relativePath.toString();
		this.index.add(element);
	}

	public void registerFolder (final RelativePath relativePath) {
		final GdxAssetsFileSystemIndexEntry element = new GdxAssetsFileSystemIndexEntry();
		element.is_file = false;
		element.path = relativePath.toString();
		this.index.add(element);
	}
}
