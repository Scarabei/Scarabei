
package com.jfixby.cmns.api.file;

import com.jfixby.cmns.api.collections.Collection;

public interface ChildrenList extends Collection<File> {

	File findChild (String short_child_name);

	// File getChild(int i);

	void print ();

	ChildrenList filterFile (FileFilter filter);

	ChildrenList filterByExtension (String extension);

}
