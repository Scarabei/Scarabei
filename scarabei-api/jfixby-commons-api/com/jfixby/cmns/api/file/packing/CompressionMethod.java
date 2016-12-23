
package com.jfixby.cmns.api.file.packing;

import java.io.IOException;

import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.io.InputStream;
import com.jfixby.cmns.api.io.OutputStream;

public interface CompressionMethod {

	public String getName ();

	public void pack (Iterable<File> input, OutputStream os) throws IOException;

	public CompressionSchema readSchema (InputStream jis) throws IOException;

}
