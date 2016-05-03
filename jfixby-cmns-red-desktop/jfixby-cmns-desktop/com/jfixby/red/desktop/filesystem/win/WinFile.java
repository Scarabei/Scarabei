
package com.jfixby.red.desktop.filesystem.win;

import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileSystem;
import com.jfixby.cmns.api.util.path.AbsolutePath;
import com.jfixby.cmns.api.util.path.RelativePath;
import com.jfixby.red.filesystem.AbstractLocalFile;

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
		return mount_point_path_string + relative;
	}

	public static String toNativePathString (final String string) {
		return string.replaceAll(RelativePath.SEPARATOR, WinFileSystem.OS_SEPARATOR + WinFileSystem.OS_SEPARATOR);
	}

}
