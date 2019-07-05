
package com.jfixby.scarabei.api.io;

import java.io.IOException;

import com.jfixby.scarabei.api.ComponentInstaller;
import com.jfixby.scarabei.api.java.ByteArray;

public class IO {

	static private ComponentInstaller<IOComponent> componentInstaller = new ComponentInstaller<IOComponent>("IO");

	public static final void installComponent (final IOComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static final IOComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final IOComponent component () {
		return componentInstaller.getComponent();
	}

	public static InputStream newInputStream (final InputStreamOpener inputStreamOpener) {
		return invoke().newInputStream(inputStreamOpener);
	}

	public static OutputStream newOutputStream (final OutputStreamOpener outputStreamOpener) {
		return invoke().newOutputStream(outputStreamOpener);
	}

	public static Buffer newBuffer (final ByteArray bytes) {
		return invoke().newBuffer(bytes);
	}

	public static BufferInputStream newBufferInputStream (final Buffer buffer) {
		return invoke().newBufferInputStream(buffer);
	}

	public static Buffer readStreamToBuffer (final InputStream is) throws IOException {
		return invoke().readStreamToBuffer(is);
	}

	public static StreamPipe newStreamPipe (final InputStream input_stream, final OutputStream output_stream,
		final U_StreamPipeProgressListener progress_listener) {
		return invoke().newStreamPipe(input_stream, output_stream, progress_listener);
	}

	public static BufferOutputStream newBufferOutputStream () {
		return invoke().newBufferOutputStream();
	}

	public static LazyInputStream newLazyInputStream (final InputStream input_stream) {
		return invoke().newLazyInputStream(input_stream);
	}

	public static int readByte (final java.io.InputStream javaInputStream) throws IOException {
		return invoke().readByte(javaInputStream);
	}

	public static int readInt (final java.io.InputStream javaInputStream) throws IOException {
		return invoke().readInt(javaInputStream);
	}

	public static void writeInt (final java.io.OutputStream javaOutputStream, final int value) throws IOException {
		invoke().writeInt(javaOutputStream, value);
	}

	public static void writeByte (final java.io.OutputStream javaOutputStream, final int value) throws IOException {
		invoke().writeByte(javaOutputStream, value);
	}

	public static void forceClose (final java.io.OutputStream os) {
		invoke().forceClose(os);
	}

	public static void forceClose (final java.io.InputStream is) {
		invoke().forceClose(is);
	}

	public static void forceClose (final OutputStream os) {
		invoke().forceClose(os);
	}

	public static void forceClose (final InputStream is) {
		invoke().forceClose(is);
	}

	public static GZipOutputStream newGZipStream (final OutputStream os) throws IOException {
		return invoke().newGZipStream(os);
	}

	public static GZipInputStream newGZipStream (final InputStream is) throws IOException {
		return invoke().newGZipStream(is);
	}

	public static void writeBytes (final java.io.OutputStream javaOutputStream, final int[] bytes) throws IOException {
		invoke().writeBytes(javaOutputStream, bytes);
	}

	public static void readBytes (final java.io.InputStream javaInputStream, final int[] array) throws IOException {
		invoke().readBytes(javaInputStream, array);
	}

	public static JavaBitInputStream newBitInputStream (final java.io.InputStream is) {
		return invoke().newBitInputStream(is);
	}

	public static JavaBitOutputStream newBitOutputStream (final java.io.OutputStream os, final JavaBitStreamMode mode) {
		return invoke().newBitOutputStream(os, mode);
	}

	public static JavaBitInputStream newBitInputStream (final java.io.InputStream is, final JavaBitStreamMode simpleByte) {
		return invoke().newBitInputStream(is, simpleByte);
	}

	public static JavaBitOutputStream newBitOutputStream (final java.io.OutputStream os) {
		return invoke().newBitOutputStream(os);
	}

	public static void writeShort (final java.io.OutputStream javaOutputStream, final int value) throws IOException {
		invoke().writeShort(javaOutputStream, value);
	}

	public static int readShort (final java.io.InputStream steam) throws IOException {
		return invoke().readShort(steam);
	}

	public static ByteArray serialize (final java.io.Serializable object) throws IOException {
		return invoke().serialize(object);
	}

	public static void serialize (final java.io.Serializable object, final OutputStream output_stream) throws IOException {
		invoke().serialize(object, output_stream);
	}

	public static <T> T deserialize (final Class<T> type, final InputStream input_stream) throws IOException {
		return invoke().deserialize(type, input_stream);
	}

	public static <T> T deserialize (final Class<T> type, final ByteArray bytes) throws IOException {
		return invoke().deserialize(type, bytes);
	}

	public static <T> T deserialize (final Class<T> type, final byte[] bytes) throws IOException {
		return invoke().deserialize(type, bytes);
	}

	public static ByteArray compress (final ByteArray data) {
		return invoke().compress(data);
	}

	public static ByteArray decompress (final ByteArray data) {
		return invoke().decompress(data);
	}

	public static ByteArray readMax (final InputStream is, final long maxBytesToRead) throws IOException {
		return invoke().readMax(is, maxBytesToRead);
	}

}
