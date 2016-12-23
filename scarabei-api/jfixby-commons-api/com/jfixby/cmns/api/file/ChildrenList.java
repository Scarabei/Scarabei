
package com.jfixby.cmns.api.file;

import java.io.IOException;

import com.jfixby.cmns.api.collections.Collection;

public interface ChildrenList extends Collection<File> {

	File findChild (String short_child_name);

	ChildrenList filterFiles (FileFilter filter);

	ChildrenList filterByExtension (String extension);

	void deleteAll () throws IOException;
}
