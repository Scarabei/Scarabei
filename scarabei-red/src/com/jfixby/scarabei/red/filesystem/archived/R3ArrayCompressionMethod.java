
package com.jfixby.scarabei.red.filesystem.archived;

import java.io.IOException;

import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.packing.CompressionMethod;
import com.jfixby.scarabei.api.file.packing.CompressionSchema;
import com.jfixby.scarabei.api.io.InputStream;
import com.jfixby.scarabei.api.io.OutputStream;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.util.JUtils;
import com.jfixby.scarabei.api.util.path.RelativePath;

public class R3ArrayCompressionMethod implements CompressionMethod {

	public static final String SCHEMA_NAME = "R3.Array";

	@Override
	public String getName () {
		return SCHEMA_NAME;
	}

	@Override
	public void pack (final Iterable<File> input, final OutputStream os) throws IOException {
		final java.io.OutputStream jos = os.toJavaOutputStream();

		final TagsList list = new TagsList();

		final RelativePath path = JUtils.newRelativePath();

		this.absrobCollection(input, path, list);
		long offset = 0;
		final FilePointers pointers = new FilePointers();
		{
			final FilePointer root = new FilePointer();
			root.path = "";
			root.isFile = false;
			pointers.list.add(root);
		}
		for (final FileTag tag : list.tags) {
			final FilePointer pointer = new FilePointer();
			pointer.path = tag.path.toString();
			pointer.isFile = tag.file.isFile();
			if (pointer.isFile) {
				pointer.offset = offset;
				pointer.size = tag.file.getSize();
				offset = offset + pointer.size;
			} else {

			}
			pointers.list.add(pointer);
		}

		final String shema_string = Json.serializeToString(pointers).toString();
		final byte[] shema_data = shema_string.getBytes();

		this.writeLong(jos, shema_string.length());
		L.d("schema_len", shema_string.length());
		endLine(jos);

		// for (int k = 0; k < shema_data.length; k++) {
		// // jos.writeBytes(shema_string);
		// jos.write(shema_data[k]);
		// }
		//
		jos.write(shema_data);
		endLine(jos);
		jos.write("data:".getBytes());
		this.writeLong(jos, offset);
		endLine(jos);
		for (final FileTag tag : list.tags) {
			if (tag.file.isFile()) {
				final byte[] data = tag.file.readBytes().toArray();
				jos.write(data);
			}
		}
		jos.flush();
	}

	private void writeLong (final java.io.OutputStream jos, final long offset) throws IOException {
		final byte[] array = this.longToByteArray(offset);
		jos.write(array);
	}

	public byte[] longToByteArray (final long value) {
		return new byte[] {(byte)(value >> 8 * 7), (byte)(value >> 8 * 6), (byte)(value >> 8 * 5), (byte)(value >> 8 * 4),
			(byte)(value >> 8 * 3), (byte)(value >> 8 * 2), (byte)(value >> 8), (byte)value};
	}

	public static void endLine (final java.io.OutputStream jos) throws IOException {
		jos.write(END_LINE.getBytes());
	}

	private void absorb (final File file, final RelativePath path, final TagsList list) throws IOException {
		if (file.isFile()) {
			this.absorbFile(file, path, list);
		}
		if (file.isFolder()) {
			this.absorbFolder(file, path, list);
		}
	}

	private void absorbFile (final File file, final RelativePath path, final TagsList list) throws IOException {
		if (!file.isFile()) {
			throw new IOException(file + " is not a file");
		}
		final FileTag info = new FileTag(file, path);
		list.addInfo(info);
	}

	private void absorbFolder (final File folder, final RelativePath path, final TagsList list) throws IOException {
		if (!folder.isFolder()) {
			Err.reportError(folder + " is not a folder");
		}
		final FileTag info = new FileTag(folder, path);
		list.addInfo(info);
		this.absrobCollection(folder.listDirectChildren(), path, list);
	}

	private void absrobCollection (final Iterable<File> input, final RelativePath path, final TagsList list) throws IOException {
		for (final File file : input) {
			final RelativePath path_i = path.child(file.getName());
			this.absorb(file, path_i, list);
		}
	}

	private void skip (final int k, final java.io.InputStream jis) throws IOException {
		for (int i = k; i > 0; i--) {
			jis.read();
		}
	}

	public static final String END_LINE = "#";// " ←\n"

	@Override
	public CompressionSchema readSchema (final InputStream jis) throws IOException {
		final java.io.InputStream is = jis.toJavaInputStream();

		final long schema_len = this.readLong(is);
		L.d("schema_len", schema_len);
		this.skip(END_LINE.length(), is);

		final byte[] shema_bytes = new byte[(int)schema_len];
		is.read(shema_bytes);
		is.close();
		final String schema_string = JUtils.newString(shema_bytes);

		// L.d("schema_string", schema_string);

		final FilePointers pointers = Json.deserializeFromString(FilePointers.class, schema_string);

		final R3ArrayCompressionSchema schema = new R3ArrayCompressionSchema(pointers);

		return schema;
	}

	private long readLong (final java.io.InputStream jis) throws IOException {
		final byte[] tmp = new byte[8];
		jis.read(tmp);
		return this.byteArrayToLong(tmp);
	}

	private long byteArrayToLong (final byte[] tmp) {
		final long b7 = tmp[0] << 8 * 7;
		final long b6 = tmp[1] << 8 * 6;
		final long b5 = tmp[2] << 8 * 5;
		final long b4 = tmp[3] << 8 * 4;
		final long b3 = tmp[4] << 8 * 3;
		final long b2 = tmp[5] << 8 * 2;
		final long b1 = tmp[6] << 8 * 1;
		final long b0 = tmp[7] << 8 * 0;
		return b0 | b1 | b2 | b3 | b4 | b5 | b6 | b7;
	}

}
