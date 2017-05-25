
package com.jfixby.scarabei.aws.android.s3;

import java.io.IOException;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.util.JUtils;
import com.jfixby.scarabei.api.util.path.RelativePath;
import com.jfixby.scarabei.aws.android.s3.AndroidS3FileSystem;
import com.jfixby.scarabei.aws.android.s3.S3ObjectInfo;

class S3ObjectInfo {

	boolean isFolder;
	String s3Key;
	long size;
	long lastModified;
	RelativePath path;
	final List<String> directSubfolders = Collections.newList();
	final List<String> directChildFiles = Collections.newList();
	final List<S3ObjectInfo> allChildren = Collections.newList();

	private String md5;
	public boolean isPresentInS3Bucket = true;

	public S3ObjectInfo (final S3ObjectSummary objectSummary) {
		this.s3Key = objectSummary.getKey();
		this.isFolder = this.s3Key.endsWith("/");
		this.size = objectSummary.getSize();
		this.lastModified = objectSummary.getLastModified().getTime();
		this.path = JUtils.newRelativePath(this.s3Key);
		this.md5 = objectSummary.getETag().toUpperCase();
	}

	S3ObjectInfo () {
		this("");
	}

	S3ObjectInfo (final String prefix) {
		this.s3Key = prefix;
		this.isFolder = true;
		this.size = 0;
		this.lastModified = 0;
		this.path = JUtils.newRelativePath(prefix);
	}

	public void addAllChildren (final List<S3ObjectSummary> summs) {
		for (final S3ObjectSummary c : summs) {
			final S3ObjectInfo child = new S3ObjectInfo(c);
			this.allChildren.add(child);
		}
	}

	public long length () {
		return this.size;
	}

	public long lastModified () {
		return this.lastModified;
	}

	public byte[] readBytes (final AndroidS3FileSystem awss3FileSystem) throws IOException {
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

	public List<String> listDirectSubfolders () {
		return this.directSubfolders;
	}

	public List<String> listDirectChildFiles () {
		return this.directChildFiles;
	}

	public void addDirectSubfolders (final List<String> subfolders) {
		for (final String name : subfolders) {
			final List<String> steps = JUtils.split(name, RelativePath.SEPARATOR);
			final String properName = steps.getLast();
			this.directSubfolders.add(properName);
		}
	}

	public void addDirectChildFiles (final List<String> files) {
		for (final String name : files) {
			final List<String> steps = JUtils.split(name, RelativePath.SEPARATOR);
			final String properName = steps.getLast();
			this.directChildFiles.add(properName);
		}
	}

	public void print (final String tag) {
		L.d(tag, this);
		this.directSubfolders.print("subfolders");
		this.directChildFiles.print("files     ");
		this.allChildren.print("children  ");

	}

	public String md5 () {
		return this.md5;
	}

}
