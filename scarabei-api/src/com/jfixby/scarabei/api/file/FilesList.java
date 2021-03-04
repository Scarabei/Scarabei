
package com.jfixby.scarabei.api.file;

import java.io.IOException;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.util.path.RelativePath;

public interface FilesList extends Collection<File> {

	File findChild (String short_child_name);

	FilesList filter (FileFilter filter);

	FilesList filterByExtension (String extension);

	void deleteAll () throws IOException;

	Map<RelativePath, File> toMap (File relativeTo);
}
