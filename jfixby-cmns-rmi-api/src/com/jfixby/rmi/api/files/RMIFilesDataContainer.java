package com.jfixby.rmi.api.files;

import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.cmns.api.util.path.RelativePath;
import com.jfixby.rmi.api.RMIFace;

public interface RMIFilesDataContainer extends RMIFace {

	boolean delete(RelativePath relativePath);

	FileInputStream getInputStream(RelativePath relativePath);

	FileOutputStream getOutputStream(RelativePath relativePath);

	boolean isFolder(RelativePath relativePath);

	boolean isFile(RelativePath relativePath);

	boolean exists(RelativePath relativePath);

	List<String> listChildren(RelativePath relativePath);

	boolean mkdirs(RelativePath relativePath);

	void rename(RelativePath relativePath, String new_name);

	long lastModified(RelativePath relativePath);

	long getSize(RelativePath relativePath);

}
