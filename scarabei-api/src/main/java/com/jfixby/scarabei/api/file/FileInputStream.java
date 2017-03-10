
package com.jfixby.scarabei.api.file;

import java.io.IOException;

import com.jfixby.scarabei.api.io.InputStream;

public interface FileInputStream extends InputStream {

	public long getFileSize () throws IOException;

}
