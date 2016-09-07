
package com.jfixby.amazon.aws.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.cmns.api.file.FileSystem;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.sys.Sys;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.api.util.path.AbsolutePath;
import com.jfixby.cmns.api.util.path.RelativePath;
import com.jfixby.red.filesystem.AbstractFileSystem;

public class AWSS3FileSystem extends AbstractFileSystem implements FileSystem {

	private final String bucketName;

	private final AmazonS3Client s3;

	public AWSS3FileSystem (final AWSS3FileSystemConfig specs) {
		this.bucketName = Debug.checkNull("getBucketName()", specs.getBucketName());

		this.s3 = new AmazonS3Client();

// ByteArray data;
//
// {
// final RelativePath INDEX_PATH = JUtils.newRelativePath(GdxAssetsFileSystemIndex.INDEX_FILE_NAME);
// final FileHandle gdx_file = Gdx.files.internal(INDEX_PATH.toString());
// if (!gdx_file.exists()) {
// throw new Error("GdxFileSystemIndex is corrupted. File not found: " + GdxAssetsFileSystemIndex.INDEX_FILE_NAME
// + " at " + gdx_file);
// }
//
// data = JUtils.newByteArray(gdx_file.readBytes());
// // byte[] bytes = Base64.decode(gdx_string);
// // data = JUtils.newString(bytes);
// // L.d("data", data);
// }
// try {
// this.index_data = IO.deserialize(GdxAssetsFileSystemIndex.class, data);
// } catch (final IOException e) {
// e.printStackTrace();
// Err.reportError(e);
// }
// // index_data.print();
// for (int i = 0; i < this.index_data.index.size(); i++) {
// final GdxAssetsFileSystemIndexEntry entry = this.index_data.index.get(i);
// // L.d("importing", entry.path);
// final RelativePath path = JUtils.newRelativePath(entry.path);
// String file_name = internalFileName(path);
// if (GdxAssetsFileSystemIndex.INDEX_FILE_NAME.equals(entry.path)) {
// file_name = path.toString();
// }
// this.index.put(path, entry.is_file);
// final FileHandle gdx_file = Gdx.files.internal(file_name);
// if (entry.is_file && !gdx_file.exists()) {
// L.d("entry.is_file", entry.is_file);
// L.d(" gdx_file", gdx_file);
// L.d(" exists", gdx_file.exists());
// throw new Error("GdxFileSystemIndex is corrupted. File not found: " + gdx_file);
// }
// }
// index.print("index");

		this.index_is_loaded = false;

		// L.d("loading GdxAssetsFileSystem index:");
		// index.keys().print("");

	}

	public static String toS3Key (final RelativePath path) {
		return S3FileSystemIndex.toS3Key(path);
	}

	public static final String OS_SEPARATOR = "/";

	@Override
	public S3File newFile (final AbsolutePath<FileSystem> file_path) {
		if (file_path == null) {
			throw new Error("Filepath is null.");
		}
		if (file_path.getMountPoint() != this) {
			L.e("file_path", file_path);
			L.e("FileSystem", this.ROOT());
			throw new Error("Path does not belong to this filesystem: " + file_path);
		}
		return new S3File(file_path, this);
	}

	@Override
	public FileOutputStream newFileOutputStream (final File output_file) {
		throw new Error("Read-only file system!");
	}

	@Override
	public FileOutputStream newFileOutputStream (final File output_file, final boolean append) {
		throw new Error("Read-only file system!");
	}

	@Override
	public FileInputStream newFileInputStream (final File input_file) {
		if (input_file == null) {
			throw new Error("Input file is null.");
		}
		if (input_file.getFileSystem() != this) {
			throw new Error("Input file does not belong to this filesystem: " + input_file);
		}

		return new S3FileInputStream((S3File)input_file);
	}

	@Override
	public String nativeSeparator () {
		return OS_SEPARATOR;
	}

	final private String string_path = "S3BucketFileSystem";

	@Override
	public String toString () {
		return this.string_path;
	}

	public S3FileSystemIndex getIndex () {
		return this.index;
	}

	@Override
	public boolean isReadOnlyFileSystem () {
		return true;
	}

	public S3ObjectInfo getObjectInfo (final RelativePath relative) {
		return this.index.getObjectInfo(relative);
	}

	public S3FileSystemIndex index () {
		if (this.index == null) {
			final boolean rebuiltIndex = this.rebuildIndex();
			if (!rebuiltIndex) {
				Err.reportError("Failed to rebuild index!");
			}
		}
		return this.index;
	}

	private boolean rebuildIndex () {

		this.index = new S3FileSystemIndex();

		this.request(JUtils.newRelativePath());

		Sys.exit();

		return false;
	}

	private void request (final RelativePath target) {
		final ListObjectsRequest request = new ListObjectsRequest().withBucketName(this.bucketName);
		final String prefix = "" + target.toString();
		request.withPrefix(prefix);

		final ObjectListing objectListing = this.s3.listObjects(request);
		final List<S3ObjectSummary> list = Collections.newList(objectListing.getObjectSummaries());
		for (final S3ObjectSummary objectSummary : list) {
			final S3ObjectInfo registered = this.index.addObjectToIndex(objectSummary);
		}
		this.index.print("list");

	}

}
