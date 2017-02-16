
package com.jfixby.scarabei.api.file;

import java.io.IOException;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.java.ByteArray;
import com.jfixby.scarabei.api.util.path.AbsolutePath;
import com.jfixby.scarabei.api.util.path.MountPoint;

public interface FileSystem extends MountPoint {

	// FileSystemMountPoint FileSystemHome();

	File ROOT ();

	File newFile (AbsolutePath<FileSystem> file_path);

	FileOutputStream newFileOutputStream (File output_file, boolean append);

	FileOutputStream newFileOutputStream (File output_file);

	FileInputStream newFileInputStream (File input_file);

	void copyFileToFolder (File file_to_copy, File to_folder) throws IOException;

	void copyFileToFolder (File file_to_copy, File to_folder, FileConflistResolver resollver) throws IOException;

	void copyFolderContentsToFolder (File forlder_from, File folder_to) throws IOException;

	void copyFolderContentsToFolder (File forlder_from, File folder_to, FileConflistResolver resollver) throws IOException;

	void copyFileToFile (File input_file, File output_file) throws IOException;

	void copyFileToFile (File input_file, File output_file, FileConflistResolver resollver) throws IOException;

	void copyFilesTo (Collection<File> files_list, File to_folder) throws IOException;

	String readFileToString (AbsolutePath<FileSystem> file_path) throws IOException;

	void writeDataToFile (AbsolutePath<FileSystem> file_path, ByteArray bytes) throws IOException;

	void writeStringToFile (String string_data, AbsolutePath<FileSystem> file_path) throws IOException;

	boolean isReadOnlyFileSystem ();

	void convertFolderToFolder (File input_folder, File ouput_folder, FolderConverter folderConverter, FileConverter fileConverter)
		throws IOException;

	void convertFile (File fileToConvert, File targetFolder, FolderConverter folderConverter, FileConverter fileConverter)
		throws IOException;

	void rebuildFolderSupportingIndexes (FolderSupportingIndexBuilderParams params) throws IOException;

	FolderSupportingIndexBuilderParams newFolderSupportingIndexBuilderParams ();

	boolean deleteSwitchIsInSafePosition ();

}
