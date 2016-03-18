package com.jfixby.cmns.api.file.cache;

import com.jfixby.cmns.api.file.File;

public interface FileCacheComponent {

    TempFolder createTempFolder(File where);

    TempFolder createTempFolder();

}
