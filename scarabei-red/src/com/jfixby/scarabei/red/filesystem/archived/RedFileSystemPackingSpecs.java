
package com.jfixby.scarabei.red.filesystem.archived;

import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.packing.FileSystemPackingSpecs;
import com.jfixby.scarabei.api.io.OutputStream;

public class RedFileSystemPackingSpecs implements FileSystemPackingSpecs {

	private String schema;
	private OutputStream os;
	private Iterable<File> listFiles;

	@Override
	public void setOutputStream (OutputStream os) {
		this.os = os;
	}

	@Override
	public OutputStream getOutputStream () {
		return os;
	}

	@Override
	public void setCompressionSchemaName (String schema) {
		this.schema = schema;
	}

	@Override
	public String getCompressionSchemaName () {
		return schema;
	}

	@Override
	public void setFilesList (Iterable<File> listFiles) {
		this.listFiles = listFiles;
	}

	@Override
	public Iterable<File> listFiles () {
		return listFiles;
	}

}
