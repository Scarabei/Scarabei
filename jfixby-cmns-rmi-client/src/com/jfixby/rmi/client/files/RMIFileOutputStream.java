
package com.jfixby.rmi.client.files;

import java.io.IOException;

import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.cmns.api.util.path.RelativePath;
import com.jfixby.red.io.AbstractRedOutputStream;

public class RMIFileOutputStream extends AbstractRedOutputStream<RedRMIFileOutputStreamOperator> implements FileOutputStream {

	public RMIFileOutputStream (final RMIDataContainer rmiDataContainer, final RelativePath relativePath) throws IOException {
		super(new RedRMIFileOutputStreamOperator(rmiDataContainer, relativePath));
	}

}
