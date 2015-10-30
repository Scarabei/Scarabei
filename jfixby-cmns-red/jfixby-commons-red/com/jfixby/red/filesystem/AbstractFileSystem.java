package com.jfixby.red.filesystem;

import java.io.IOException;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.filesystem.File;
import com.jfixby.cmns.api.filesystem.FileInputStream;
import com.jfixby.cmns.api.filesystem.FileOutputStream;
import com.jfixby.cmns.api.filesystem.FileSystem;
import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.io.StreamPipe;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.path.AbsolutePath;
import com.jfixby.cmns.api.path.ChildrenList;

public abstract class AbstractFileSystem implements FileSystem {

	private File ROOT;

	@Override
	public File ROOT() {
		if (ROOT == null) {
			ROOT = this.newFile(JUtils.newAbsolutePath((FileSystem) this));
		}
		return ROOT;
	}

	abstract public String md5Hex(File file) throws IOException;

	@Override
	public void copyFileToFolder(File file_to_copy, File to_folder)
			throws IOException {
		if (!file_to_copy.exists()) {
			throw new Error("The file or folder does not exist: "
					+ file_to_copy);
		}
		FileSystem target_file_system = to_folder.getFileSystem();
//		FileSystem input_file_system = file_to_copy.getFileSystem();
		if (file_to_copy.isFolder()) {
			L.d("copying folder", file_to_copy);
			String shortName = file_to_copy.getName();
			File target_folder = target_file_system.newFile(to_folder.child(
					shortName).getAbsoluteFilePath());
			L.d("            to", target_folder);

			target_folder.makeFolder();
			Collection<File> from_folder_content_list = file_to_copy
					.listChildren();
			for (int i = 0; i < from_folder_content_list.size(); i++) {
				File child_file_to_copy = from_folder_content_list
						.getElementAt(i);
				copyFileToFolder(child_file_to_copy, target_folder);
			}
			return;
		}
		if (file_to_copy.isFile()) {
			L.d("copying file", file_to_copy.getAbsoluteFilePath());
			String shortName = file_to_copy.getName();
			File target_output_file = target_file_system.newFile(to_folder
					.child(shortName).getAbsoluteFilePath());
			this.copyFileToFile(file_to_copy, target_output_file);

		}

	}

	@Override
	public void copyFolderContentsToFolder(File forlder_from, File folder_to)
			throws IOException {
		if (!forlder_from.exists()) {
			throw new Error("The folder does not exist: " + forlder_from);
		}
		if (forlder_from.isFile()) {
			throw new Error("This is not a folder: " + forlder_from);
		}

		folder_to.makeFolder();
		ChildrenList children = forlder_from.listChildren();
		for (int i = 0; i < children.size(); i++) {
			File file_to_copy = (children.getElementAt(i));
			this.copyFileToFolder(file_to_copy, folder_to);
		}
	}

	@Override
	public void copyFilesTo(Collection<File> files_list, File to_folder)
			throws IOException {
		for (int i = 0; i < files_list.size(); i++) {
			File file_to_copy = files_list.getElementAt(i);
			this.copyFileToFolder(file_to_copy, to_folder);
		}
	}

	@Override
	public String readFileToString(AbsolutePath<FileSystem> file_path)
			throws IOException {
		File file = this.newFile(file_path);
		FileInputStream is = this.newFileInputStream(file);
		byte[] data = is.readAll();
		is.close();
		return new String(data);
	}

	@Override
	public void writeDataToFile(AbsolutePath<FileSystem> file_path, byte[] bytes)
			throws IOException {
		File file = this.newFile(file_path);
		FileOutputStream fos = this.newFileOutputStream(file);
		fos.write(bytes);
		fos.flush();
		fos.close();
	}

	@Override
	public void writeStringToFile(String string_data,
			AbsolutePath<FileSystem> file_path) throws IOException {
		writeDataToFile(file_path, string_data.getBytes());
	}

	@Override
	public void copyFileToFile(File input_file, File output_file)
			throws IOException {
		if (!input_file.exists()) {
			throw new IOException("File not found: " + input_file);
		}
		if (input_file.isFolder()) {
			this.copyFolderContentsToFolder(input_file, output_file);
			return;
		}
		if (input_file.isFile()) {
			FileSystem input_file_system = input_file.getFileSystem();
			FileSystem output_file_system = output_file.getFileSystem();

			FileInputStream input_stream = input_file_system
					.newFileInputStream(input_file);
			FileOutputStream output_stream = output_file_system
					.newFileOutputStream(output_file);

			StreamPipe pipe = IO.newStreamPipe(input_stream, output_stream,
					null);
			L.d("          to", output_file.getAbsoluteFilePath());

			pipe.transferAll();
			return;
		}
	}

	@Override
	public boolean isReadOnlyFileSystem() {
		return false;
	}

}
