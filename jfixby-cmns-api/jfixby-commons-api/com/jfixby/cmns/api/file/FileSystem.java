
package com.jfixby.cmns.api.file;

import java.io.IOException;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.util.path.AbsolutePath;
import com.jfixby.cmns.api.util.path.MountPoint;

public interface FileSystem extends MountPoint {

	// FileSystemMountPoint FileSystemHome();

	File ROOT ();

	File newFile (AbsolutePath<FileSystem> file_path);

	FileOutputStream newFileOutputStream (File output_file, boolean append);

	FileOutputStream newFileOutputStream (File output_file);

	FileInputStream newFileInputStream (File input_file);

	String nativeSeparator ();

	void copyFileToFolder (File file_to_copy, File to_folder) throws IOException;

	void copyFolderContentsToFolder (File forlder_from, File folder_to) throws IOException;

	void copyFilesTo (Collection<File> files_list, File to_folder) throws IOException;

	String readFileToString (AbsolutePath<FileSystem> file_path) throws IOException;

	void writeDataToFile (AbsolutePath<FileSystem> file_path, ByteArray bytes) throws IOException;

	void writeStringToFile (String string_data, AbsolutePath<FileSystem> file_path) throws IOException;

	void copyFileToFile (File input_file, File output_file) throws IOException;

	boolean isReadOnlyFileSystem ();

	void convertFolderToFolder (File input_folder, File ouput_folder, FolderConverter folderConverter, FileConverter fileConverter)
		throws IOException;

	void convertFile (File fileToConvert, File targetFolder, FolderConverter folderConverter, FileConverter fileConverter)
		throws IOException;

}
