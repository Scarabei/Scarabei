
package com.jfixby.amazon.aws.s3;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.api.util.path.RelativePath;

public class S3ObjectInfo {

	boolean isFolder;
	String s3Key;
	long size;
	long lastModified;
	RelativePath path;

	public S3ObjectInfo (final S3ObjectSummary objectSummary) {
		final S3ObjectInfo info = this;
		info.s3Key = objectSummary.getKey();
		info.isFolder = info.s3Key.endsWith("/");
		info.size = objectSummary.getSize();
		info.lastModified = objectSummary.getLastModified().getTime();

		final RelativePath path = JUtils.newRelativePath(info.s3Key);
		info.path = path;

	}

	public boolean exists () {
		return true;
	}

	public long length () {
		return this.size;
	}

	public long lastModified () {
		return this.lastModified;
	}

	public byte[] readBytes () {
		Err.reportNotImplementedYet();
		return null;
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

}
