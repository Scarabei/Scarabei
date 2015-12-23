package com.jfixby.rmi.client.files;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.cmns.api.io.BufferOutputStream;
import com.jfixby.cmns.api.io.Data;
import com.jfixby.cmns.api.util.path.RelativePath;

public class RMIFileOutputStream implements FileOutputStream {

	private FileOutputStream os;

	public RMIFileOutputStream(RMIDataContainer rmiDataContainer, RelativePath relativePath) throws IOException {
		try {
			os = rmiDataContainer.lookup().getOutputStream(relativePath);
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
	public void write(Data data) throws IOException {
	}

	@Override
	public void close() throws IOException {
	}

	@Override
	public void flush() throws IOException {
	}

	@Override
	public void write(byte[] bytes) throws IOException {
	}

	@Override
	public OutputStream toJavaOutputStream() {
		return null;
	}

}
