package com.jfixby.cmns.api.file.packing;

import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.io.OutputStream;

public interface FileSystemPackingSpecs {

	void setTargetFolder(File folder_to_pack);

	void setOutputStream(OutputStream os);

	File getTargetFolder();

	OutputStream getOutputStream();

	void setCompressionSchemaName(String string);

	String getCompressionSchemaName();

}
