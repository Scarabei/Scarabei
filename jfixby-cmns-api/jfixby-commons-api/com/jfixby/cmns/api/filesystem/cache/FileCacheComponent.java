package com.jfixby.cmns.api.filesystem.cache;

import com.jfixby.cmns.api.filesystem.File;

public interface FileCacheComponent {

	TempFolder createTempFolder(File where);

}
