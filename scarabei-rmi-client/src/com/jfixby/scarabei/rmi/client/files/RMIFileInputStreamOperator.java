
package com.jfixby.scarabei.rmi.client.files;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.jfixby.scarabei.api.io.IO;
import com.jfixby.scarabei.api.io.JavaInputStreamOperator;
import com.jfixby.scarabei.api.java.ByteArray;
import com.jfixby.scarabei.api.util.path.RelativePath;

public class RMIFileInputStreamOperator implements JavaInputStreamOperator {

	private final RMIDataContainer rmiDataContainer;
	private final RelativePath relativePath;
	InputStream is;

	public RMIFileInputStreamOperator (final RMIDataContainer rmiDataContainer, final RelativePath relativePath) {
		this.rmiDataContainer = rmiDataContainer;
		this.relativePath = relativePath;
	}

	@Override
	public void closeStream () {
		IO.forceClose(this.is);
		this.is = null;
	}

	@Override
	public InputStream getJavaStream () throws IOException {
		if (this.is == null) {
			try {
				final ByteArray data = this.rmiDataContainer.lookup().readDataFromFile(this.relativePath.steps().toJavaList());
				this.is = new ByteArrayInputStream(data.toArray());
			} catch (MalformedURLException | RemoteException | NotBoundException e) {
				throw new IOException(e);
			}
		}
		return this.is;

	}

	@Override
	public boolean isReadAllSupported () {
		return true;
	}

	@Override
	public ByteArray readAll () throws IOException {
		try {
			final ByteArray data = this.rmiDataContainer.lookup().readDataFromFile(this.relativePath.steps().toJavaList());
			return data;
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			throw new IOException(e);
		}
	}

}
