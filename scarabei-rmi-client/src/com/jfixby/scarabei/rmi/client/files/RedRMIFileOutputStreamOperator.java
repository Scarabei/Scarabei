
package com.jfixby.scarabei.rmi.client.files;

import java.io.IOException;
import java.io.OutputStream;
import java.rmi.NotBoundException;

import com.jfixby.scarabei.api.io.BufferOutputStream;
import com.jfixby.scarabei.api.io.IO;
import com.jfixby.scarabei.api.io.JavaOutputStreamOperator;
import com.jfixby.scarabei.api.java.ByteArray;
import com.jfixby.scarabei.api.util.path.RelativePath;

public class RedRMIFileOutputStreamOperator implements JavaOutputStreamOperator {
	final RMIDataContainer rmiDataContainer;
	private final java.util.List<String> relativePath;
	private BufferOutputStream os;
	private final boolean append;

	public RedRMIFileOutputStreamOperator (final RMIDataContainer rmiDataContainer, final RelativePath relativePath,
		final boolean append) {
		this.rmiDataContainer = rmiDataContainer;
		this.relativePath = relativePath.steps().toJavaList();
		this.append = append;

	}

	@Override
	public void closeStream () {
		this.os.close();
		final ByteArray data = this.os.getBytes();
		try {

			this.rmiDataContainer.lookup().writeDataToFile(this.relativePath, data, this.append);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.os = null;
	}

	@Override
	public OutputStream getJavaStream () throws IOException {
		if (this.os == null) {
			this.os = IO.newBufferOutputStream();
			try {
				this.rmiDataContainer.lookup().ping();
			} catch (final NotBoundException e) {
				throw new IOException(e);
			}
		}
		return this.os.toJavaOutputStream();
	}

	@Override
	public boolean isBulkWriteSupported () {
		return false;
	}

	@Override
	public void writeAll (final ByteArray bytes) throws IOException {
		try {
			this.rmiDataContainer.lookup().writeDataToFile(this.relativePath, bytes, this.append);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

}
