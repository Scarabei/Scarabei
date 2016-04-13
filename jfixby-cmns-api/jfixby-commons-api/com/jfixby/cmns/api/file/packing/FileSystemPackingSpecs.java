
package com.jfixby.cmns.api.file.packing;

import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.io.OutputStream;

public interface FileSystemPackingSpecs {

	void setFilesList (Iterable<File> files);

	void setOutputStream (OutputStream os);

	Iterable<File> listFiles ();

	OutputStream getOutputStream ();

	void setCompressionSchemaName (String string);

	String getCompressionSchemaName ();

}
