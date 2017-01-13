
package com.jfixby.scarabei.red.filesystem;

import java.io.IOException;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FileSystem;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.util.path.AbsolutePath;

public abstract class AbstractLocalFile<T extends AbstractLocalFileSystem> extends AbstractRedFile {
	public abstract String getAbsolutePathString ();

	AbsolutePath<FileSystem> absolute_path;
	private final T fs;

	public AbstractLocalFile (final AbsolutePath<FileSystem> absolute_path, final T fileSystem) {
		this.absolute_path = absolute_path;
		this.fs = fileSystem;

	}

	@Override
	final public T getFileSystem () {
		return this.fs;
	}

	@Override
	final public AbsolutePath<FileSystem> getAbsoluteFilePath () {
		return this.absolute_path;
	}

	@Override
	final public boolean rename (final String new_name) {
		final java.io.File f = new java.io.File(this.getAbsolutePathString());
		final AbsolutePath<FileSystem> newPath = this.getAbsoluteFilePath().parent().child(new_name);
		final File newFile = this.getFileSystem().newFile(newPath);
		final boolean success = f.renameTo(newFile.toJavaFile());
		if (success) {
			this.absolute_path = newPath;
		}
		return success;
	}

	@Override
	final public boolean isFile () {
		final java.io.File f = new java.io.File(this.getAbsolutePathString());
		return f.isFile();
	}

	@Override
	final public long lastModified () {
		final java.io.File f = new java.io.File(this.getAbsolutePathString());
		return f.lastModified();
	}

	@Override
	final public File child (final String child_name) {
		return this.getFileSystem().newFile(this.getAbsoluteFilePath().child(child_name));

	}

	@Override
	final public String nameWithoutExtension () {
		final java.io.File file = new java.io.File(this.getAbsolutePathString());
		final String name = file.getName();
		final int dotIndex = name.lastIndexOf('.');
		if (dotIndex == -1) {
			return name;
		}
		return name.substring(0, dotIndex);
	}

	@Override
	final public File parent () {
		if (!this.getAbsoluteFilePath().isRoot()) {
			return this.getFileSystem().newFile(this.getAbsoluteFilePath().parent());
		}
		Err.reportError("This is already a root file. No parent available: " + this);
		return null;
	}

	@Override
	final public boolean makeFolder () {
		final java.io.File f = new java.io.File(this.getAbsolutePathString());
		return f.mkdirs();
	}

	@Override
	final public boolean delete () throws IOException {
		if (this.isFolder()) {
			this.clearFolder();
		}
		final java.io.File f = new java.io.File(this.getAbsolutePathString());
		return f.delete();
	}

	@Override
	final public java.io.File toJavaFile () {
		final java.io.File f = new java.io.File(this.getAbsolutePathString());
		return f;
	}

	@Override
	final public boolean isFolder () {
		final java.io.File f = new java.io.File(this.getAbsolutePathString());
		return f.isDirectory();
	}

	@Override
	final public boolean exists () {
		final java.io.File f = new java.io.File(this.getAbsolutePathString());
		return f.exists();
	}

	static final boolean DIRECT_CHILDREN = true;
	static final boolean ALL_CHILDREN = !DIRECT_CHILDREN;

	@Override
	final public RedFilesList listDirectChildren () {
		final List<AbstractLocalFile<T>> filesQueue = Collections.newList();
		filesQueue.add(this);
		final RedFilesList result = new RedFilesList();
		collectChildren(filesQueue, result, DIRECT_CHILDREN);

		return result;

	}

	@Override
	final public RedFilesList listAllChildren () {
		final List<AbstractLocalFile<T>> filesQueue = Collections.newList();
		filesQueue.add(this);
		final RedFilesList result = new RedFilesList();
		collectChildren(filesQueue, result, ALL_CHILDREN);

		return result;

	}

	static private <T extends AbstractLocalFileSystem> void collectChildren (final List<AbstractLocalFile<T>> filesQueue,
		final RedFilesList result, final boolean directFlag) {
		while (filesQueue.size() > 0) {
			final AbstractLocalFile<T> nextfile = filesQueue.removeElementAt(0);
			final java.io.File file = new java.io.File(nextfile.getAbsolutePathString());
			if (!file.exists()) {
				Err.reportError("File does not exist: " + nextfile.getAbsolutePathString());
			}

			if (file.isDirectory()) {
				final String[] list = file.list();
				if (list == null) {
					L.e("list() is null", file);
					continue;
				}
				final List<String> files = Collections.newList(list);

				for (int i = 0; i < files.size(); i++) {
					final String file_i = files.getElementAt(i);
					final AbsolutePath<FileSystem> absolute_file = nextfile.absolute_path.child(file_i);
					final T fs = (T)absolute_file.getMountPoint();
					final AbstractLocalFile<T> child = (AbstractLocalFile<T>)fs.newFile(absolute_file);
					result.add(child);
					if (directFlag == ALL_CHILDREN) {

						if (child.isFolder()) {
							filesQueue.add(child);
						}
					} else {

					}
				}

			} else {
				Err.reportError("This is not a folder: " + nextfile.absolute_path);
			}

		}
	}

	final public String toAbsolutePathString () {
		return this.getAbsolutePathString();
	}

	final public java.io.File getJavaFile () {
		final java.io.File file = new java.io.File(this.getAbsolutePathString());
		return file;
	}

	@Override
	final public long getSize () {
		final java.io.File file = new java.io.File(this.getAbsolutePathString());
		if (file.isFile()) {
			return file.length();
		} else {
			return 0;
		}
	}

	@Override
	final public String getName () {
		final java.io.File f = new java.io.File(this.getAbsolutePathString());
		return f.getName();
	}

	@Override
	final public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getAbsolutePathString() == null) ? 0 : this.getAbsolutePathString().hashCode());
		return result;
	}

	@Override
	final public boolean equals (final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final AbstractLocalFile other = (AbstractLocalFile)obj;
		if (this.getAbsolutePathString() == null) {
			if (other.getAbsolutePathString() != null) {
				return false;
			}
		} else if (!this.getAbsolutePathString().equals(other.getAbsolutePathString())) {
			return false;
		}
		return true;
	}

}
