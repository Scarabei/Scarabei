package com.jfixby.cmns.api.io;

import java.io.IOException;

import com.jfixby.cmns.api.components.ComponentInstaller;
import com.jfixby.cmns.api.filesystem.FileInputStream;

public class IO {

	static private ComponentInstaller<IOComponent> componentInstaller = new ComponentInstaller<IOComponent>(
			"IO");

	public static final void installComponent(IOComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final IOComponent invoke() {
		return componentInstaller.invokeComponent();
	}

	public static final IOComponent component() {
		return componentInstaller.getComponent();
	}

	public static InputStream toInputStream(
			java.io.InputStream java_input_stream) throws IOException {
		return invoke().toInputStream(java_input_stream);
	}

	public static OutputStream toOutputStream(
			java.io.OutputStream java_output_stream) throws IOException {
		return invoke().toOutputStream(java_output_stream);
	}

	public static Buffer newBuffer(byte[] bytes) {
		return invoke().newBuffer(bytes);
	}

	public static BufferInputStream newBufferInputStream(Buffer buffer) {
		return invoke().newBufferInputStream(buffer);
	}

	public static Buffer readStreamToBuffer(FileInputStream is)
			throws IOException {
		return invoke().readStreamToBuffer(is);
	}

	public static StreamPipe newStreamPipe(InputStream input_stream,
			OutputStream output_stream,
			U_StreamPipeProgressListener progress_listener) {
		return invoke().newStreamPipe(input_stream, output_stream,
				progress_listener);
	}

	public static BufferOutputStream newBufferOutputStream() {
		return invoke().newBufferOutputStream();
	}

	public static void serialize(Object object, OutputStream output_stream)
			throws IOException {
		invoke().serialize(object, output_stream);
	}

	public static <T> T deserialize(Class<T> type, InputStream input_stream)
			throws IOException {
		return invoke().deserialize(type, input_stream);
	}
}
