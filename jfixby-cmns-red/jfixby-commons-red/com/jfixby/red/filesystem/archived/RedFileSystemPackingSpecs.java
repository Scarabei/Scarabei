package com.jfixby.red.filesystem.archived;

import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.packing.FileSystemPackingSpecs;
import com.jfixby.cmns.api.io.OutputStream;

public class RedFileSystemPackingSpecs implements FileSystemPackingSpecs {

	private String schema;
	private OutputStream os;
	private File folder_to_pack;

	@Override
	public void setTargetFolder(File folder_to_pack) {
		this.folder_to_pack = folder_to_pack;
	}

	@Override
	public void setOutputStream(OutputStream os) {
		this.os = os;
	}

	@Override
	public File getTargetFolder() {
		return folder_to_pack;
	}

	@Override
	public OutputStream getOutputStream() {
		return os;
	}

	@Override
	public void setCompressionSchemaName(String schema) {
		this.schema = schema;
	}

	@Override
	public String getCompressionSchemaName() {
		return schema;
	}

}
