
package com.jfixby.scarabei.red.desktop.filesystem.unix;

import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FileSystem;
import com.jfixby.scarabei.api.util.path.AbsolutePath;
import com.jfixby.scarabei.api.util.path.RelativePath;
import com.jfixby.scarabei.red.filesystem.AbstractLocalFile;

public class UnixFile extends AbstractLocalFile<UnixFileSystem> implements File {

	public UnixFile (final AbsolutePath<FileSystem> path, final UnixFileSystem fs) {
		super(path, fs);
	}

	@Override
	public String getAbsolutePathString () {
		final String mount_point_path_string = "";
		String relative = toNativePathString(this.getAbsoluteFilePath().getRelativePath().getPathString());
		if (relative.length() > 0) {
			relative = UnixFileSystem.OS_SEPARATOR + relative;
		}
		final String result = mount_point_path_string + relative;
		if (result.equals("")) {
			return "/";
		}
		return result;
	}

	public static String toNativePathString (final String string) {
		return string.replaceAll(RelativePath.SEPARATOR, UnixFileSystem.OS_SEPARATOR);
	}

}
