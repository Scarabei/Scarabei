package com.jfixby.cmns.api.path;

import com.jfixby.cmns.api.file.File;


public interface FileFilter {

	boolean fits(File child);

}
