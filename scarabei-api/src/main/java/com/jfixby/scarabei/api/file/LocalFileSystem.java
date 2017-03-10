
package com.jfixby.scarabei.api.file;

import java.io.IOException;

import com.jfixby.scarabei.api.ComponentInstaller;
import com.jfixby.scarabei.api.util.path.AbsolutePath;

public class LocalFileSystem {

	static private ComponentInstaller<LocalFileSystemComponent> componentInstaller = new ComponentInstaller<LocalFileSystemComponent>(
		"LocalFileSystem");

	public static void deInstallCurrentComponent () {
		componentInstaller.deInstallCurrentComponent();
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static final void installComponent (final LocalFileSystemComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final LocalFileSystemComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final LocalFileSystemComponent component () {
		return componentInstaller.getComponent();
	}

	public static File newFile (final AbsolutePath<FileSystem> file_path) {
		return invoke().newFile(file_path);
	}

	public static FileInputStream newFileInputStream (final File newFile) throws IOException {
		return invoke().newFileInputStream(newFile);
	}

	public static void copyFolderContentsToFolder (final File input_folder, final File output_folder) throws IOException {
		invoke().copyFolderContentsToFolder(input_folder, output_folder);
	}

	public static File ApplicationHome () {
		return invoke().ApplicationHome();
	}

	public static File newFile (final java.io.File java_folder) {
		return invoke().newFile(java_folder);
	}

	public static File newFile (final String java_path) {
		return invoke().newFile(java_path);
	}

	public static void copyFilesTo (final FilesList children_list, final File to_folder) throws IOException {
		invoke().copyFilesTo(children_list, to_folder);
	}

	public static String readFileToString (final AbsolutePath<FileSystem> file_path) throws IOException {
		return invoke().readFileToString(file_path);
	}

	public static FileOutputStream newFileOutputStream (final File file) throws IOException {
		return invoke().newFileOutputStream(file);
	}

	public static void writeStringToFile (final String string_data, final AbsolutePath<FileSystem> file_path) throws IOException {
		invoke().writeStringToFile(string_data, file_path);
	}

	public static void copyFileToFolder (final File file, final File to_folder) throws IOException {
		invoke().copyFileToFolder(file, to_folder);
	}

	public static File ROOT () {
		return invoke().ROOT();
	}

	public static void copyFileToFile (final File inputFile, final File outputFile) throws IOException {
		invoke().copyFileToFile(inputFile, outputFile);
	}

}
