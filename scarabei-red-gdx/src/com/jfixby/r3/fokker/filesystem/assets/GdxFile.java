
package com.jfixby.r3.fokker.filesystem.assets;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FileSystem;
import com.jfixby.scarabei.api.file.FilesList;
import com.jfixby.scarabei.api.java.ByteArray;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.util.path.AbsolutePath;
import com.jfixby.scarabei.api.util.path.RelativePath;
import com.jfixby.scarabei.red.filesystem.AbstractRedFile;
import com.jfixby.scarabei.red.filesystem.RedFilesList;

public class GdxFile extends AbstractRedFile implements File {

	final private AbsolutePath<FileSystem> absolute_path;
	private final GdxFileSystem fs;

	public GdxFile (final AbsolutePath<FileSystem> output_file_path, final GdxFileSystem file_system) {
		this.absolute_path = output_file_path;
		this.fs = file_system;
	}

	@Override
	public AbsolutePath<FileSystem> getAbsoluteFilePath () {
		return this.absolute_path;
	}

	private String getGdxInternalPathString () {
		return this.absolute_path.getRelativePath().toString();
	}

	@Override
	public boolean isFile () {
		final FileHandle file = Gdx.files.internal(this.getGdxInternalPathString());
		if (!file.exists()) {
			return false;
		}
		return !file.isDirectory();
	}

	@Override
	public FilesList listAllChildren () {
		Err.throwNotImplementedYet();
		return null;
	}

	@Override
	public boolean isFolder () {
		final FileHandle file = Gdx.files.internal(this.getGdxInternalPathString());
		if (!file.exists()) {
			return false;
		}
		return file.isDirectory();
	}

	@Override
	public boolean exists () {
		final FileHandle file = Gdx.files.internal(this.getGdxInternalPathString());
		return file.exists();
	}

	@Override
	public boolean rename (final String new_name) {
		final java.io.File f = new java.io.File(this.getGdxInternalPathString());
		final GdxFile new_file = new GdxFile(this.absolute_path.parent().child(new_name), this.fs);
		return f.renameTo(new java.io.File(new_file.getGdxInternalPathString()));
	}

	@Override
	public boolean makeFolder () {
		final java.io.File f = new java.io.File(this.getGdxInternalPathString());
		return f.mkdirs();
	}

	@Override
	public boolean delete () throws IOException {
		final java.io.File f = this.toJavaFile();

		if (this.isFolder()) {
			this.clearFolder();
		}
		if (this.getFileSystem().deleteSwitchIsInSafePosition()) {
			L.e("Delete switch is in safe position, file ignored", f);
			return false;
		} else {
			L.d("delete", f);
		}
		return f.delete();

	}

	public static String toNativePathString (final String string) {
		return string.replaceAll(RelativePath.SEPARATOR, GdxFileSystem.OS_SEPARATOR + GdxFileSystem.OS_SEPARATOR);
	}

	@Override
	public FilesList listDirectChildren () {
		final FileHandle file = Gdx.files.internal(this.getGdxInternalPathString());
		if (!file.exists()) {
			Err.reportError("File does not exist: " + this.absolute_path);
		}
		if (file.isDirectory()) {
			final FileHandle[] list = file.list();

			final List<FileHandle> files = Collections.newList(list);
			final RedFilesList listFiles = new RedFilesList();
			for (int i = 0; i < files.size(); i++) {
				final FileHandle file_i = files.getElementAt(i);

				final AbsolutePath<FileSystem> absolute_file = this.absolute_path.child(file_i.name());
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
	public String getName () {
		final java.io.File f = new java.io.File(this.getGdxInternalPathString());
		return f.getName();
	}

	@Override
	public GdxFileSystem getFileSystem () {
		return this.fs;
	}

	@Override
	public String nameWithoutExtension () {
		final java.io.File file = new java.io.File(this.getGdxInternalPathString());
		final String name = file.getName();
		final int dotIndex = name.lastIndexOf('.');
		if (dotIndex == -1) {
			return name;
		}
		return name.substring(0, dotIndex);
	}

	public String toAbsolutePathString () {
		return this.getGdxInternalPathString();
	}

	@Override
	public void writeBytes (final ByteArray bytes) throws IOException {
		throw new IOException("Read-only file system: " + this.getFileSystem());
	}

	@Override
	public void writeString (final String bytes) throws IOException {
		throw new IOException("Read-only file system: " + this.getFileSystem());
	}

	@Override
	public long getSize () {
		final FileHandle file = Gdx.files.internal(this.getGdxInternalPathString());
		return file.length();
	}

	@Override
	public java.io.File toJavaFile () {
		final FileHandle file = Gdx.files.internal(this.getGdxInternalPathString());
		final java.io.File f = file.file();
		return f;
	}

	@Override
	public File parent () {
		return new GdxFile(this.absolute_path.parent(), this.fs);
	}

	@Override
	public File child (final String child_name) {
		return new GdxFile(this.getAbsoluteFilePath().child(child_name), this.getFileSystem());
	}

	@Override
	public long lastModified () {
		final FileHandle file = Gdx.files.internal(this.getGdxInternalPathString());
		return file.lastModified();
	}

}
