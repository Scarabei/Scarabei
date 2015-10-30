package com.jfixby.cmns.api.path;

import com.jfixby.cmns.api.filesystem.File;


public interface FileFilter {

	boolean fits(File child);

}
