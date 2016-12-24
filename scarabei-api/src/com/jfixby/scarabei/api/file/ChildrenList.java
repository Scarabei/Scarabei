
package com.jfixby.scarabei.api.file;

import java.io.IOException;

import com.jfixby.scarabei.api.collections.Collection;

public interface ChildrenList extends Collection<File> {

	File findChild (String short_child_name);

	ChildrenList filterFiles (FileFilter filter);

	ChildrenList filterByExtension (String extension);

	void deleteAll () throws IOException;
}
