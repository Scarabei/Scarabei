
package com.jfixby.red.filesystem;

import java.io.IOException;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.file.ChildrenList;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.cmns.api.file.FileSystem;
import com.jfixby.cmns.api.io.InputStream;
import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.md5.MD5;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.api.util.path.AbsolutePath;

public abstract class AbstractFileSystem implements FileSystem {

	private File ROOT;

	@Override
	public File ROOT () {
		if (this.ROOT == null) {
			this.ROOT = this.newFile(JUtils.newAbsolutePath((FileSystem)this));
		}
		return this.ROOT;
	}

// abstract public String md5Hex (File file) throws IOException;

	final public String md5Hex (final File file) throws IOException {
		final InputStream input_stream = this.newFileInputStream(file);
		input_stream.open();
		final String checksum = MD5.md5Stream(input_stream);
		input_stream.close();
		return checksum.toUpperCase();
	}

	@Override
	public void copyFileToFolder (final File file_to_copy, final File to_folder) throws IOException {
		if (!file_to_copy.exists()) {
			throw new Error("The file or folder does not exist: " + file_to_copy);
		}
		final FileSystem target_file_system = to_folder.getFileSystem();
		// FileSystem input_file_system = file_to_copy.getFileSystem();
		if (file_to_copy.isFolder()) {
			L.d("copying folder", file_to_copy);
			final String shortName = file_to_copy.getName();
			final File target_folder = target_file_system.newFile(to_folder.child(shortName).getAbsoluteFilePath());
			L.d("            to", target_folder);

			target_folder.makeFolder();
			final Collection<File> from_folder_content_list = file_to_copy.listChildren();
			for (int i = 0; i < from_folder_content_list.size(); i++) {
				final File child_file_to_copy = from_folder_content_list.getElementAt(i);
				this.copyFileToFolder(child_file_to_copy, target_folder);
			}
			return;
		}
		if (file_to_copy.isFile()) {
			L.d("copying file", file_to_copy.getAbsoluteFilePath());
			final String shortName = file_to_copy.getName();
			final File target_output_file = target_file_system.newFile(to_folder.child(shortName).getAbsoluteFilePath());
			this.copyFileToFile(file_to_copy, target_output_file);

		}

	}

	@Override
	public void copyFolderContentsToFolder (final File forlder_from, final File folder_to) throws IOException {
		if (!forlder_from.exists()) {
			throw new Error("The folder does not exist: " + forlder_from);
		}
		if (forlder_from.isFile()) {
			throw new Error("This is not a folder: " + forlder_from);
		}

		folder_to.makeFolder();
		final ChildrenList children = forlder_from.listChildren();
		for (int i = 0; i < children.size(); i++) {
			final File file_to_copy = (children.getElementAt(i));
			this.copyFileToFolder(file_to_copy, folder_to);
		}
	}

	@Override
	public void copyFilesTo (final Collection<File> files_list, final File to_folder) throws IOException {
		for (int i = 0; i < files_list.size(); i++) {
			final File file_to_copy = files_list.getElementAt(i);
			this.copyFileToFolder(file_to_copy, to_folder);
		}
	}

	@Override
	public String readFileToString (final AbsolutePath<FileSystem> file_path) throws IOException {
		final File file = this.newFile(file_path);
		final FileInputStream is = this.newFileInputStream(file);
		final ByteArray data = is.readAll();
		is.close();
		return JUtils.newString(data);
	}

	@Override
	public void writeDataToFile (final AbsolutePath<FileSystem> file_path, final ByteArray bytes) throws IOException {
		final File file = this.newFile(file_path);
		final FileOutputStream fos = this.newFileOutputStream(file);
		fos.write(bytes);
		fos.flush();
		fos.close();
	}

	@Override
	public void writeStringToFile (final String string_data, final AbsolutePath<FileSystem> file_path) throws IOException {
		this.writeDataToFile(file_path, JUtils.newByteArray(string_data.getBytes()));
	}

	@Override
	public void copyFileToFile (final File input_file, final File output_file) throws IOException {
		if (!input_file.exists()) {
			throw new IOException("File not found: " + input_file);
		}
		if (input_file.isFolder()) {
			this.copyFolderContentsToFolder(input_file, output_file);
			return;
		}
		if (input_file.isFile()) {
			L.d("copying file", input_file);
			L.d("          to", output_file.getAbsoluteFilePath());
			// DebugTimer timer = Debug.newTimer();
			// timer.reset();
			final ByteArray data = input_file.readBytes();
			// timer.printTimeAbove(30, "readBytes");
			// timer.reset();
			output_file.writeBytes(data);
			// timer.printTimeAbove(30, "writeBytes");

			return;
		}
	}

	@Override
	public boolean isReadOnlyFileSystem () {
		return false;
	}

}
