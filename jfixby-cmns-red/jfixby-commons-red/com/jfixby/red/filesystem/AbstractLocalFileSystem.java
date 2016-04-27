
package com.jfixby.red.filesystem;

import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.red.io.JavaFileInputStream;
import com.jfixby.red.io.JavaFileOutputStream;

public abstract class AbstractLocalFileSystem extends AbstractFileSystem {

	@Override
	final public FileOutputStream newFileOutputStream (final File output_file) {
		Debug.checkNull("File", output_file);
		Debug.checkTrue("File belongs to this filesystem?", output_file.getFileSystem() == this);
		return new JavaFileOutputStream(output_file.toJavaFile());
	}

	@Override
	final public FileInputStream newFileInputStream (final File input_file) {
		Debug.checkNull("File", input_file);
		Debug.checkTrue("File belongs to this filesystem?", input_file.getFileSystem() == this);
		return new JavaFileInputStream(input_file.toJavaFile());
	}

}
