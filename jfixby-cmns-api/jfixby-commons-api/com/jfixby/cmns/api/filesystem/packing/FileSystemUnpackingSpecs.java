package com.jfixby.cmns.api.filesystem.packing;

import com.jfixby.cmns.api.filesystem.File;
import com.jfixby.cmns.api.io.InputStream;

public interface FileSystemUnpackingSpecs {

	void setTargetFolder(File local_resource_folder);

	void setInputStream(InputStream is);

	File getTargetFolder();

	InputStream getInputStream();

}
