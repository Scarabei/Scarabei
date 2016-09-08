
package com.jfixby.red.filesystem;

import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.file.ChildrenList;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileSystem;
import com.jfixby.cmns.api.util.path.AbsolutePath;

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
	final public String toString () {
		return this.getAbsoluteFilePath() + "";
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
		throw new Error("This is already a root file. No parent available: " + this);
	}

	@Override
	final public boolean makeFolder () {
		final java.io.File f = new java.io.File(this.getAbsolutePathString());
		return f.mkdirs();
	}

	@Override
	final public boolean delete () {
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

	@Override
	final public ChildrenList listChildren () {
		final java.io.File file = new java.io.File(this.getAbsolutePathString());
		if (!file.exists()) {
			throw new Error("File does not exist: " + this.getAbsolutePathString());
		}
		if (file.isDirectory()) {
			final String[] list = file.list();

			final List<String> files = Collections.newList(list);
			final FilesList listFiles = new FilesList();
			for (int i = 0; i < files.size(); i++) {
				final String file_i = files.getElementAt(i);
				//
				// String parent =
				// absolute_path.getRelativePath().getPathString();
				// RelativePath relative = RedTriplane.Java().newRelativePath(
				// parent + RelativePath.SEPARATOR + file_i);
				// AbsolutePath absolute_file = new WinAbsolutePath(
				// (WinMountPoint) absolute_path.getMountPoint(), relative);

				final AbsolutePath<FileSystem> absolute_file = this.absolute_path.child(file_i);
				listFiles.add(absolute_file.getMountPoint().newFile(absolute_file));
			}
			// L.d("listFiles", listFiles);

			//
			return listFiles;
		} else {
			throw new Error("This is not a folder: " + this.absolute_path);
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
