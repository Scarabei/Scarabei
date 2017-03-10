
package com.jfixby.scarabei.api.file.packing;

import java.io.IOException;

import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.io.InputStream;
import com.jfixby.scarabei.api.io.OutputStream;

public interface CompressionMethod {

	public String getName ();

	public void pack (Iterable<File> input, OutputStream os) throws IOException;

	public CompressionSchema readSchema (InputStream jis) throws IOException;

}
