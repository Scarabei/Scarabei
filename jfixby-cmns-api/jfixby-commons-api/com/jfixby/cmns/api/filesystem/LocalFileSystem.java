package com.jfixby.cmns.api.filesystem;

import java.io.IOException;

import com.jfixby.cmns.api.components.ComponentInstaller;
import com.jfixby.cmns.api.path.AbsolutePath;
import com.jfixby.cmns.api.path.ChildrenList;

public class LocalFileSystem {

	static private ComponentInstaller<LocalFileSystemComponent> componentInstaller = new ComponentInstaller<LocalFileSystemComponent>(
			"LocalFileSystem");

	public static final void installComponent(
			LocalFileSystemComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final LocalFileSystemComponent invoke() {
		return componentInstaller.invokeComponent();
	}

	public static final LocalFileSystemComponent component() {
		return componentInstaller.getComponent();
	}

	public static File newFile(AbsolutePath<FileSystem> file_path) {
		return invoke().newFile(file_path);
	}

	public static FileInputStream newFileInputStream(File newFile)
			throws IOException {
		return invoke().newFileInputStream(newFile);
	}

	public static void copyFolderContentsToFolder(File input_folder,
			File output_folder) throws IOException {
		invoke().copyFolderContentsToFolder(input_folder, output_folder);
	}

	public static File ApplicationHome() {
		return invoke().ApplicationHome();
	}

	public static File newFile(java.io.File java_folder) {
		return invoke().newFile(java_folder);
	}

	public static File newFile(String java_path) {
		return invoke().newFile(java_path);
	}

	public static java.io.File toJavaFile(File file) {
		return invoke().toJavaFile(file);
	}

	public static void copyFilesTo(ChildrenList children_list, File to_folder)
			throws IOException {
		invoke().copyFilesTo(children_list, to_folder);
	}

	public static String readFileToString(AbsolutePath<FileSystem> file_path)
			throws IOException {
		return invoke().readFileToString(file_path);
	}

	public static String toAbsolutePathString(AbsolutePath<FileSystem> file) {
		return invoke().toAbsolutePathString(file);
	}

	public static FileOutputStream newFileOutputStream(File file)
			throws IOException {
		return invoke().newFileOutputStream(file);
	}

	public static File WorkspaceFolder() {
		return invoke().WorkspaceFolder();
	}

	public static void writeStringToFile(String string_data,
			AbsolutePath<FileSystem> file_path) throws IOException {
		invoke().writeStringToFile(string_data, file_path);
	}

}
