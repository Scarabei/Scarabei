
package com.jfixby.scarabei.red.filesystem;

import java.io.IOException;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FileConflistResolver;
import com.jfixby.scarabei.api.file.FileConverter;
import com.jfixby.scarabei.api.file.FileInputStream;
import com.jfixby.scarabei.api.file.FileOutputStream;
import com.jfixby.scarabei.api.file.FileSystem;
import com.jfixby.scarabei.api.file.FilesList;
import com.jfixby.scarabei.api.file.FolderConverter;
import com.jfixby.scarabei.api.file.FolderSupportingIndexBuilderParams;
import com.jfixby.scarabei.api.io.InputStream;
import com.jfixby.scarabei.api.java.ByteArray;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.md5.MD5;
import com.jfixby.scarabei.api.md5.MD5String;
import com.jfixby.scarabei.api.util.JUtils;
import com.jfixby.scarabei.api.util.path.AbsolutePath;

public abstract class AbstractFileSystem implements FileSystem {

	private File ROOT;

	@Override
	final public File ROOT () {
		if (this.ROOT == null) {
			this.ROOT = this.newFile(JUtils.newAbsolutePath((FileSystem)this));
		}
		return this.ROOT;
	}

	boolean deleteSwitchIsInSafePosition = !true;

	@Override
	public boolean deleteSwitchIsInSafePosition () {
		return this.deleteSwitchIsInSafePosition;
	}

// abstract public String md5Hex (File file) throws IOException;

	final public MD5String md5Hex (final File file) throws IOException {
		final InputStream input_stream = this.newFileInputStream(file);
		input_stream.open();
		final MD5String checksum = MD5.md5Stream(input_stream);
		input_stream.close();
		return checksum;
	}

	@Override
	final public FileOutputStream newFileOutputStream (final File output_file) {
		return this.newFileOutputStream(output_file, false);
	}

	@Override
	final public void copyFileToFolder (final File file_to_copy, final File to_folder) throws IOException {
		this.copyFileToFolder(file_to_copy, to_folder, FileConflistResolver.OVERWRITE_ALL);
	}

	@Override
	final public void copyFileToFolder (final File file_to_copy, final File to_folder, final FileConflistResolver resollver)
		throws IOException {
		Debug.checkTrue("The file or folder does not exist: " + file_to_copy, file_to_copy.exists());
		if (file_to_copy.isFolder()) {

			final String shortName = file_to_copy.getName();
			final File target_folder = to_folder.child(shortName);

			final boolean continueCopying = true;

			if (continueCopying) {
// L.d("copying folder", file_to_copy);
// L.d(" to", target_folder);
				this.copyFolderContentsToFolder(file_to_copy, target_folder, resollver);

			}

		} else if (file_to_copy.isFile()) {

			final String shortName = file_to_copy.getName();
			final File target_output_file = to_folder.child(shortName);

			final boolean continueCopying = true;
			if (continueCopying) {
// L.d("copying file", file_to_copy.getAbsoluteFilePath());
				this.copyFileToFile(file_to_copy, target_output_file, resollver);
			}

		} else {
			Err.reportError("Weirdo file: " + file_to_copy);
		}

	}

	@Override
	public void copyFolderContentsToFolder (final File input_folder, final File ouput_folder, final FileConflistResolver resolver)
		throws IOException {
		Debug.checkTrue("The folder does not exist: " + input_folder, input_folder.exists());
		Debug.checkTrue("This is not a folder: " + input_folder, input_folder.exists());

		ouput_folder.makeFolder();
		final FilesList children = input_folder.listDirectChildren();
// children.print("children");
		for (int i = 0; i < children.size(); i++) {
			final File file_to_copy = (children.getElementAt(i));
			this.copyFileToFolder(file_to_copy, ouput_folder, resolver);
		}
	}

	@Override
	final public void copyFolderContentsToFolder (final File input_folder, final File ouput_folder) throws IOException {
		this.copyFolderContentsToFolder(input_folder, ouput_folder, FileConflistResolver.OVERWRITE_ALL);
	}

	@Override
	final public void copyFilesTo (final Collection<File> files_list, final File to_folder) throws IOException {
		for (int i = 0; i < files_list.size(); i++) {
			final File file_to_copy = files_list.getElementAt(i);
			this.copyFileToFolder(file_to_copy, to_folder);
		}
	}

