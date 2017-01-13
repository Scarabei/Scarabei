
package com.jfixby.scarabei.rmi.client.files;

import java.io.IOException;

import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.FilesList;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FileInputStream;
import com.jfixby.scarabei.api.file.FileOutputStream;
import com.jfixby.scarabei.api.file.FileSystem;
import com.jfixby.scarabei.api.util.path.AbsolutePath;
import com.jfixby.scarabei.api.util.path.RelativePath;
import com.jfixby.scarabei.red.filesystem.AbstractRedFile;
import com.jfixby.scarabei.red.filesystem.RedFilesList;

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
	public FilesList listAllChildren () {
		Err.throwNotImplementedYet();
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
	public FilesList listDirectChildren () throws IOException, Error {
		final RMIDataContainer content = this.virtualFileSystem.getContent();

		if (!content.exists(this.relativePath)) {
			Err.reportError("File does not exist: " + this.absolute_path);
		}
		if (content.isFolder(this.relativePath)) {
			final List<String> files = content.listChildren(this.relativePath);

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
		Err.reportError("The method is not supported");
		return null;
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
