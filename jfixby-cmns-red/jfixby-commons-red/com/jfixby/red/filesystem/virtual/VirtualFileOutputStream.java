
package com.jfixby.red.filesystem.virtual;

import java.io.IOException;

import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.red.io.AbstractRedOutputStream;

public class VirtualFileOutputStream extends AbstractRedOutputStream<RedVirtualFileOutputStreamOperator>
	implements FileOutputStream {

	public VirtualFileOutputStream (final VirtualFile output_file) throws IOException {
		super(new RedVirtualFileOutputStreamOperator(output_file));
	}

}
