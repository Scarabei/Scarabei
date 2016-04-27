
package com.jfixby.rmi.client.files;

import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.cmns.api.util.path.RelativePath;
import com.jfixby.red.io.AbstractRedInputStream;

public class RMIFileInputStream extends AbstractRedInputStream<RMIFileInputStreamOperator> implements FileInputStream {

	public RMIFileInputStream (final RMIDataContainer rmiDataContainer, final RelativePath relativePath) {
		super(new RMIFileInputStreamOperator(rmiDataContainer, relativePath));
	}

}
