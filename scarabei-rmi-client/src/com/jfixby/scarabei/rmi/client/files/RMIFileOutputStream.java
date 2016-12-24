
package com.jfixby.scarabei.rmi.client.files;

import com.jfixby.scarabei.api.file.FileOutputStream;
import com.jfixby.scarabei.api.util.path.RelativePath;
import com.jfixby.scarabei.red.io.AbstractRedOutputStream;

public class RMIFileOutputStream extends AbstractRedOutputStream<RedRMIFileOutputStreamOperator> implements FileOutputStream {

	public RMIFileOutputStream (final RMIDataContainer rmiDataContainer, final RelativePath relativePath, final boolean append) {
		super(new RedRMIFileOutputStreamOperator(rmiDataContainer, relativePath, append));
	}

}
