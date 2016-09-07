
package com.jfixby.amazon.aws.s3;

import java.io.IOException;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.api.util.path.RelativePath;

public class S3ObjectInfo {

	boolean isFolder;
	String s3Key;
	long size;
	long lastModified;
	RelativePath path;
	final List<String> subfolders = Collections.newList();
	final List<String> files = Collections.newList();

	public S3ObjectInfo (final S3ObjectSummary objectSummary) {
		this.s3Key = objectSummary.getKey();
		this.isFolder = this.s3Key.endsWith("/");
		this.size = objectSummary.getSize();
		this.lastModified = objectSummary.getLastModified().getTime();
		this.path = JUtils.newRelativePath(this.s3Key);
	}

	S3ObjectInfo () {
		this.s3Key = "";
		this.isFolder = true;
		this.size = 0;
		this.lastModified = 0;
		this.path = JUtils.newRelativePath();
	}

	public long length () {
		return this.size;
	}

	public long lastModified () {
		return this.lastModified;
	}

	public byte[] readBytes (final AWSS3FileSystem awss3FileSystem) throws IOException {
		return awss3FileSystem.readData(this.s3Key);
	}

	public boolean isFile () {
		return !this.isFolder;
	}

	@Override
	public String toString () {
		return "S3ObjectInfo [path=" + this.path + ", isFolder=" + this.isFolder + ", size=" + this.size + " lastModified="
			+ this.lastModified + "]";
	}

	public RelativePath getPath () {
		return this.path;
	}

	public boolean isFolder () {
		return this.isFolder;
	}

	public List<String> listSubfolders () {
		return this.subfolders;
	}

	public List<String> listFiles () {
		return this.files;
	}

	public void addSubFolders (final List<String> subfolders) {
		for (final String name : subfolders) {
			final List<String> steps = JUtils.split(name, RelativePath.SEPARATOR);
			final String properName = steps.getLast();
			this.subfolders.add(properName);
		}
	}

	public void addFiles (final List<String> files) {
		for (final String name : files) {
			final List<String> steps = JUtils.split(name, RelativePath.SEPARATOR);
			final String properName = steps.getLast();
			this.files.add(properName);
		}
	}

	public void print (final String tag) {
		L.d(tag, this);
		this.subfolders.print("subfolders");
		this.files.print("files     ");
	}

}