	@Override
	final public String readFileToString (final AbsolutePath<FileSystem> file_path) throws IOException {
		final File file = this.newFile(file_path);
		final FileInputStream is = this.newFileInputStream(file);
		final ByteArray data = is.readAll();
		is.close();
		return JUtils.newString(data);
	}

	@Override
	final public void writeDataToFile (final AbsolutePath<FileSystem> file_path, final ByteArray bytes) throws IOException {
		final File file = this.newFile(file_path);
		final FileOutputStream fos = this.newFileOutputStream(file);
		fos.write(bytes);
		fos.flush();
		fos.close();
	}

	@Override
	final public void writeStringToFile (final String string_data, final AbsolutePath<FileSystem> file_path) throws IOException {
		this.writeDataToFile(file_path, JUtils.newByteArray(string_data.getBytes()));
	}

	@Override
	final public void copyFileToFile (final File input_file, final File output_file) throws IOException {
		this.copyFileToFile(input_file, output_file, FileConflistResolver.OVERWRITE_ALL);
	}

	@Override
	final public void copyFileToFile (final File input_file, final File output_file, final FileConflistResolver resollver)
		throws IOException {
		if (!input_file.exists()) {
			throw new IOException("File not found: " + input_file);
		}
		if (input_file.isFolder()) {
			this.copyFolderContentsToFolder(input_file, output_file);
			return;
		}
		if (input_file.isFile()) {
			if (!output_file.exists() || resollver.overwrite(input_file, output_file)) {
				L.d("copying file", input_file);
				L.d("          to", output_file.getAbsoluteFilePath());
				final ByteArray data = input_file.readBytes();
				output_file.writeBytes(data);
			} else {
// L.d(" skip file", input_file);
// L.d(" skip", output_file.getAbsoluteFilePath());
			}
			return;
		}
	}

	@Override
	public boolean isReadOnlyFileSystem () {
		return false;
	}

	@Override
	final public void convertFolderToFolder (final File input_folder, final File ouput_folder,
		final FolderConverter folderConverter, final FileConverter fileConverter) throws IOException {

		Debug.checkTrue("The folder does not exist: " + input_folder, input_folder.exists());
		Debug.checkTrue("This is not a folder: " + input_folder, input_folder.exists());

		ouput_folder.makeFolder();
		final FilesList children = input_folder.listDirectChildren();
		for (int i = 0; i < children.size(); i++) {
			final File file_to_copy = (children.getElementAt(i));
			this.convertFile(file_to_copy, ouput_folder, folderConverter, fileConverter);
		}

	}

	@Override
	final public void convertFile (final File fileToConvert, final File targetFolder, final FolderConverter folderConverter,
		final FileConverter fileConverter) throws IOException {

		Debug.checkTrue("The file or folder does not exist: " + fileToConvert, fileToConvert.exists());

		final String shortName = fileToConvert.getName();
		final File target = targetFolder.child(shortName);

		if (fileToConvert.isFolder()) {
			if (folderConverter != null) {
				final boolean keep_running = folderConverter.convert(fileToConvert, target);
				if (keep_running) {
					this.convertFolderToFolder(fileToConvert, target, folderConverter, fileConverter);
				}
			}
		} else if (fileToConvert.isFile()) {
			if (fileConverter != null) {
				fileConverter.convert(fileToConvert, target);
			}
		} else {
			Err.reportError("Weirdo file: " + fileToConvert);
		}

	}

	final FileConverter COPY_FILE = new FileConverter() {

		@Override
		public boolean convert (final File inputFile, final File outputFile) throws IOException {
			AbstractFileSystem.this.copyFileToFile(inputFile, outputFile);
			return true;
		}
	};
	final FolderConverter COPY_FOLDER = new FolderConverter() {

		@Override
		public boolean convert (final File inputFolder, final File outputfolder) throws IOException {
			final FilesList children = inputFolder.listDirectChildren();
			outputfolder.makeFolder();
			AbstractFileSystem.this.copyFilesTo(children, outputfolder);
			return true;
		}
	};

	@Override
	public void rebuildFolderSupportingIndexes (final FolderSupportingIndexBuilderParams params) throws IOException {
		RedFolderSupportingIndexBuilder.rebuild(params);
	}

	@Override
	public FolderSupportingIndexBuilderParams newFolderSupportingIndexBuilderParams () {
		return new RedFolderSupportingIndexBuilderParams();
	}

}
