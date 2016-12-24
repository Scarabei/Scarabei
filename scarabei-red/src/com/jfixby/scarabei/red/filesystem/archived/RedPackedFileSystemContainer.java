
package com.jfixby.scarabei.red.filesystem.archived;

import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.packing.CompressionSchema;

public class RedPackedFileSystemContainer {
	private File data_file;

	public RedPackedFileSystemContainer (CompressionSchema schema, File file) {
		this.data_file = file;
		schema.print("CompressionSchema");
	}

}
