
package com.jfixby.cmns.api.file.packing;

import java.io.IOException;

import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.util.path.RelativePath;

public interface CompressionSchema {

	void print (String tag);

	boolean isFile (RelativePath relativePath);

	long lastModified (RelativePath relativePath);

	boolean isFolder (RelativePath relativePath);

	Iterable<String> listChildren (RelativePath relativePath);

	FileData readFileData (RelativePath relativePath, File archive) throws IOException;

}
