
package com.jfixby.red.filesystem.sandbox;

import java.io.IOException;

import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.file.ChildrenList;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileHash;
import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.cmns.api.file.FileSystem;
import com.jfixby.cmns.api.util.path.AbsolutePath;
import com.jfixby.cmns.api.util.path.RelativePath;
import com.jfixby.red.filesystem.AbstractRedFile;
import com.jfixby.red.filesystem.FilesList;

public class SandboxFile extends AbstractRedFile implements File {

	private final RedSandboxFileSystem sandbox;
	private final AbsolutePath<FileSystem> absolute_path;
	private final RelativePath relativePath;
	private final AbsolutePath<FileSystem> unprotected_path;

	public SandboxFile (final RedSandboxFileSystem sandbox, final AbsolutePath<FileSystem> file_path) {
		this.sandbox = sandbox;
		this.absolute_path = file_path;
		this.relativePath = file_path.getRelativePath();
		this.unprotected_path = sandbox.getRootFolder().getAbsoluteFilePath().proceed(this.relativePath);
	}

	private File getUnprotectedFile () {
		final File unprotected_file = this.unprotected_path.getMountPoint().newFile(this.unprotected_path);
		return unprotected_file;
	}

	@Override
	public AbsolutePath<FileSystem> getAbsoluteFilePath () {
		return this.absolute_path;
	}

	@Override
	public boolean delete () throws IOException {
		final File unprotected_file = this.getUnprotectedFile();
		return unprotected_file.delete();
	}

	@Override
	public boolean isFolder () throws IOException {
		final File unprotected_file = this.getUnprotectedFile();
		return unprotected_file.isFolder();
	}

	@Override
	final public FileHash calculateHash () throws IOException {
		return this.getUnprotectedFile().calculateHash();
	}

	@Override
	public boolean isFile () throws IOException {
		final File unprotected_file = this.getUnprotectedFile();
		return unprotected_file.isFile();
	}

	@Override
	public String toString () {
		return "File [" + this.absolute_path + "]";
	}

	@Override
	public ChildrenList listDirectChildren () throws IOException {
		final File unprotected_file = this.getUnprotectedFile();

		if (!unprotected_file.exists()) {
			throw new Error("File does not exist: " + this.absolute_path);
		}
		if (unprotected_file.isFolder()) {

			final ChildrenList unprotected_children = unprotected_file.listDirectChildren();

			// List<String> files = content.listChildren(relativePath);

			final FilesList listFiles = new FilesList();
			for (int i = 0; i < unprotected_children.size(); i++) {
				final String file_i = unprotected_children.getElementAt(i).getName();
				final AbsolutePath<FileSystem> absolute_file = this.absolute_path.child(file_i);
				final File file = absolute_file.getMountPoint().newFile(absolute_file);
				{
					listFiles.add(file);
				}
			}
			// L.d("listFiles", listFiles);

			//
			return listFiles;
		} else {
			throw new Error("This is not a folder: " + this.absolute_path);
		}
	}

	@Override
	public ChildrenList listAllChildren () {
		Err.reportNotImplementedYet();
		return null;
	}

	@Override
	public File child (final String child_name) {
		return new SandboxFile(this.getFileSystem(), this.getAbsoluteFilePath().child(child_name));
	}

	@Override
	public boolean exists () throws IOException {
		final File unprotected_file = this.getUnprotectedFile();
		return unprotected_file.exists();
	}

	@Override
	public boolean makeFolder () {
		final File unprotected_file = this.getUnprotectedFile();
		return unprotected_file.makeFolder();
	}

	@Override
	public boolean rename (final String new_name) {
		final File unprotected_file = this.getUnprotectedFile();
		return unprotected_file.rename(new_name);
	}

	@Override
	public String getName () {
		if (this.relativePath.isRoot()) {
			return this.sandbox.getName();
		}
		return this.absolute_path.getName();
	}

	@Override
	public RedSandboxFileSystem getFileSystem () {
		return this.sandbox;
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
	public FileInputStream newInputStream () {
// super.newInputStream();
		final File unprotected_file = this.getUnprotectedFile();
		return unprotected_file.newInputStream();
	}

	@Override
	public FileOutputStream newOutputStream () {
		final File unprotected_file = this.getUnprotectedFile();
		return unprotected_file.newOutputStream();
	}

	@Override
	public long getSize () throws IOException {
		if (this.isFile()) {
			final File unprotected_file = this.getUnprotectedFile();
			return unprotected_file.getSize();
		} else {
			return 0;
		}
	}

	@Override
	public java.io.File toJavaFile () {
		final File unprotected_file = this.getUnprotectedFile();
		return unprotected_file.toJavaFile();
	}

	@Override
	public File parent () {
		return new SandboxFile(this.getFileSystem(), this.absolute_path.parent());
	}

	@Override
	public long lastModified () throws IOException {
		final File unprotected_file = this.getUnprotectedFile();
		return unprotected_file.lastModified();
	}

}
