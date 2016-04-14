
package com.jfixby.red.filesystem.archived;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.Map;
import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.cmns.api.file.packing.CompressionSchema;
import com.jfixby.cmns.api.file.packing.FileData;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.api.util.path.RelativePath;

public class R3ArrayCompressionSchema implements CompressionSchema {

	private FilePointers pointers;
	final Map<RelativePath, FilePointer> registry = Collections.newMap();
	final Map<RelativePath, ArrayList<String>> folders = Collections.newMap();

	public R3ArrayCompressionSchema (FilePointers pointers) {

		this.pointers = pointers;
		for (int i = 0; i < pointers.list.size(); i++) {
			FilePointer pointer = pointers.list.get(i);
			RelativePath key = JUtils.newRelativePath(pointer.path);
			registry.put(key, pointer);
			if (key.size() > 0) {
				RelativePath parent_folder = key.parent();
				ArrayList<String> parent_children_list = folders.get(parent_folder);
				if (parent_children_list == null) {
					parent_children_list = new ArrayList<String>();
					folders.put(parent_folder, parent_children_list);
				}
				parent_children_list.add(key.getLastStep());
			}
		}
	}

	@Override
	public void print (String tag) {
		registry.print(tag);
	}

	@Override
	public boolean isFile (RelativePath relativePath) {
		return chechNull(registry.get(relativePath), relativePath).isFile;
	}

	@Override
	public long lastModified (RelativePath relativePath) {
		return chechNull(registry.get(relativePath), relativePath).lastModified;
	}

	@Override
	public boolean isFolder (RelativePath relativePath) {
		return !chechNull(registry.get(relativePath), relativePath).isFile;
	}

	@Override
	public Iterable<String> listChildren (RelativePath relativePath) {
		return folders.get(relativePath);
	}

	long header_skip = -1;

	@Override
	public FileData readFileData (RelativePath relativePath, File archive) throws IOException {
		archive = Debug.checkNull("archive", archive);
		if (this.header_skip == -1) {
			setup_header_skip(archive);
		}
		// header_skip = 100;
		FileInputStream is = archive.newInputStream();
		InputStream jis = is.toJavaInputStream();
		skip(header_skip, jis);
		L.d("header_skip", header_skip);

		FilePointer pointer = registry.get(relativePath);

		if (pointer == null) {
			throw new IOException("File not found " + relativePath + " in archive " + archive);
		}
		if (!pointer.isFile) {
			throw new IOException("File not found " + relativePath + " in archive " + archive);
		}
		skip(pointer.offset, jis);
		byte[] bytes = new byte[(int)pointer.size];
		jis.read(bytes);

		return new ContentLeaf(JUtils.newByteArray(bytes), pointer);

	}

	private FilePointer chechNull (FilePointer filePointer, RelativePath relativePath) {
		if (filePointer != null) {
			return filePointer;
		}
		registry.print("Failed to read: " + relativePath);
		throw new Error("Failed to read: " + relativePath);
	}

	public static final String END_LINE = "#";// " ←\n"

	private void setup_header_skip (File archive) throws IOException {
		this.header_skip = 0;
		FileInputStream is = archive.newInputStream();
		InputStream jis = is.toJavaInputStream();

		int schema_name_len = jis.read();
		header_skip = header_skip + 1;

		skip(END_LINE.length(), jis);
		header_skip = header_skip + END_LINE.length();
		//
		skip(schema_name_len, jis);
		header_skip = header_skip + schema_name_len;

		skip(END_LINE.length(), jis);
		header_skip = header_skip + END_LINE.length();
		//
		// //
		long schema_len = readLong(jis);
		L.d("schema_len", schema_len);
		// //
		header_skip = header_skip + 8;
		// // //
		skip(END_LINE.length(), jis);
		header_skip = header_skip + END_LINE.length();
		// //
		skip(schema_len, jis);
		header_skip = header_skip + schema_len;

		skip(END_LINE.length(), jis);
		header_skip = header_skip + END_LINE.length();
		//
		header_skip = header_skip + "data:".length();

		header_skip = header_skip + 8;

		skip(END_LINE.length(), jis);
		header_skip = header_skip + END_LINE.length();

		jis.close();
		is.close();

	}

	private long readLong (InputStream jis) throws IOException {
		byte[] tmp = new byte[8];
		jis.read(tmp);
		return byteArrayToLong(tmp);
	}

	private long byteArrayToLong (byte[] tmp) {
		long b7 = tmp[0] << 8 * 7;
		long b6 = tmp[1] << 8 * 6;
		long b5 = tmp[2] << 8 * 5;
		long b4 = tmp[3] << 8 * 4;
		long b3 = tmp[4] << 8 * 3;
		long b2 = tmp[5] << 8 * 2;
		long b1 = tmp[6] << 8 * 1;
		long b0 = tmp[7] << 8 * 0;
		return b0 | b1 | b2 | b3 | b4 | b5 | b6 | b7;
	}

	private void skip (final long k, InputStream jis) throws IOException {
		for (long i = k; i > 0; i--) {
			jis.read();
		}
	}
}
