
package com.jfixby.scarabei.red.filesystem;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FileInputStream;
import com.jfixby.scarabei.api.file.FileOutputStream;
import com.jfixby.scarabei.red.io.JavaFileInputStream;
import com.jfixby.scarabei.red.io.JavaFileOutputStream;

public abstract class AbstractLocalFileSystem extends AbstractFileSystem {

	@Override
	final public FileOutputStream newFileOutputStream (final File output_file, final boolean append) {
		Debug.checkNull("File", output_file);
		Debug.checkTrue("File belongs to this filesystem?", output_file.getFileSystem() == this);
		return new JavaFileOutputStream(output_file.toJavaFile(), append);
	}

	@Override
	final public FileOutputStream newFileOutputStream (final File output_file) {
		return this.newFileOutputStream(output_file, false);
	}

	@Override
	final public FileInputStream newFileInputStream (final File input_file) {
		Debug.checkNull("File", input_file);
		Debug.checkTrue("File belongs to this filesystem?", input_file.getFileSystem() == this);
		return new JavaFileInputStream(input_file.toJavaFile());
	}

}
