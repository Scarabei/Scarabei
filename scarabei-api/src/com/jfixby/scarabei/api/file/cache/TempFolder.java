
package com.jfixby.scarabei.api.file.cache;

import java.io.IOException;

import com.jfixby.scarabei.api.file.File;

public interface TempFolder {

	File getRoot ();

	void delete () throws IOException;

}
