package com.jfixby.cmns.api.filesystem.cache;

import com.jfixby.cmns.api.filesystem.File;

public interface TempFolder {

	File getRoot();

	void delete();

}
