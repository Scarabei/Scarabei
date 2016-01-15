package com.jfixby.red.filesystem.archived;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
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

	public R3ArrayCompressionSchema(FilePointers pointers) {

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
	public void print(String tag) {
		registry.print(tag);
	}

	@Override
	public boolean isFile(RelativePath relativePath) {
		return chechNull(chechNull(registry.get(relativePath), relativePath), relativePath).isFile;
	}

	@Override
	public long lastModified(RelativePath relativePath) {
		return chechNull(chechNull(registry.get(relativePath), relativePath), relativePath).lastModified;
	}

	@Override
	public boolean isFolder(RelativePath relativePath) {
		return !chechNull(registry.get(relativePath), relativePath).isFile;
	}

	@Override
	public Iterable<String> listChildren(RelativePath relativePath) {
		return folders.get(relativePath);
	}

	long header_skip = -1;

	@Override
	public FileData readFileData(RelativePath relativePath, File archive) throws IOException {
		archive = Debug.checkNull("archive", archive);
		if (this.header_skip == -1) {
			setup_header_skip(archive);
		}
		// header_skip = 100;
		FileInputStream is = archive.newInputStream();
		InputStream jis = is.toJavaInputStream();
		skip(header_skip, jis);
		L.d("header_skip", header_skip);
		byte byt = 0;
		while (byt != -1) {
			byt = (byte) jis.read();
			L.d_addChars(new String(new byte[] { byt }, "UTF-8"));
		}

		 return null;

	}

	private FilePointer chechNull(FilePointer filePointer, RelativePath relativePath) {
		if (filePointer != null) {
			return filePointer;
		}
		registry.print("Failed to read: " + relativePath);
		throw new Error("Failed to read: " + relativePath);
	}

	private void setup_header_skip(File archive) throws IOException {
		this.header_skip = 0;
		FileInputStream is = archive.newInputStream();
		InputStream jis = is.toJavaInputStream();
		int schema_name_len = jis.read();
		header_skip = header_skip + 1;

		skip(5, jis);
		header_skip = header_skip + 5;

		byte[] name_array = new byte[schema_name_len];
		jis.read(name_array);
		header_skip = header_skip + schema_name_len;

		skip(5, jis);
		header_skip = header_skip + 5;

		java.io.ObjectInputStream jos = new ObjectInputStream(jis);
		long schema_len = jos.readLong();
		header_skip = header_skip + schema_len;

		skip(5, jos);
		header_skip = header_skip + 5;

		byte[] shema_bytes = new byte[(int) schema_len];
		jos.read(shema_bytes);
		header_skip = header_skip + schema_len;
		// L.d("shema_bytes", new String(shema_bytes));
		jos.close();
		is.close();

	}

	private void skip(final long k, InputStream jis) throws IOException {
		for (long i = k; i > 0; i--) {
			jis.read();
		}
	}
}
