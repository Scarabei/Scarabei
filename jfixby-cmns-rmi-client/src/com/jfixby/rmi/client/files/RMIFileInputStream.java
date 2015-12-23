package com.jfixby.rmi.client.files;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.cmns.api.io.Data;
import com.jfixby.cmns.api.util.path.RelativePath;

public class RMIFileInputStream implements FileInputStream {

	private FileInputStream is;

	public RMIFileInputStream(RMIDataContainer rmiDataContainer, RelativePath relativePath) throws IOException {
		try {
			is = rmiDataContainer.lookup().getInputStream(relativePath.steps().toJavaList());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		throw new IOException();
	}

	@Override
	public boolean hasData() throws IOException {
		return false;
	}

	@Override
	public Data read() throws IOException {
		return null;
	}

	@Override
	public int available() throws IOException {
		return 0;
	}

	@Override
	public void close() throws IOException {
	}

	@Override
	public byte[] readAll() throws IOException {
		return null;
	}

	@Override
	public InputStream toJavaInputStream() {
		return null;
	}

}
