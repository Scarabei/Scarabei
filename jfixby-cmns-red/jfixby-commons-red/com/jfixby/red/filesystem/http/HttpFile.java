
package com.jfixby.red.filesystem.http;

import java.io.IOException;

import com.jfixby.cmns.api.collections.CollectionScanner;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.file.ChildrenList;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileHash;
import com.jfixby.cmns.api.file.FileSystem;
import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.net.http.HttpURL;
import com.jfixby.cmns.api.util.path.AbsolutePath;
import com.jfixby.cmns.api.util.path.RelativePath;
import com.jfixby.red.filesystem.AbstractRedFile;
import com.jfixby.red.filesystem.FilesList;
import com.jfixby.red.filesystem.RedFileHash;
import com.jfixby.red.filesystem.http.descript.HttpFileEntry;
import com.jfixby.red.filesystem.http.descript.HttpFolderDescriptor;

public class HttpFile extends AbstractRedFile implements File {

	private final HttpFileSystem fs;
	private final AbsolutePath<FileSystem> absolute_path;
	private final RelativePath relativePath;

	public HttpFile (final HttpFileSystem virtualFileSystem, final AbsolutePath<FileSystem> file_path) {
		this.fs = virtualFileSystem;
		this.absolute_path = file_path;
		this.relativePath = file_path.getRelativePath();
	}

	@Override
	public AbsolutePath<FileSystem> getAbsoluteFilePath () {
		return this.absolute_path;
	}

	@Override
	public ChildrenList listAllChildren () throws IOException {
		final List<HttpFile> filesQueue = Collections.newList();
		filesQueue.add(this);
		final FilesList result = new FilesList();
		final ChildrenList children = this.listDirectChildren();
		for (final File f : children) {
			result.add(f);
			if (f.isFolder()) {
				final ChildrenList sub_list = f.listAllChildren();
				result.addAll(sub_list);
			}
		}
		return result;
	}

	@Override
	public boolean isFolder () throws IOException {
		if (this.absolute_path.isRoot()) {
			return true;
		}
		final HttpFolderDescriptor desc = this.parent().readDescriptor();
		return desc.entries.get(this.getName()).is_folder;
	}

	@Override
	public boolean isFile () throws IOException {
		if (this.absolute_path.isRoot()) {
			return !true;
		}
		final HttpFolderDescriptor desc = this.parent().readDescriptor();
		return desc.entries.get(this.getName()).is_file;
	}

	@Override
	public boolean delete () {
		Err.reportNotImplementedYet();
		return false;

	}

	@Override
	public String toString () {
		return "HttpFile [" + this.absolute_path + "]";
	}

	@Override
	public ChildrenList listDirectChildren () throws IOException {
		this.checkExists();
		this.checkIsFolder();

		final HttpFolderDescriptor desc = this.readDescriptor();

		final FilesList listFiles = new FilesList();

		Collections.scanCollection(desc.entries.keySet(), new CollectionScanner<String>() {
			@Override
			public void scanElement (final String key, final int i) {
				final HttpFileEntry e = desc.entries.get(key);
				final String child_name = e.name;
				Debug.checkTrue("valid name", key.equals(child_name));
				final File childFile = HttpFile.this.child(child_name);
				listFiles.add(childFile);
			}
		});

		return listFiles;
	}

	private HttpFolderDescriptor readDescriptor () throws IOException {

		final HttpURL url = this.fs
			.getURLFor(this.getAbsoluteFilePath().child(HttpFolderDescriptor.HTTP_FOLDER_DESCRIPTOR_FILE_NAME));

// final HTTPFileInfo info = this.getFileInfo(url);
//
// if (!info.codeIs(200)) {
// throw new IOException("Folder decscriptor not found " + url);
// }

		final HttpFolderDescriptor desc = this.getDescriptor(url);

		return desc;
	}

	private HttpFolderDescriptor getDescriptor (final HttpURL url) throws IOException {
		HttpFolderDescriptor desc = this.fs.getCachedDescriptor(url);
		if (desc == null) {
			final ByteArray data = HTTPOperator.readFile(url);
			desc = HTTPOperator.decode(data);
			this.fs.caheValue(url, desc);
		}
		return desc;
	}

// private HTTPFileInfo getFileInfo (final HttpURL url) {
//
// HTTPFileInfo info = this.fs.getCachedInfo(url);
//
// if (info == null) {
// info = HTTPOperator.getFileInfo(url);
// this.fs.caheValue(url, info);
// }
//
// return info;
// }

	@Override
	public HttpFile child (final String child_name) {
		return new HttpFile(this.getFileSystem(), this.absolute_path.child(child_name));
	}

	@Override
	public boolean exists () throws IOException {
		return this.isFile() || this.isFolder();
	}

	@Override
	public boolean makeFolder () {
		Err.reportNotImplementedYet();
		return false;
	}

	@Override
	public boolean rename (final String new_name) {
		Err.reportNotImplementedYet();
		return false;
	}

	@Override
	public String getName () {
		if (this.relativePath.isRoot()) {
			return this.fs.getName();
		}
		return this.absolute_path.getName();
	}

	@Override
	public HttpFileSystem getFileSystem () {
		return this.fs;
	}

	@Override
	public String nameWithoutExtension () {
		final String name = this.getName();
		final int dotIndex = name.lastIndexOf('.');
		if (dotIndex == -1) {
			return name;
		}
		return name.substring(0, dotIndex);
	}

	@Override
	public long getSize () throws IOException {
		if (this.isFile()) {

			final HttpFolderDescriptor desc = this.parent().readDescriptor();
			final HttpFileEntry entry = desc.entries.get(this.getName());
			return entry.size;
		} else {
			return 0;
		}
	}

	@Override
	public java.io.File toJavaFile () {
		throw new Error("The method is not supported");
	}

	@Override
	public HttpFile parent () {
		return new HttpFile(this.fs, this.absolute_path.parent());
	}

	@Override
	public long lastModified () {

		try {
			final HttpFolderDescriptor desc = this.parent().readDescriptor();
			final HttpFileEntry entry = desc.entries.get(this.getName());
			return entry.lastModified;
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public FileHash calculateHash () throws IOException {
		final HttpFolderDescriptor desc = this.parent().readDescriptor();
		final HttpFileEntry entry = desc.entries.get(this.getName());
		return new RedFileHash(entry.hash);
	}

}
