package com.jfixby.red.filesystem.virtual;

import java.io.IOException;
import java.io.OutputStream;

import com.jfixby.cmns.api.filesystem.FileOutputStream;
import com.jfixby.cmns.api.io.BufferOutputStream;
import com.jfixby.cmns.api.io.Data;
import com.jfixby.cmns.api.io.IO;

public class VirtualFileOutputStream implements FileOutputStream {

	private ContentLeaf leaf;
	private BufferOutputStream os;

	public VirtualFileOutputStream(VirtualFile output_file) throws IOException {
		VirtualFile v_file = (VirtualFile) output_file;
		leaf = v_file.getContent();
		if (leaf == null) {
			leaf = v_file.createFile();
			if (leaf == null) {
				throw new IOException("Unable to write to the file: " + output_file);
			}
		}
		os = IO.newBufferOutputStream();
	}

	@Override
	public void write(Data data) throws IOException {
		os.write(data);
	}

	@Override
	public void close() throws IOException {
		os.close();
		byte[] data = os.getBytes();
		leaf.setData(data);
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
