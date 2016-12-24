
package com.jfixby.scarabei.red.desktop.filesystem.win;

import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FileSystem;
import com.jfixby.scarabei.api.util.path.AbsolutePath;
import com.jfixby.scarabei.api.util.path.RelativePath;
import com.jfixby.scarabei.red.filesystem.AbstractLocalFile;

public class WinFile extends AbstractLocalFile<WinFileSystem> implements File {

	public WinFile (final AbsolutePath<FileSystem> absolute_path, final WinFileSystem windowsFileSystem) {
		super(absolute_path, windowsFileSystem);
	}

	@Override
	public String getAbsolutePathString () {
		final String mount_point_path_string = "";
		String relative = toNativePathString(this.getAbsoluteFilePath().getRelativePath().getPathString());
		if (relative.length() > 0) {
			relative = WinFileSystem.OS_SEPARATOR + relative;
		}
		final String result = mount_point_path_string + relative;
		if (result.equals("")) {
			return "/";
		}
		return result;
	}

	public static String toNativePathString (final String string) {
		return string.replaceAll(RelativePath.SEPARATOR, WinFileSystem.OS_SEPARATOR + WinFileSystem.OS_SEPARATOR);
	}

}
