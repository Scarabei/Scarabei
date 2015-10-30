package com.jfixby.cmns.api.path;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.filesystem.File;

public interface ChildrenList extends Collection<File> {

	File findChild(String short_child_name);

	// File getChild(int i);

	void print();

	ChildrenList filter(FileFilter filter);

}
