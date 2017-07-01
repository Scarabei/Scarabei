
package com.jfixby.scarabei.red.filesystem.archived;

import java.io.IOException;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FileSystem;
import com.jfixby.scarabei.api.file.FilesList;
import com.jfixby.scarabei.api.file.packing.FileData;
import com.jfixby.scarabei.api.util.path.AbsolutePath;
import com.jfixby.scarabei.api.util.path.RelativePath;
import com.jfixby.scarabei.red.filesystem.AbstractRedFile;
import com.jfixby.scarabei.red.filesystem.RedFilesList;

public class PackedFile extends AbstractRedFile implements File {

	private final RedPackedFileSystem virtualFileSystem;
	private AbsolutePath<FileSystem> absolute_path;
	private RelativePath relativePath;

	public PackedFile (final RedPackedFileSystem virtualFileSystem, final AbsolutePath<FileSystem> file_path) {
		this.virtualFileSystem = virtualFileSystem;
		this.absolute_path = file_path;
		this.relativePath = file_path.getRelativePath();
	}

	@Override
	public AbsolutePath<FileSystem> getAbsoluteFilePath () {
		return this.absolute_path;
	}

	@Override
	public boolean delete () throws IOException {
		if (this.isFolder()) {
			this.clearFolder();
		}
		final PackedFileSystemContent content = this.virtualFileSystem.getContent();
		// L.d("deleting", this.absolute_path);
		;
		return content.delete(this.absolute_path.getRelativePath());

	}

	@Override
	public boolean isFolder () {
		final PackedFileSystemContent content = this.virtualFileSystem.getContent();
		return content.isFolder(this.absolute_path.getRelativePath());
	}

	@Override
	public boolean isFile () {
		final PackedFileSystemContent content = this.virtualFileSystem.getContent();
		return content.isFile(this.absolute_path.getRelativePath());
	}

	@Override
	public FilesList listDirectChildren () {
		final PackedFileSystemContent content = this.virtualFileSystem.getContent();

		if (!content.exists(this.relativePath)) {
			Err.reportError("File does not exist: " + this.absolute_path);
		}
		if (content.isFolder(this.relativePath)) {
			final List<String> files = Collections.newList(content.listChildren(this.relativePath));

			final RedFilesList listFiles = new RedFilesList();
			for (int i = 0; i < files.size(); i++) {
				final String file_i = files.getElementAt(i);

				final AbsolutePath<FileSystem> absolute_file = this.absolute_path.child(file_i);
				;
				listFiles.add(absolute_file.getMountPoint().newFile(absolute_file));
			}
			// L.d("listFiles", listFiles);

			//
			return listFiles;
		} else {
			Err.reportError("This is not a folder: " + this.absolute_path);
		}
		return null;
	}

	@Override
	public File child (final String child_name) {
		return new PackedFile(this.getFileSystem(), this.absolute_path.child(child_name));
	}

	@Override
	public boolean exists () {
		final PackedFileSystemContent content = this.virtualFileSystem.getContent();
		return content.exists(this.absolute_path.getRelativePath());
	}

	@Override
	public boolean makeFolder () {
		final PackedFileSystemContent content = this.virtualFileSystem.getContent();
		return content.mkdirs(this.absolute_path.getRelativePath());
	}

	@Override
	public boolean rename (final String new_name) {
		final PackedFileSystemContent content = this.virtualFileSystem.getContent();
		content.rename(this.absolute_path.getRelativePath(), new_name);
		this.absolute_path = this.absolute_path.parent().child(new_name);
		this.relativePath = this.absolute_path.getRelativePath();
		return true;
	}

	@Override
	public String getName () {
		if (this.relativePath.isRoot()) {
			return this.virtualFileSystem.getName();
		}
		return this.absolute_path.getName();
	}

	@Override
	public RedPackedFileSystem getFileSystem () {
		return this.virtualFileSystem;
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

	public FileData createFile () {
		if (this.relativePath.isRoot()) {
			return null;
		}
		final PackedFileSystemContent content = this.virtualFileSystem.getContent();
		if (!content.mkdirs(this.relativePath.parent())) {
			return null;
		}
		return content.createFile(this.relativePath);
	}

	public FileData getContent () throws IOException {
		final PackedFileSystemContent content = this.virtualFileSystem.getContent();
		return content.getContentLeaf(this.relativePath);
	}

	@Override
	public long getSize () {
		if (this.isFile()) {
			try {
				return this.getContent().getSize();
			} catch (final IOException e) {
				e.printStackTrace();
			}
			return 0;
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
	public File parent () {
		return new PackedFile(this.virtualFileSystem, this.absolute_path.parent());
	}

	@Override
	public long lastModified () {
		final PackedFileSystemContent content = this.virtualFileSystem.getContent();
		return content.lastModified(this.absolute_path.getRelativePath());
	}

}
