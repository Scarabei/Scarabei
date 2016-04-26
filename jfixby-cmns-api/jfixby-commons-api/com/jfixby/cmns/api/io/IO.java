
package com.jfixby.cmns.api.io;

import java.io.IOException;

import com.jfixby.cmns.api.ComponentInstaller;
import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.cmns.api.java.ByteArray;

public class IO {

	static private ComponentInstaller<IOComponent> componentInstaller = new ComponentInstaller<IOComponent>("IO");

	public static final void installComponent (final IOComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final IOComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final IOComponent component () {
		return componentInstaller.getComponent();
	}

	public static InputStream toInputStream (final java.io.InputStream java_input_stream) throws IOException {
		return component().toInputStream(java_input_stream);
	}

	public static OutputStream toOutputStream (final java.io.OutputStream java_output_stream) throws IOException {
		return component().toOutputStream(java_output_stream);
	}

	public static Buffer newBuffer (final ByteArray bytes) {
		return component().newBuffer(bytes);
	}

	public static BufferInputStream newBufferInputStream (final Buffer buffer) {
		return component().newBufferInputStream(buffer);
	}

	public static Buffer readStreamToBuffer (final FileInputStream is) throws IOException {
		return component().readStreamToBuffer(is);
	}

	public static StreamPipe newStreamPipe (final InputStream input_stream, final OutputStream output_stream,
		final U_StreamPipeProgressListener progress_listener) {
		return component().newStreamPipe(input_stream, output_stream, progress_listener);
	}

	public static BufferOutputStream newBufferOutputStream () {
		return component().newBufferOutputStream();
	}

	public static int readByte (final java.io.InputStream javaInputStream) throws IOException {
		return component().readByte(javaInputStream);
	}

	public static int readInt (final java.io.InputStream javaInputStream) throws IOException {
		return component().readInt(javaInputStream);
	}

	public static void writeInt (final java.io.OutputStream javaOutputStream, final int value) throws IOException {
		component().writeInt(javaOutputStream, value);
	}

	public static void writeByte (final java.io.OutputStream javaOutputStream, final int value) throws IOException {
		component().writeByte(javaOutputStream, value);
	}

	public static void forceClose (final java.io.OutputStream os) {
		component().forceClose(os);
	}

	public static void forceClose (final java.io.InputStream is) {
		component().forceClose(is);
	}

	public static GZipOutputStream newGZipStream (final OutputStream os) throws IOException {
		return component().newGZipStream(os);
	}

	public static GZipInputStream newGZipStream (final InputStream is) throws IOException {
		return component().newGZipStream(is);
	}

	public static void writeBytes (final java.io.OutputStream javaOutputStream, final int[] bytes) throws IOException {
		component().writeBytes(javaOutputStream, bytes);
	}

	public static void readBytes (final java.io.InputStream javaInputStream, final int[] array) throws IOException {
		component().readBytes(javaInputStream, array);
	}

	public static JavaBitInputStream newBitInputStream (final java.io.InputStream is) {
		return component().newBitInputStream(is);
	}

	public static JavaBitOutputStream newBitOutputStream (final java.io.OutputStream os, final JavaBitStreamMode mode) {
		return component().newBitOutputStream(os, mode);
	}

	public static JavaBitInputStream newBitInputStream (final java.io.InputStream is, final JavaBitStreamMode simpleByte) {
		return component().newBitInputStream(is, simpleByte);
	}

	public static JavaBitOutputStream newBitOutputStream (final java.io.OutputStream os) {
		return component().newBitOutputStream(os);
	}

	public static void writeShort (final java.io.OutputStream javaOutputStream, final int value) throws IOException {
		component().writeShort(javaOutputStream, value);
	}

	public static int readShort (final java.io.InputStream steam) throws IOException {
		return component().readShort(steam);
	}

	public static ByteArray serialize (final java.io.Serializable object) throws IOException {
		return invoke().serialize(object);
	}

	public static void serialize (final java.io.Serializable object, final OutputStream output_stream) throws IOException {
		component().serialize(object, output_stream);
	}

	public static <T> T deserialize (final Class<T> type, final InputStream input_stream) throws IOException {
		return component().deserialize(type, input_stream);
	}

	public static <T> T deserialize (final Class<T> type, final ByteArray bytes) throws IOException {
		return component().deserialize(type, bytes);
	}

}
