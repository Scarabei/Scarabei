
package com.jfixby.cmns.api.file.cache;

import java.io.IOException;

import com.jfixby.cmns.api.file.File;

public interface FileCacheComponent {

	TempFolder createTempFolder (File where) throws IOException;

	TempFolder createTempFolder () throws IOException;

}
