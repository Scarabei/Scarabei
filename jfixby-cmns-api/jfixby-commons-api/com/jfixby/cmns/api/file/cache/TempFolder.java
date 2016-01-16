package com.jfixby.cmns.api.file.cache;

import com.jfixby.cmns.api.file.File;

public interface TempFolder {

	File getRoot();

	void delete();

}
