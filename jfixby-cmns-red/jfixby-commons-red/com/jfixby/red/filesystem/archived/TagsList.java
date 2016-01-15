package com.jfixby.red.filesystem.archived;

import java.util.Vector;

public class TagsList {
	public final Vector<FileTag> tags = new Vector<FileTag>();

	public void addInfo(FileTag info) {
		tags.addElement(info);
		// L.d("packing", info);
	}

}
