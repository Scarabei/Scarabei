package com.jfixby.red.filesystem.sandbox;

import java.io.IOException;

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

public class SandboxFile implements File {
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

	private RedSandboxFileSystem sandbox;
	private AbsolutePath<FileSystem> absolute_path;
	private RelativePath relativePath;
	private AbsolutePath<FileSystem> unprotected_path;

	public SandboxFile(RedSandboxFileSystem sandbox,
			AbsolutePath<FileSystem> file_path) {
		this.sandbox = sandbox;
		this.absolute_path = file_path;
		this.relativePath = file_path.getRelativePath();
		this.unprotected_path = sandbox.getRootFolder().getAbsoluteFilePath()
				.proceed(relativePath);
	}

	private File getUnprotectedFile() {
		File unprotected_file = unprotected_path.getMountPoint().newFile(
				unprotected_path);
		return unprotected_file;
	}

	@Override
	public AbsolutePath<FileSystem> getAbsoluteFilePath() {
		return absolute_path;
	}

	@Override
	public boolean delete() {
		File unprotected_file = getUnprotectedFile();
		return unprotected_file.delete();
	}

	@Override
	public boolean isFolder() {
		File unprotected_file = getUnprotectedFile();
		return unprotected_file.isFolder();
	}

	@Override
	public boolean isFile() {
		File unprotected_file = getUnprotectedFile();
		return unprotected_file.isFile();
	}

	@Override
	public void clearFolder() {
		if (this.isFolder()) {
			ChildrenList children = listChildren();
			for (int i = 0; i < children.size(); i++) {
				File child = children.getElementAt(i);
				// WinFile file = new WinFile(child);
				// File child = this.sandbox.newFile(child_path);

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
		return "File [" + absolute_path + "]";
	}

	@Override
	public ChildrenList listChildren() {
		File unprotected_file = getUnprotectedFile();

		if (!unprotected_file.exists()) {
			throw new Error("File does not exist: " + absolute_path);
		}
		if (unprotected_file.isFolder()) {

			ChildrenList unprotected_children = unprotected_file.listChildren();

			// List<String> files = content.listChildren(relativePath);

			FilesList listFiles = new FilesList();
			for (int i = 0; i < unprotected_children.size(); i++) {
				String file_i = unprotected_children.getElementAt(i).getName();
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
	public File child(String child_name) {
		return new SandboxFile(this.getFileSystem(), this.getAbsoluteFilePath()
				.child(child_name));
	}

	@Override
	public boolean exists() {
		File unprotected_file = getUnprotectedFile();
		return unprotected_file.exists();
	}

	@Override
	public boolean makeFolder() {
		File unprotected_file = getUnprotectedFile();
		return unprotected_file.makeFolder();
	}

	@Override
	public boolean rename(String new_name) {
		File unprotected_file = getUnprotectedFile();
		return unprotected_file.rename(new_name);
	}

	@Override
	public String getName() {
		if (this.relativePath.isRoot()) {
			return this.sandbox.getName();
		}
		return this.absolute_path.getName();
	}

	@Override
	public RedSandboxFileSystem getFileSystem() {
		return this.sandbox;
	}

	@Override
	public String nameWithoutExtension() {
		String name = getName();
		int dotIndex = name.lastIndexOf('.');
		if (dotIndex == -1)
			return name;
		return name.substring(0, dotIndex);
	}

	@Override
	public FileHash calculateHash() throws IOException {
		File unprotected_file = getUnprotectedFile();
		return unprotected_file.calculateHash();
	}

	@Override
	public FileInputStream newInputStream() throws IOException {
		File unprotected_file = getUnprotectedFile();
		return unprotected_file.newInputStream();
	}

	@Override
	public FileOutputStream newOutputStream() throws IOException {
		File unprotected_file = getUnprotectedFile();
		return unprotected_file.newOutputStream();
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

	@Override
	public long getSize() {
		if (this.isFile()) {
			File unprotected_file = getUnprotectedFile();
			return unprotected_file.getSize();
		} else {
			return 0;
		}
	}

	@Override
	public java.io.File toJavaFile() {
		File unprotected_file = getUnprotectedFile();
		return unprotected_file.toJavaFile();
	}

	@Override
	public File parent() {
		return new SandboxFile(this.getFileSystem(),
				this.absolute_path.parent());
	}

	@Override
	public void writeString(String bytes) throws IOException {
		this.writeBytes(bytes.getBytes());
	}

	@Override
	public long lastModified() {
		File unprotected_file = getUnprotectedFile();
		return unprotected_file.lastModified();
	}

	@Override
	public File proceed(RelativePath relative) {
		AbsolutePath<FileSystem> file_path = this.getAbsoluteFilePath()
				.proceed(relativePath);
		return this.getFileSystem().newFile(file_path);
	}

}
