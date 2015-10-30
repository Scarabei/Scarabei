package com.jfixby.cmns.api.io;

import java.io.IOException;

import com.jfixby.cmns.api.filesystem.FileOutputStream;

public interface IOComponent  {

	StreamPipe newStreamPipe(InputStream input_stream,
			OutputStream output_stream,
			U_StreamPipeProgressListener progress_listener);

	void serialize(Object object, OutputStream output_stream)
			throws IOException;

	public <T> T deserialize(Class<T> type, InputStream input_stream)
			throws IOException;

	Buffer newBuffer();

	BufferOutputStream newBufferOutputStream();

	Buffer readStreamToBuffer(InputStream is) throws IOException;

	Buffer newBuffer(byte[] bytes);

	void writeBufferToStream(Buffer buffer, FileOutputStream os)
			throws IOException;

	BufferInputStream newBufferInputStream(Buffer buffer);

	
	

	public InputStream toInputStream(java.io.InputStream java_input_stream)
			throws IOException;
	
	public OutputStream toOutputStream(java.io.OutputStream java_output_stream)
			throws IOException;

	// public String deserializeFromString(String from_string) throws
	// IOException;

	// Object deserialize(FileInputStream output_stream,Class<?> type);

}
