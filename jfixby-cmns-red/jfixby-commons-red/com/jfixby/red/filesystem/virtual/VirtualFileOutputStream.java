
package com.jfixby.red.filesystem.virtual;

import java.io.IOException;
import java.io.OutputStream;

import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.cmns.api.io.BufferOutputStream;
import com.jfixby.cmns.api.io.Data;
import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.util.JUtils;

public class VirtualFileOutputStream implements FileOutputStream {

	private ContentLeaf leaf;
	private BufferOutputStream os;

	public VirtualFileOutputStream (VirtualFile output_file) throws IOException {
		VirtualFile v_file = output_file;
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
	public void forceClose () {
		os.forceClose();
	}

	@Override
	public void write (Data data) throws IOException {
		os.write(data);
	}

	@Override
	public void write (byte[] bytes) throws IOException {
		this.write(JUtils.newByteArray(bytes));
	}

	@Override
	public void close () throws IOException {
		os.close();
		ByteArray data = os.getBytes();
		leaf.setData(data);
	}

	@Override
	public void flush () throws IOException {
		os.flush();
	}

	@Override
	public void write (ByteArray bytes) throws IOException {
		os.write(bytes);
	}

	@Override
	public OutputStream toJavaOutputStream () {
		return os.toJavaOutputStream();
	}

}
