
package com.jfixby.scarabei.red.filesystem;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FileSystem;
import com.jfixby.scarabei.api.file.LocalFile;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.util.path.AbsolutePath;
import com.jfixby.scarabei.api.util.path.RelativePath;

public final class LocalRedFile extends AbstractRedFile implements LocalFile {

	AbsolutePath<FileSystem> absolute_path;
	private final LocalFileSystem fs;

	final public String getAbsolutePathString (final File file) {

// final Path path = new Path(file.toJavaFile());

// final String mount_point_path_string = "";
// String relative = toNativePathString(this.getAbsoluteFilePath().getRelativePath().getPathString());
// if (relative.length() > 0) {
// relative = UnixFileSystem.OS_SEPARATOR + relative;
// }
// final String result = mount_point_path_string + relative;
// if (result.equals("")) {
// return "/";
// }
// return result;
//
// Err.throwNotImplementedYet();
		return null;
	}

	@Override
	final public java.io.File toJavaFile () {

		final RelativePath relative = this.getAbsoluteFilePath().getRelativePath();
// L.d("relative", relative.toString());
// final Collection<String> steps = relative.steps();
// final String unixPath = JUtils.wrapSequence(steps, steps.size(), "", "", "/");
// L.d("unixPath", unixPath);

// final java.io.File root = path.toFile().getAbsoluteFile();
// L.d("root", root);
//
// L.d("file", this);
// L.d("path", path);
//
// path = path.resolve("");
///
		Path path = Paths.get("root").toAbsolutePath().getRoot().toAbsolutePath();
		for (final String step : relative.steps()) {
			path = path.resolve(step);
// L.d("+" + step, path);
		}
// L.d("root",);
// L.d("path", path.toFile().getAbsolutePath());
// Sys.exit();
		final java.io.File f = path.toFile().getAbsoluteFile();

// L.d("path", path);
// L.d("f", f);
// L.d();
//
// Debug.printCallStack();
// Sys.exit();
		return f;
	}

	public LocalRedFile (final AbsolutePath<FileSystem> absolute_path, final LocalFileSystem fileSystem) {
		this.absolute_path = absolute_path;
		this.fs = fileSystem;

	}

	@Override
	final public LocalFileSystem getFileSystem () {
		return this.fs;
	}

	@Override
	final public AbsolutePath<FileSystem> getAbsoluteFilePath () {
		return this.absolute_path;
	}

	@Override
	final public boolean rename (final String new_name) {
		final java.io.File f = this.toJavaFile();
		final AbsolutePath<FileSystem> newPath = this.getAbsoluteFilePath().parent().child(new_name);
		final File newFile = this.getFileSystem().newFile(newPath);
		final boolean success = f.renameTo(newFile.toJavaFile());
		if (success) {
			this.absolute_path = newPath;
		}
		return success;
	}

	@Override
	final public boolean isFile () {
		final java.io.File f = this.toJavaFile();
		return f.isFile();
	}

	@Override
	final public long lastModified () {
		final java.io.File f = this.toJavaFile();
		return f.lastModified();
	}

	@Override
	final public File child (final String child_name) {
		return this.getFileSystem().newFile(this.getAbsoluteFilePath().child(child_name));

	}

	@Override
	final public String nameWithoutExtension () {
		final java.io.File file = this.toJavaFile();
		final String name = file.getName();
		final int dotIndex = name.lastIndexOf('.');
		if (dotIndex == -1) {
			return name;
		}
		return name.substring(0, dotIndex);
	}

	@Override
	final public File parent () {
		if (!this.getAbsoluteFilePath().isRoot()) {
			return this.getFileSystem().newFile(this.getAbsoluteFilePath().parent());
		}
		Err.reportError("This is already a root file. No parent available: " + this);
		return null;
	}

	@Override
	final public boolean makeFolder () {
		final java.io.File f = this.toJavaFile();
		return f.mkdirs();
	}

	@Override
	final public boolean delete () throws IOException {
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

	@Override
	final public boolean isFolder () {
		final java.io.File f = this.toJavaFile();
		return f.isDirectory();
	}

	@Override
	final public boolean exists () {
		final java.io.File f = this.toJavaFile();
		return f.exists();
	}

	static final boolean DIRECT_CHILDREN = true;
	static final boolean ALL_CHILDREN = !DIRECT_CHILDREN;

	@Override
	final public RedFilesList listDirectChildren () {
		final List<LocalRedFile> filesQueue = Collections.newList();
		filesQueue.add(this);
		final RedFilesList result = new RedFilesList();
		collectChildren(filesQueue, result, DIRECT_CHILDREN);

		return result;

	}

	@Override
	final public RedFilesList listAllChildren () {
		final List<LocalRedFile> filesQueue = Collections.newList();
		filesQueue.add(this);
		final RedFilesList result = new RedFilesList();
		collectChildren(filesQueue, result, ALL_CHILDREN);

		return result;

	}

	static private <T extends LocalFileSystem> void collectChildren (final List<LocalRedFile> filesQueue,
		final RedFilesList result, final boolean directFlag) {
		while (filesQueue.size() > 0) {
			final LocalRedFile nextfile = filesQueue.removeElementAt(0);
// L.d("absPathString", absPathString);
			final java.io.File file = nextfile.toJavaFile();
			if (!file.exists()) {
				Err.reportError("File does not exist: " + file);
			}

			if (file.isDirectory()) {
// L.d("file", file.getAbsolutePath());
// Sys.exit();

				final String[] list = file.list();
				if (list == null) {
					L.e("list() is null", file);
					continue;
				}
				final List<String> files = Collections.newList(list);

				for (int i = 0; i < files.size(); i++) {
					final String file_i = files.getElementAt(i);
					final AbsolutePath<FileSystem> absolute_file = nextfile.absolute_path.child(file_i);
					final T fs = (T)absolute_file.getMountPoint();
					final LocalRedFile child = fs.newFile(absolute_file);
					result.add(child);
					if (directFlag == ALL_CHILDREN) {

						if (child.isFolder()) {
							filesQueue.add(child);
						}
					} else {

					}
				}

			} else {
				Err.reportError("This is not a folder: " + nextfile.absolute_path);
			}

		}
	}

// final public String getAbsolutePathString () {
// return this.fs.getAbsolutePathString(this);
// }

// final public String toAbsolutePathString () {
// return this.getAbsolutePathString();
// }

// final public java.io.File getJavaFile () {
// final java.io.File file = new java.io.File(this.getAbsolutePathString());
// return file;
// }

	@Override
	final public long getSize () {
		final java.io.File file = this.toJavaFile();
		if (file.isFile()) {
			return file.length();
		} else {
			return 0;
		}
	}

	@Override
	final public String getName () {
		final java.io.File f = this.toJavaFile();
		return f.getName();
	}

}
