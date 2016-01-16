package com.jfixby.red.filesystem.archived;

import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.packing.CompressionSchema;

public class RedPackedFileSystemContainer {
	private File data_file;

	public RedPackedFileSystemContainer(CompressionSchema schema, File file) {
		this.data_file = file;
		schema.print("CompressionSchema");
	}

}
