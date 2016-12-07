
package com.jfixby.red.net.http;

import java.io.IOException;

import com.jfixby.cmns.api.collections.CollectionScanner;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.collections.Map;
import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.file.ChildrenList;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileHash;
import com.jfixby.cmns.api.file.FileSystem;
import com.jfixby.cmns.api.file.FolderSupportingIndex;
import com.jfixby.cmns.api.file.FolderSupportingIndexEntry;
import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.net.http.HttpURL;
import com.jfixby.cmns.api.util.path.AbsolutePath;
import com.jfixby.cmns.api.util.path.RelativePath;
import com.jfixby.red.filesystem.AbstractRedFile;
import com.jfixby.red.filesystem.FilesList;
import com.jfixby.red.filesystem.RedFileHash;

class RedHttpFile extends AbstractRedFile implements File {

	private final RedHttpFileSystem fs;
	private final AbsolutePath<FileSystem> absolute_path;
	private final RelativePath relativePath;

	public RedHttpFile (final RedHttpFileSystem virtualFileSystem, final AbsolutePath<FileSystem> file_path) {
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
		final List<RedHttpFile> filesQueue = Collections.newList();
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
		final FolderSupportingIndex desc = this.parent().readDescriptor();
		final FolderSupportingIndexEntry value = desc.entries.get(this.getName());
		if (value == null) {
			return false;
		}
		return value.is_folder;
	}

	@Override
	public boolean isFile () throws IOException {
		if (this.absolute_path.isRoot()) {
			return !true;
		}
		final FolderSupportingIndex desc = this.parent().readDescriptor();
		final FolderSupportingIndexEntry value = desc.entries.get(this.getName());
		if (value == null) {
			return false;
		}
		return value.is_file;
	}

	@Override
	public boolean delete () {
		Err.throwNotImplementedYet();
		return false;

	}

	@Override
	public ChildrenList listDirectChildren () throws IOException {
		this.checkExists();
		this.checkIsFolder();

		final FolderSupportingIndex desc = this.readDescriptor();

		final FilesList listFiles = new FilesList();
// Collections.newMap(desc.entries).print("entries");
		Collections.scanCollection(desc.entries.keySet(), new CollectionScanner<String>() {
			@Override
			public void scanElement (final String key, final long i) {
				final FolderSupportingIndexEntry e = desc.entries.get(key);
				final String child_name = e.name;
				Debug.checkTrue("invalid name: key=" + key + " child_name=" + child_name, key.equals(child_name));
				{
					final File childFile = RedHttpFile.this.child(child_name);
					listFiles.add(childFile);
				}
			}
		});

		return listFiles;
	}

	private FolderSupportingIndex readDescriptor () throws IOException {
		final AbsolutePath<FileSystem> path = this.getAbsoluteFilePath().child(FolderSupportingIndex.FILE_NAME);

		final FolderSupportingIndex desc = this.getDescriptor(path);
		return desc;
	}

	private FolderSupportingIndex getDescriptor (final AbsolutePath<FileSystem> path) throws IOException {

		final HttpURL url = this.fs.getURLFor(path);
		FolderSupportingIndex desc = this.fs.getCachedDescriptor(url);
		if (desc == null) {
// L.d("not found", url);
// this.fs.printCache();
			final ByteArray data = HTTPOperator.readFile(url);
			desc = HTTPOperator.decode(data);
			this.caheValue(path, url, desc);
		}
		return desc;
	}

	private void caheValue (final AbsolutePath<FileSystem> path, final HttpURL url, final FolderSupportingIndex desc) {
		Debug.checkNull(desc.children);
		final Map<String, FolderSupportingIndex> children = Collections.newMap();
		children.putJavaMap(desc.children);
		desc.children.clear();
		this.fs.caheValue(url, desc);

// L.d("url", url);
// L.d("path", path);
// children.print("children");
// L.d();
		for (int i = 0; i < children.size(); i++) {
			final String key = children.getKeyAt(i);
			final FolderSupportingIndex val = children.get(key);
			final AbsolutePath<FileSystem> subPath = path.parent().child(key).child(FolderSupportingIndex.FILE_NAME);
			final HttpURL subUrl = this.fs.getURLFor(subPath);
			this.caheValue(subPath, subUrl, val);
// L.d("key", key);
// L.d("subPath", subPath);
// L.d("subUrl", subUrl);

// final HttpURL childUrl = url.child(key);

		}

	}

	@Override
	public RedHttpFile child (final String child_name) {
		return new RedHttpFile(this.getFileSystem(), this.absolute_path.child(child_name));
	}

	@Override
	public boolean exists () throws IOException {
		if (this.absolute_path.isRoot()) {
			return true;
		}
		final FolderSupportingIndex desc = this.parent().readDescriptor();
		final FolderSupportingIndexEntry value = desc.entries.get(this.getName());
		if (value == null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean makeFolder () {
		Err.throwNotImplementedYet();
		return false;
	}

	@Override
	public boolean rename (final String new_name) {
		Err.throwNotImplementedYet();
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
	public RedHttpFileSystem getFileSystem () {
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

			final FolderSupportingIndex desc = this.parent().readDescriptor();
			final FolderSupportingIndexEntry entry = desc.entries.get(this.getName());
			return entry.size;
		} else {
			return 0;
		}
	}

	@Override
	public java.io.File toJavaFile () {
		Err.reportError("The method is not supported");
		return null;
	}

	@Override
	public RedHttpFile parent () {
		return new RedHttpFile(this.fs, this.absolute_path.parent());
	}

	@Override
	public long lastModified () {

		try {
			final FolderSupportingIndex desc = this.parent().readDescriptor();
			final FolderSupportingIndexEntry entry = desc.entries.get(this.getName());
			return entry.lastModified;
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public FileHash calculateHash () throws IOException {
		final FolderSupportingIndex desc = this.parent().readDescriptor();
		final FolderSupportingIndexEntry entry = desc.entries.get(this.getName());
		return new RedFileHash(entry.hash);
	}

}
