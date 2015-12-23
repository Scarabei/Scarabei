package com.jfixby.rmi.client.files;

import java.io.IOException;
import java.io.OutputStream;
import java.rmi.NotBoundException;
import java.util.List;

import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.cmns.api.io.BufferOutputStream;
import com.jfixby.cmns.api.io.Data;
import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.util.path.RelativePath;

public class RMIFileOutputStream implements FileOutputStream {

	private BufferOutputStream os;
	private RMIDataContainer rmiDataContainer;
	private List<String> relativePath;

	public RMIFileOutputStream(RMIDataContainer rmiDataContainer, RelativePath relativePath) throws IOException {
		this.rmiDataContainer = rmiDataContainer;
		this.relativePath = relativePath.steps().toJavaList();
		os = IO.newBufferOutputStream();
		try {
			rmiDataContainer.lookup().ping();
		} catch (NotBoundException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void write(Data data) throws IOException {
		os.write(data);
	}

	@Override
	public void close() throws IOException {
		os.close();
		byte[] data = os.getBytes();
		try {
			rmiDataContainer.lookup().writeDataToFile(relativePath, data);
		} catch (NotBoundException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void flush() throws IOException {
		os.flush();
	}

	@Override
	public void write(byte[] bytes) throws IOException {
		os.write(bytes);
	}

	@Override
	public OutputStream toJavaOutputStream() {
		return os.toJavaOutputStream();
	}

}
