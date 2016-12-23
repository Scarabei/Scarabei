
package com.jfixby.cmns.api.file.cache;

import java.io.IOException;

import com.jfixby.cmns.api.file.File;

public interface TempFolder {

	File getRoot ();

	void delete () throws IOException;

}
