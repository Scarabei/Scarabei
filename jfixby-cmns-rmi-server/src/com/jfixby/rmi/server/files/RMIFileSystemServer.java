package com.jfixby.rmi.server.files;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.cmns.api.file.FileSystem;
import com.jfixby.cmns.api.util.path.AbsolutePath;
import com.jfixby.rmi.api.files.RemoteFileSystem;

public class RMIFileSystemServer extends UnicastRemoteObject implements RemoteFileSystem {

	private int port;
	private String box_name;
	private File rootFolder;

	public RMIFileSystemServer(RMIFileSystemServerConfig config) throws RemoteException {
		box_name = config.getMailBoxName();
		port = config.getPort();
		rootFolder = config.getServerRootFolder();
	}

	@Override
	public File ROOT() {
		return rootFolder;
	}

	@Override
	public File newFile(AbsolutePath<FileSystem> file_path) {
		return new RemoteFile(this, file_path);
	}

	@Override
	public FileOutputStream newFileOutputStream(File output_file) throws IOException {
		return output_file.newOutputStream();
	}

	@Override
	public FileInputStream newFileInputStream(File input_file) throws IOException {
		return input_file.newInputStream();
	}

	@Override
	public String nativeSeparator() {
		return OS_SEPARATOR;
	}

	public static final String OS_SEPARATOR = "/";

	@Override
	public void copyFileToFolder(File file_to_copy, File to_folder) throws IOException {
	}

	@Override
	public void copyFolderContentsToFolder(File forlder_from, File folder_to) throws IOException {
	}

	@Override
	public void copyFilesTo(Collection<File> files_list, File to_folder) throws IOException {
	}

	@Override
	public String readFileToString(AbsolutePath<FileSystem> file_path) throws IOException {
		return null;
	}

	@Override
	public void writeDataToFile(AbsolutePath<FileSystem> file_path, byte[] bytes) throws IOException {
	}

	@Override
	public void writeStringToFile(String string_data, AbsolutePath<FileSystem> file_path) throws IOException {
	}

	@Override
	public void copyFileToFile(File input_file, File output_file) throws IOException {
	}

	@Override
	public boolean isReadOnlyFileSystem() {
		return false;
	}

}
