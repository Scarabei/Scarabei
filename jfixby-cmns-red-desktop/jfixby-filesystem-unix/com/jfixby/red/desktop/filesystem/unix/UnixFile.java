package com.jfixby.red.desktop.filesystem.unix;

import java.io.IOException;

import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.filesystem.File;
import com.jfixby.cmns.api.filesystem.FileHash;
import com.jfixby.cmns.api.filesystem.FileInputStream;
import com.jfixby.cmns.api.filesystem.FileOutputStream;
import com.jfixby.cmns.api.filesystem.FileSystem;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.path.AbsolutePath;
import com.jfixby.cmns.api.path.ChildrenList;
import com.jfixby.cmns.api.path.RelativePath;
import com.jfixby.red.filesystem.FilesList;
import com.jfixby.red.filesystem.RedFileHash;

public class UnixFile implements File {
	@Override
	public void checkIsFolder() {
		checkExists();
		if (!this.isFolder()) {
			throw new Error("" + this + " is not a folder");
		}
	}

	@Override
	public void checkExists() {
		if (!this.exists()) {
			throw new Error(this + " does not exist.");
		}
	}

	@Override
	public void checkIsFile() {
		checkExists();
		if (!this.isFile()) {
			throw new Error(this + " does not exist.");
		}
	}

	final private AbsolutePath<FileSystem> absolute_path;
	private UnixFileSystem fs;
	private String absolute_path_string;

	public UnixFile(AbsolutePath<FileSystem> output_file_path,
			UnixFileSystem fileSystem) {
		this.absolute_path = output_file_path;
		this.fs = fileSystem;
		absolute_path_string = this.getAbsoluteWindowsPathString();
	}

	@Override
	public AbsolutePath<FileSystem> getAbsoluteFilePath() {
		return this.absolute_path;
	}

	@Override
	public boolean isFile() {
		java.io.File f = new java.io.File(this.getAbsoluteWindowsPathString());
		return f.isFile();
	}

	@Override
	public long lastModified() {
		java.io.File f = new java.io.File(this.getAbsoluteWindowsPathString());
		return f.lastModified();
	}

	@Override
	public boolean isFolder() {
		java.io.File f = new java.io.File(this.getAbsoluteWindowsPathString());
		return f.isDirectory();
	}

	@Override
	public boolean exists() {
		java.io.File f = new java.io.File(this.getAbsoluteWindowsPathString());
		return f.exists();
	}

	@Override
	public boolean rename(String new_name) {
		java.io.File f = new java.io.File(this.getAbsoluteWindowsPathString());
		UnixFile new_file = new UnixFile(this.absolute_path.parent().child(
				new_name), this.fs);
		return f.renameTo(new java.io.File(new_file.getAbsoluteWindowsPathString()));
	}

	@Override
	public boolean makeFolder() {
		java.io.File f = new java.io.File(this.getAbsoluteWindowsPathString());
		return f.mkdirs();
	}

	public boolean delete() {
		if (this.isFolder()) {
			this.clearFolder();
		}
		java.io.File f = new java.io.File(getAbsoluteWindowsPathString());

		return f.delete();

		// boolean result = f.delete();
		// L.d("result", result);
	}

	public String getAbsoluteWindowsPathString() {
		String mount_point_path_string = "";
		String relative = toNativePathString(absolute_path.getRelativePath()
				.getPathString());
		if (relative.length() > 0) {
			relative = UnixFileSystem.OS_SEPARATOR + relative;
		}
		return mount_point_path_string + relative;
	}

	public static String toNativePathString(String string) {
		return string.replaceAll(RelativePath.SEPARATOR,
				UnixFileSystem.OS_SEPARATOR);
	}

	@Override
	public ChildrenList listChildren() {
		java.io.File file = new java.io.File(getAbsoluteWindowsPathString());
		if (!file.exists()) {
			throw new Error("File does not exist: " + absolute_path);
		}
		if (file.isDirectory()) {
			String[] list = file.list();

			List<String> files = JUtils.newList(list);
			FilesList listFiles = new FilesList();
			for (int i = 0; i < files.size(); i++) {
				String file_i = files.getElementAt(i);
				//
				// String parent =
				// absolute_path.getRelativePath().getPathString();
				// RelativePath relative = RedTriplane.Java().newRelativePath(
				// parent + RelativePath.SEPARATOR + file_i);
				// AbsolutePath absolute_file = new WinAbsolutePath(
				// (WinMountPoint) absolute_path.getMountPoint(), relative);

				AbsolutePath<FileSystem> absolute_file = absolute_path
						.child(file_i);
				listFiles.add(absolute_file.getMountPoint().newFile(
						absolute_file));
			}
			// L.d("listFiles", listFiles);

			//
			return listFiles;
		} else {
			throw new Error("This is not a folder: " + this.absolute_path);
		}
	}

