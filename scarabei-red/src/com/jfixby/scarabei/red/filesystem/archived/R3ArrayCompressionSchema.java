
package com.jfixby.scarabei.red.filesystem.archived;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FileInputStream;
import com.jfixby.scarabei.api.file.packing.CompressionSchema;
import com.jfixby.scarabei.api.file.packing.FileData;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.util.JUtils;
import com.jfixby.scarabei.api.util.path.RelativePath;

public class R3ArrayCompressionSchema implements CompressionSchema {

	private final FilePointers pointers;
	final Map<RelativePath, FilePointer> registry = Collections.newMap();
	final Map<RelativePath, ArrayList<String>> folders = Collections.newMap();

	public R3ArrayCompressionSchema (final FilePointers pointers) {

		this.pointers = pointers;
		for (int i = 0; i < pointers.list.size(); i++) {
			final FilePointer pointer = pointers.list.get(i);
			final RelativePath key = JUtils.newRelativePath(pointer.path);
			this.registry.put(key, pointer);
			if (key.size() > 0) {
				final RelativePath parent_folder = key.parent();
				ArrayList<String> parent_children_list = this.folders.get(parent_folder);
				if (parent_children_list == null) {
					parent_children_list = new ArrayList<String>();
					this.folders.put(parent_folder, parent_children_list);
				}
				parent_children_list.add(key.getLastStep());
			}
		}
	}

	@Override
	public void print (final String tag) {
		this.registry.print(tag);
	}

	@Override
	public boolean isFile (final RelativePath relativePath) {
		return this.chechNull(this.registry.get(relativePath), relativePath).isFile;
	}

	@Override
	public long lastModified (final RelativePath relativePath) {
		return this.chechNull(this.registry.get(relativePath), relativePath).lastModified;
	}

	@Override
	public boolean isFolder (final RelativePath relativePath) {
		return !this.chechNull(this.registry.get(relativePath), relativePath).isFile;
	}

	@Override
	public Iterable<String> listChildren (final RelativePath relativePath) {
		return this.folders.get(relativePath);
	}

	long header_skip = -1;

	@Override
	public FileData readFileData (final RelativePath relativePath, File archive) throws IOException {
		archive = Debug.checkNull("archive", archive);
		if (this.header_skip == -1) {
			this.setup_header_skip(archive);
		}
		// header_skip = 100;
		final FileInputStream is = archive.newInputStream();
		is.open();
		final InputStream jis = is.toJavaInputStream();
		this.skip(this.header_skip, jis);
		L.d("header_skip", this.header_skip);

		final FilePointer pointer = this.registry.get(relativePath);

		if (pointer == null) {
			throw new IOException("File not found " + relativePath + " in archive " + archive);
		}
		if (!pointer.isFile) {
			throw new IOException("File not found " + relativePath + " in archive " + archive);
		}
		this.skip(pointer.offset, jis);
		final byte[] bytes = new byte[(int)pointer.size];
		jis.read(bytes);

		return new ContentLeaf(JUtils.newByteArray(bytes), pointer);

	}

	private FilePointer chechNull (final FilePointer filePointer, final RelativePath relativePath) {
		if (filePointer != null) {
			return filePointer;
		}
		this.registry.print("Failed to read: " + relativePath);
		Err.reportError("Failed to read: " + relativePath);
		return filePointer;
	}

	public static final String END_LINE = "#";// " ←\n"

	private void setup_header_skip (final File archive) throws IOException {
		this.header_skip = 0;
		final FileInputStream is = archive.newInputStream();
		is.open();
		final InputStream jis = is.toJavaInputStream();

		final int schema_name_len = jis.read();
		this.header_skip = this.header_skip + 1;

		this.skip(END_LINE.length(), jis);
		this.header_skip = this.header_skip + END_LINE.length();
		//
		this.skip(schema_name_len, jis);
		this.header_skip = this.header_skip + schema_name_len;

		this.skip(END_LINE.length(), jis);
		this.header_skip = this.header_skip + END_LINE.length();
		//
		// //
		final long schema_len = this.readLong(jis);
		L.d("schema_len", schema_len);
		// //
		this.header_skip = this.header_skip + 8;
		// // //
		this.skip(END_LINE.length(), jis);
		this.header_skip = this.header_skip + END_LINE.length();
		// //
		this.skip(schema_len, jis);
		this.header_skip = this.header_skip + schema_len;

		this.skip(END_LINE.length(), jis);
		this.header_skip = this.header_skip + END_LINE.length();
		//
		this.header_skip = this.header_skip + "data:".length();

		this.header_skip = this.header_skip + 8;

		this.skip(END_LINE.length(), jis);
		this.header_skip = this.header_skip + END_LINE.length();

		jis.close();
		is.close();

	}

	private long readLong (final InputStream jis) throws IOException {
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

	private void skip (final long k, final InputStream jis) throws IOException {
		for (long i = k; i > 0; i--) {
			jis.read();
		}
	}
}
