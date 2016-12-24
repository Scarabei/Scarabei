
package com.jfixby.scarabei.api.file.cache;

import java.io.IOException;

import com.jfixby.scarabei.api.file.File;

public interface FileCacheComponent {

	TempFolder createTempFolder (File where) throws IOException;

	TempFolder createTempFolder () throws IOException;

}
