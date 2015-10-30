package com.jfixby.cmns.api.filesystem.packing;

import com.jfixby.cmns.api.filesystem.File;
import com.jfixby.cmns.api.io.OutputStream;

public interface FileSystemPackingSpecs {

	void setTargetFolder(File folder_to_pack);

	void setOutputStream(OutputStream os);

	File getTargetFolder();

	OutputStream getOutputStream();

}