	@Override
	public void clearFolder() {
		if (this.isFolder()) {
			ChildrenList children = listChildren();
			for (int i = 0; i < children.size(); i++) {
				// WinFile file = new WinFile(child);
				File child = children.getElementAt(i);
				child.delete();
				// L.d("deleting", child.getAbsoluteFilePath());
			}
		} else {
			L.e("Unable to clear", absolute_path);
			L.e("       this is not a folder.");
		}
	}

	@Override
	public String toString() {
		return absolute_path + "";
	}

	@Override
	public File child(String child_name) {
		return new UnixFile(this.getAbsoluteFilePath().child(child_name),
				this.getFileSystem());
	}

	@Override
	public String getName() {
		java.io.File f = new java.io.File(this.getAbsoluteWindowsPathString());
		return f.getName();
	}

	@Override
	public UnixFileSystem getFileSystem() {
		return fs;
	}

	@Override
	public String nameWithoutExtension() {
		java.io.File file = new java.io.File(
				this.getAbsoluteWindowsPathString());
		String name = file.getName();
		int dotIndex = name.lastIndexOf('.');
		if (dotIndex == -1)
			return name;
		return name.substring(0, dotIndex);
	}

	public String toAbsolutePathString() {
		return this.getAbsoluteWindowsPathString();
	}

	@Override
	public FileHash calculateHash() throws IOException {
		return new RedFileHash(this.fs.md5Hex(this));
	}

	@Override
	public FileInputStream newInputStream() throws IOException {
		return absolute_path.getMountPoint().newFileInputStream(this);
	}

	@Override
	public FileOutputStream newOutputStream() throws IOException {
		return absolute_path.getMountPoint().newFileOutputStream(this);
	}

	@Override
	public String readToString() throws IOException {
		FileInputStream is = this.newInputStream();
		byte[] data = is.readAll();
		is.close();
		return new String(data);
	}

	@Override
	public byte[] readBytes() throws IOException {
		FileInputStream is = this.getFileSystem().newFileInputStream(this);
		byte[] bytes = is.readAll();
		is.close();
		return bytes;
	}

	@Override
	public void writeBytes(byte[] bytes) throws IOException {
		FileOutputStream os = this.getFileSystem().newFileOutputStream(this);
		os.write(bytes);
		os.close();
	}

	public java.io.File getJavaFile() {
		java.io.File file = new java.io.File(
				this.getAbsoluteWindowsPathString());
		return file;
	}

	@Override
	public long getSize() {
		java.io.File file = new java.io.File(
				this.getAbsoluteWindowsPathString());
		if (file.isFile()) {
			return file.length();
		} else {
			return 0;
		}
	}

	@Override
	public java.io.File toJavaFile() {
		java.io.File f = new java.io.File(this.getAbsoluteWindowsPathString());
		return f;
	}

	@Override
	public File parent() {
		if (!this.absolute_path.isRoot()) {
			return new UnixFile(this.absolute_path.parent(), this.fs);
		}
		throw new Error("This is already a root file. No parent available: "
				+ this);
	}

	@Override
	public void writeString(String bytes) throws IOException {
		this.writeBytes(bytes.getBytes());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((absolute_path_string == null) ? 0 : absolute_path_string
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnixFile other = (UnixFile) obj;
		if (absolute_path_string == null) {
			if (other.absolute_path_string != null)
				return false;
		} else if (!absolute_path_string.equals(other.absolute_path_string))
			return false;
		return true;
	}

	@Override
	public File proceed(RelativePath relativePath) {
		AbsolutePath<FileSystem> file_path = this.getAbsoluteFilePath()
				.proceed(relativePath);
		return this.getFileSystem().newFile(file_path);
	}
}
