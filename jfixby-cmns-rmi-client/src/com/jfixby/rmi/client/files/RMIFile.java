
package com.jfixby.rmi.client.files;

import java.io.IOException;

import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.file.ChildrenList;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.cmns.api.file.FileSystem;
import com.jfixby.cmns.api.util.path.AbsolutePath;
import com.jfixby.cmns.api.util.path.RelativePath;
import com.jfixby.red.filesystem.AbstractRedFile;
import com.jfixby.red.filesystem.FilesList;

public class RMIFile extends AbstractRedFile implements File {

	private final RMIFileSystem virtualFileSystem;
	private AbsolutePath<FileSystem> absolute_path;
	private RelativePath relativePath;

	public RMIFile (final RMIFileSystem virtualFileSystem, final AbsolutePath<FileSystem> file_path) {
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
		final RMIDataContainer content = this.virtualFileSystem.getContent();
		// L.d("deleting", this.absolute_path);
		;
		return content.delete(this.absolute_path.getRelativePath());

	}

	@Override
	public ChildrenList listAllChildren () {
		Err.reportNotImplementedYet();
		return null;
	}

	@Override
	public boolean isFolder () throws IOException {
		final RMIDataContainer content = this.virtualFileSystem.getContent();
		return content.isFolder(this.absolute_path.getRelativePath());
	}

	@Override
	public boolean isFile () throws IOException {
		final RMIDataContainer content = this.virtualFileSystem.getContent();
		return content.isFile(this.absolute_path.getRelativePath());
	}

	@Override
	public ChildrenList listDirectChildren () throws IOException, Error {
		final RMIDataContainer content = this.virtualFileSystem.getContent();

		if (!content.exists(this.relativePath)) {
			throw new Error("File does not exist: " + this.absolute_path);
		}
		if (content.isFolder(this.relativePath)) {
			final List<String> files = content.listChildren(this.relativePath);

			final FilesList listFiles = new FilesList();
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
			throw new Error("This is not a folder: " + this.absolute_path);
		}
	}

	@Override
	public File child (final String child_name) {
		return new RMIFile(this.getFileSystem(), this.absolute_path.child(child_name));
	}

	@Override
	public boolean exists () throws IOException {
		final RMIDataContainer content = this.virtualFileSystem.getContent();
		return content.exists(this.absolute_path.getRelativePath());
	}

	@Override
	public boolean makeFolder () throws IOException {
		final RMIDataContainer content = this.virtualFileSystem.getContent();
		return content.mkdirs(this.absolute_path.getRelativePath());
	}

	@Override
	public boolean rename (final String new_name) throws IOException {
		final RMIDataContainer content = this.virtualFileSystem.getContent();
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
	public RMIFileSystem getFileSystem () {
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

	@Override
	public long getSize () throws IOException {
		if (this.isFile()) {
			// return this.getContent().getData().length;
			final RMIDataContainer content = this.virtualFileSystem.getContent();
			return content.getSize(this.relativePath);

		} else {
			return 0;
		}
	}

	@Override
	public java.io.File toJavaFile () {
		throw new Error("The method is not supported");
	}

	@Override
	public File parent () {
		return new RMIFile(this.virtualFileSystem, this.absolute_path.parent());
	}

	@Override
	public long lastModified () throws IOException {
		final RMIDataContainer content = this.virtualFileSystem.getContent();
		return content.lastModified(this.absolute_path.getRelativePath());
	}

	public FileInputStream getInputStream () {
		final RMIDataContainer content = this.virtualFileSystem.getContent();
		return content.getInputStream(this.absolute_path.getRelativePath());
	}

	public FileOutputStream getOutputStream (final boolean append) {
		final RMIDataContainer content = this.virtualFileSystem.getContent();
		return content.getOutputStream(this.absolute_path.getRelativePath(), append);
	}

}
