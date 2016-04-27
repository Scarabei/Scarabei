
package com.jfixby.red.android.filesystem;

import java.io.IOException;

import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.file.ChildrenList;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.cmns.api.file.FileSystem;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.util.path.AbsolutePath;
import com.jfixby.cmns.api.util.path.RelativePath;
import com.jfixby.red.filesystem.AbstractRedFile;
import com.jfixby.red.filesystem.FilesList;

public class AndroidFile extends AbstractRedFile implements File {

	final private AbsolutePath<FileSystem> absolute_path;
	private final AndroidFileSystem fs;
	private final String absolute_path_string;

	public AndroidFile (final AbsolutePath<FileSystem> output_file_path, final AndroidFileSystem windowsFileSystem) {
		this.absolute_path = output_file_path;
		this.fs = windowsFileSystem;
		this.absolute_path_string = this.getAbsoluteWindowsPathString();
	}

	@Override
	public AbsolutePath<FileSystem> getAbsoluteFilePath () {
		return this.absolute_path;
	}

	@Override
	public boolean isFile () {
		final java.io.File f = new java.io.File(this.getAbsoluteWindowsPathString());
		return f.isFile();
	}

	@Override
	public long lastModified () {
		final java.io.File f = new java.io.File(this.getAbsoluteWindowsPathString());
		return f.lastModified();
	}

	@Override
	public boolean isFolder () {
		final java.io.File f = new java.io.File(this.getAbsoluteWindowsPathString());
		return f.isDirectory();
	}

	@Override
	public boolean exists () {
		final java.io.File f = new java.io.File(this.getAbsoluteWindowsPathString());
		return f.exists();
	}

	@Override
	public boolean rename (final String new_name) {
		final java.io.File f = new java.io.File(this.getAbsoluteWindowsPathString());
		final AndroidFile new_file = new AndroidFile(this.absolute_path.parent().child(new_name), this.fs);
		return f.renameTo(new java.io.File(new_file.getAbsoluteWindowsPathString()));
	}

	@Override
	public boolean makeFolder () {
		final java.io.File f = new java.io.File(this.getAbsoluteWindowsPathString());
		return f.mkdirs();
	}

	@Override
	public boolean delete () {
		if (this.isFolder()) {
			this.clearFolder();
		}
		final java.io.File f = new java.io.File(this.getAbsoluteWindowsPathString());

		return f.delete();

		// boolean result = f.delete();
		// L.d("result", result);
	}

	public String getAbsoluteWindowsPathString () {
		final String mount_point_path_string = "";
		String relative = toNativePathString(this.absolute_path.getRelativePath().getPathString());
		if (relative.length() > 0) {
			relative = AndroidFileSystem.OS_SEPARATOR + relative;
		}
		return AndroidFileSystem.OS_SEPARATOR + mount_point_path_string + relative;
	}

	public static String toNativePathString (final String string) {
		return string.replaceAll(RelativePath.SEPARATOR, AndroidFileSystem.OS_SEPARATOR);
	}

	@Override
	public ChildrenList listChildren () {
		final java.io.File file = new java.io.File(this.getAbsoluteWindowsPathString());
		if (!file.exists()) {
			throw new Error("File does not exist: " + file);
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

	@Override
	public void clearFolder () {
		if (this.isFolder()) {
			final ChildrenList children = this.listChildren();
			for (int i = 0; i < children.size(); i++) {
				// WinFile file = new WinFile(child);
				final File child = children.getElementAt(i);
				child.delete();
				// L.d("deleting", child.getAbsoluteFilePath());
			}
		} else {
			L.e("Unable to clear", this.absolute_path);
			L.e("       this is not a folder.");
		}
	}

	@Override
	public String toString () {
		return AndroidFileSystem.OS_SEPARATOR + this.absolute_path + "";
	}

	@Override
	public File child (final String child_name) {
		return new AndroidFile(this.getAbsoluteFilePath().child(child_name), this.getFileSystem());
	}

	@Override
	public String getName () {
		final java.io.File f = new java.io.File(this.getAbsoluteWindowsPathString());
		return f.getName();
	}

	@Override
	public AndroidFileSystem getFileSystem () {
		return this.fs;
	}

	@Override
	public String nameWithoutExtension () {
		final java.io.File file = new java.io.File(this.getAbsoluteWindowsPathString());
		final String name = file.getName();
		final int dotIndex = name.lastIndexOf('.');
		if (dotIndex == -1) {
			return name;
		}
		return name.substring(0, dotIndex);
	}

	public String toAbsolutePathString () {
		return this.getAbsoluteWindowsPathString();
	}

	@Override
	public FileInputStream newInputStream () throws IOException {
		return this.absolute_path.getMountPoint().newFileInputStream(this);
	}

	@Override
	public FileOutputStream newOutputStream () throws IOException {
		return this.absolute_path.getMountPoint().newFileOutputStream(this);
	}

	public java.io.File getJavaFile () {
		final java.io.File file = new java.io.File(this.getAbsoluteWindowsPathString());
		return file;
	}

	@Override
	public long getSize () {
		final java.io.File file = new java.io.File(this.getAbsoluteWindowsPathString());
		if (file.isFile()) {
			return file.length();
		} else {
			return 0;
		}
	}

	@Override
	public java.io.File toJavaFile () {
		final java.io.File f = new java.io.File(this.getAbsoluteWindowsPathString());
		return f;
	}

	@Override
	public File parent () {
		if (!this.absolute_path.isRoot()) {
			return new AndroidFile(this.absolute_path.parent(), this.fs);
		}
		throw new Error("This is already a root file. No parent available: " + this);
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.absolute_path_string == null) ? 0 : this.absolute_path_string.hashCode());
		return result;
	}

	@Override
	public boolean equals (final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final AndroidFile other = (AndroidFile)obj;
		if (this.absolute_path_string == null) {
			if (other.absolute_path_string != null) {
				return false;
			}
		} else if (!this.absolute_path_string.equals(other.absolute_path_string)) {
			return false;
		}
		return true;
	}

}
