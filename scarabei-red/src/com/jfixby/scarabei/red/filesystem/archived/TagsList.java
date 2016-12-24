
package com.jfixby.scarabei.red.filesystem.archived;

import java.util.ArrayList;

public class TagsList {
	public final ArrayList<FileTag> tags = new ArrayList<FileTag>();

	public void addInfo (FileTag info) {
		tags.add(info);
		// L.d("packing", info);
	}

}
