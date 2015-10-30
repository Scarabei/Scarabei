package com.jfixby.red.filesystem.virtual;

import java.io.IOException;

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

public class VirtualFile implements File {
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

	private VirtualFileSystem virtualFileSystem;
	private AbsolutePath<FileSystem> absolute_path;
	private RelativePath relativePath;

	public VirtualFile(VirtualFileSystem virtualFileSystem,
			AbsolutePath<FileSystem> file_path) {
		this.virtualFileSystem = virtualFileSystem;
		this.absolute_path = file_path;
		this.relativePath = file_path.getRelativePath();
	}

	@Override
	public AbsolutePath<FileSystem> getAbsoluteFilePath() {
		return absolute_path;
	}

	@Override
	public boolean delete() {
		if (this.isFolder()) {
			this.clearFolder();
		}
		VirtualFileSystemContent content = this.virtualFileSystem.getContent();
		// L.d("deleting", this.absolute_path);
		;
		return content.delete(this.absolute_path.getRelativePath());

	}

	@Override
	public boolean isFolder() {
		VirtualFileSystemContent content = this.virtualFileSystem.getContent();
		return content.isFolder(this.absolute_path.getRelativePath());
	}

	@Override
	public boolean isFile() {
		VirtualFileSystemContent content = this.virtualFileSystem.getContent();
		return content.isFile(this.absolute_path.getRelativePath());
	}

	@Override
	public void clearFolder() {
		if (this.isFolder()) {
			ChildrenList children = listChildren();
			for (int i = 0; i < children.size(); i++) {
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
		return "File [" + absolute_path + "]";
	}

	@Override
	public ChildrenList listChildren() {
		VirtualFileSystemContent content = this.virtualFileSystem.getContent();

		if (!content.exists(relativePath)) {
			throw new Error("File does not exist: " + absolute_path);
		}
		if (content.isFolder(relativePath)) {
			List<String> files = content.listChildren(relativePath);

			FilesList listFiles = new FilesList();
			for (int i = 0; i < files.size(); i++) {
				String file_i = files.getElementAt(i);

				AbsolutePath<FileSystem> absolute_file = absolute_path
						.child(file_i);
				;
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
		return new VirtualFile(this.getFileSystem(),
				this.absolute_path.child(child_name));
	}

	@Override
	public boolean exists() {
		VirtualFileSystemContent content = this.virtualFileSystem.getContent();
		return content.exists(this.absolute_path.getRelativePath());
	}

	@Override
	public boolean makeFolder() {
		VirtualFileSystemContent content = this.virtualFileSystem.getContent();
		return content.mkdirs(this.absolute_path.getRelativePath());
	}

	@Override
	public boolean rename(String new_name) {
		VirtualFileSystemContent content = this.virtualFileSystem.getContent();
		content.rename(this.absolute_path.getRelativePath(), new_name);
		this.absolute_path = this.absolute_path.parent().child(new_name);
		this.relativePath = this.absolute_path.getRelativePath();
		return true;
	}

	@Override
	public String getName() {
		if (this.relativePath.isRoot()) {
			return this.virtualFileSystem.getName();
		}
		return this.absolute_path.getName();
	}

	@Override
	public VirtualFileSystem getFileSystem() {
		return this.virtualFileSystem;
	}

	@Override
	public String nameWithoutExtension() {
		String name = getName();
		int dotIndex = name.lastIndexOf('.');
		if (dotIndex == -1)
			return name;
		return name.substring(0, dotIndex);
	}

	public ContentLeaf createFile() {
		if (this.relativePath.isRoot()) {
			return null;
		}
		VirtualFileSystemContent content = this.virtualFileSystem.getContent();
		if (!content.mkdirs(relativePath.parent())) {
			return null;
		}
		return content.createFile(this.relativePath);
	}

	public ContentLeaf getContent() {
		VirtualFileSystemContent content = this.virtualFileSystem.getContent();
		return content.getContentLeaf(this.relativePath);
	}

	@Override
	public FileHash calculateHash() throws IOException {
		return new RedFileHash(this.virtualFileSystem.md5Hex(this));
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

	@Override
	public long getSize() {
		if (this.isFile()) {
			return this.getContent().getData().length;
		} else {
			return 0;
		}
	}

	@Override
	public java.io.File toJavaFile() {
		throw new Error("The method is not supported");
	}

	@Override
	public File parent() {
		return new VirtualFile(virtualFileSystem, this.absolute_path.parent());
	}

	@Override
	public void writeString(String bytes) throws IOException {
		this.writeBytes(bytes.getBytes());
	}

	@Override
	public long lastModified() {
		VirtualFileSystemContent content = this.virtualFileSystem.getContent();
		return content.lastModified(this.absolute_path.getRelativePath());
	}

	@Override
	public File proceed(RelativePath relativePath) {
		AbsolutePath<FileSystem> file_path = this.getAbsoluteFilePath()
				.proceed(relativePath);
		return this.getFileSystem().newFile(file_path);
	}

}
