
package com.jfixby.r3.fokker.filesystem.assets.fs;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FileSystem;
import com.jfixby.scarabei.api.file.FilesList;
import com.jfixby.scarabei.api.java.ByteArray;
import com.jfixby.scarabei.api.util.path.AbsolutePath;
import com.jfixby.scarabei.api.util.path.RelativePath;
import com.jfixby.scarabei.red.filesystem.AbstractRedFile;
import com.jfixby.scarabei.red.filesystem.RedFilesList;

public class GdxAssetFile extends AbstractRedFile implements File {

	final private AbsolutePath<FileSystem> absolute_path;
	private final GdxAssetsFileSystem fs;
	private final RelativePath relative;
	private FileHandle gdx_file;

	@Override
	public FilesList listAllChildren () {
		Err.throwNotImplementedYet();
		return null;
	}

	public GdxAssetFile (final AbsolutePath<FileSystem> output_file_path, final GdxAssetsFileSystem file_system) {
		this.absolute_path = output_file_path;
		this.fs = file_system;
		this.relative = this.absolute_path.getRelativePath();
		if (this.fs.index_is_loaded) {
			final Boolean isFile = this.fs.index.get(this.relative);
			this.gdx_file = Gdx.files.internal(this.getGdxInternalPathString());

			if (isFile == null) {
				if (this.gdx_file.exists()) {
					this.throwOutdated(output_file_path);
				}
			} else {
				if (isFile == true) {
					if (!this.gdx_file.exists()) {
						this.throwOutdated(output_file_path);
					}
				}
			}

			// -------------------------------------

			// if (gdx_file == null) {
			// Err.reportError(this.getGdxInternalPathString() + "");
			// }
			// if (gdx_file.exists() && isFile == null) {
			// Err.reportError("Index is outdated: " + gdx_file);
			// }
			// if (!gdx_file.exists() && isFile == true) {
			// Err.reportError("Index is outdated: " + gdx_file);
			// }
			// if (gdx_file.exists() && isFile != null) {
			// if (gdx_file.isDirectory() && isFile == false) {
			// Err.reportError("Index is outdated: " + gdx_file);
			// }
			// }
		}
	}

	private void throwOutdated (final AbsolutePath<FileSystem> output_file_path) {
		this.fs.index.print("index");
		Err.reportError("Index is outdated: " + output_file_path);
	}

	@Override
	public AbsolutePath<FileSystem> getAbsoluteFilePath () {
		return this.absolute_path;
	}

	String getGdxInternalPathString () {
		return GdxAssetsFileSystem.internalFileName(this.absolute_path.getRelativePath());
	}

	@Override
	public boolean isFile () {
		final Boolean isFile = this.fs.index.get(this.relative);
		if (isFile == null) {
			return false;
		}
		return isFile;
	}

	@Override
	public boolean isFolder () {
		final Boolean isFile = this.fs.index.get(this.relative);
		if (isFile == null) {
			return false;
		}
		return !isFile;
	}

	@Override
	public boolean exists () {
		final Boolean isFile = this.fs.index.get(this.relative);
		if (isFile == null) {
			return false;
		}
		// return isFile != null;
		return isFile || true;
	}

	@Override
	public boolean rename (final String new_name) {
		Err.reportError("Read-only file system!");
		return false;
	}

	@Override
	public boolean makeFolder () {
		// java.io.File f = new java.io.File(this.getGdxInternalPathString());
		// return f.mkdirs();
		Err.reportError("Read-only file system!");
		return false;
	}

	@Override
	public boolean delete () {
		// if (this.isFolder()) {
		// this.clearFolder();
		// }
		// java.io.File f = new java.io.File(getGdxInternalPathString());
		//
		// L.d("deleting", f);
		// f.delete();
		Err.reportError("Read-only file system!");
		return false;
	}

	public static String toNativePathString (final String string) {
		return string.replaceAll(RelativePath.SEPARATOR, GdxAssetsFileSystem.OS_SEPARATOR + GdxAssetsFileSystem.OS_SEPARATOR);
	}

	@Override
	public FilesList listDirectChildren () {
		final FileHandle file = Gdx.files.internal(this.getGdxInternalPathString());
		if (!this.exists()) {
			Err.reportError("File does not exist: " + this.absolute_path);
		}
		if (this.isFolder()) {
			final Collection<String> my_steps = this.relative.steps();
			final RedFilesList listFiles = new RedFilesList();
			for (int i = 0; i < this.fs.index.size(); i++) {
				final RelativePath path = this.fs.index.getKeyAt(i);
				final Collection<String> candidate_steps = path.steps();
				if (my_steps.size() + 1 == candidate_steps.size()) {
					if (path.parent().equals(this.relative)) {
						final AbsolutePath<FileSystem> absolute_file = this.absolute_path.child(candidate_steps.getLast());
						final File addFile = absolute_file.getMountPoint().newFile(absolute_file);
						{
							listFiles.add(addFile);
						}
					}
				}
			}
			return listFiles;
		} else {
			Err.reportError("This is not a folder: " + this.absolute_path);
		}
		return null;
	}

// @Override
// public String toString () {
// return "@GdxAssetsFileSystem:>" + RelativePath.SEPARATOR + this.absolute_path.getRelativePath();
// }

	@Override
	public File child (final String child_name) {
		return new GdxAssetFile(this.getAbsoluteFilePath().child(child_name), this.getFileSystem());
	}

	@Override
	public String getName () {
		// java.io.File f = new java.io.File(this.getGdxInternalPathString());
		return this.absolute_path.getRelativePath().getLastStep();
	}

	@Override
	public GdxAssetsFileSystem getFileSystem () {
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
		// FileHandle file =
		// Gdx.files.internal(this.getGdxInternalPathString());
		// java.io.File f = file.file();
		// return f;
		Err.reportError("Method is not supported: toJavaFile()");
		return null;
	}

	@Override
	public File parent () {
		return new GdxAssetFile(this.absolute_path.parent(), this.fs);
	}

	@Override
	public long lastModified () {
		return this.gdx_file.lastModified();
	}

	public FileHandle toFileHandle () {
		return this.gdx_file;
	}

}
