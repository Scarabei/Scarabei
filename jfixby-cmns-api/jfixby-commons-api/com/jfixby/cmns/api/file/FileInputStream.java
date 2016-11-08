
package com.jfixby.cmns.api.file;

import java.io.IOException;

import com.jfixby.cmns.api.io.InputStream;

public interface FileInputStream extends InputStream {

	public long getFileSize () throws IOException;

}
