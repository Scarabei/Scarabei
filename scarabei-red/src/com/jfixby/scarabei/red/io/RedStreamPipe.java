
package com.jfixby.scarabei.red.io;

import java.io.IOException;

import com.jfixby.scarabei.api.io.InputStream;
import com.jfixby.scarabei.api.io.OutputStream;
import com.jfixby.scarabei.api.io.StreamPipe;
import com.jfixby.scarabei.api.io.U_StreamPipeProgressListener;

public class RedStreamPipe implements StreamPipe {

	private InputStream input_stream;
	private U_StreamPipeProgressListener progress_listener;
	private OutputStream output_stream;

	public RedStreamPipe (InputStream input_stream, OutputStream output_stream, U_StreamPipeProgressListener progress_listener) {

		this.input_stream = input_stream;
		this.progress_listener = progress_listener;
		this.output_stream = output_stream;
		if (this.progress_listener == null) {
			this.progress_listener = U_StreamPipeProgressListener.NULL;
		}
	}

	public static void copyStream (java.io.InputStream input, java.io.OutputStream output) throws IOException {
		byte[] buffer = new byte[1024 * 100]; // Adjust if you want
		int bytesRead;
		while ((bytesRead = input.read(buffer)) != -1) {
			output.write(buffer, 0, bytesRead);
		}
	}

	@Override
	public void transferAll () throws IOException {
		// int total = this.input_stream.available();

		// progress_listener.begin(total);
		byte[] all = input_stream.readAll().toArray();
		this.output_stream.write(all);

		// while (this.input_stream.hasData()) {
		// Data data = this.input_stream.read();
		// this.output_stream.write(data);
		// int left = this.input_stream.available();
		// progress_listener.update(total - left);
		// }
		progress_listener.end();

		input_stream.close();
		output_stream.flush();
		output_stream.close();

	}
}
