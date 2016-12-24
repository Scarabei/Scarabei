
package com.jfixby.scarabei.rmi.client.files;

import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.FileInputStream;
import com.jfixby.scarabei.api.util.path.RelativePath;
import com.jfixby.scarabei.red.io.AbstractRedInputStream;

public class RMIFileInputStream extends AbstractRedInputStream<RMIFileInputStreamOperator> implements FileInputStream {

	public RMIFileInputStream (final RMIDataContainer rmiDataContainer, final RelativePath relativePath) {
		super(new RMIFileInputStreamOperator(rmiDataContainer, relativePath));
// this.rmiDataContainer = rmiDataContainer;
	}

	@Override
	public long getFileSize () {
		Err.reportError("Not implemented yet");
		return 0;
	}

}
