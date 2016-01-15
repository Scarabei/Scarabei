package com.jfixby.red.filesystem.archived;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.packing.CompressionMethod;
import com.jfixby.cmns.api.file.packing.CompressionSchema;
import com.jfixby.cmns.api.io.InputStream;
import com.jfixby.cmns.api.io.OutputStream;
import com.jfixby.cmns.api.json.Json;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.api.util.path.RelativePath;

public class R3ArrayCompressionMethod implements CompressionMethod {

	public static final String SCHEMA_NAME = "R3.Array";

	@Override
	public String getName() {
		return SCHEMA_NAME;
	}

	@Override
	public void pack(Iterable<File> input, OutputStream os) throws IOException {
		java.io.OutputStream o = os.toJavaOutputStream();
		ObjectOutputStream jos = new ObjectOutputStream(o);

		TagsList list = new TagsList();

		RelativePath path = JUtils.newRelativePath();

		absrobCollection(input, path, list);
		long offset = 0;
		FilePointers pointers = new FilePointers();
		{
			FilePointer root = new FilePointer();
			root.path = "";
			root.isFile = false;
			pointers.list.add(root);
		}
		for (FileTag tag : list.tags) {
			FilePointer pointer = new FilePointer();
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

		String shema_string = Json.serializeToString(pointers);
		byte[] shema_data = shema_string.getBytes();

		jos.writeLong(shema_string.length());
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
		jos.writeLong(offset);
		endLine(jos);
		for (FileTag tag : list.tags) {
			if (tag.file.isFile()) {
				byte[] data = tag.file.readBytes();
				jos.write(data);
			}
		}
		jos.flush();
	}

	public static void endLine(java.io.OutputStream jos) throws IOException {
		jos.write(" ←\n".getBytes());
	}

	private void absorb(File file, RelativePath path, TagsList list) {
		if (file.isFile()) {
			absorbFile(file, path, list);
		}
		if (file.isFolder()) {
			absorbFolder(file, path, list);
		}
	}

	private void absorbFile(File file, RelativePath path, TagsList list) {
		if (!file.isFile()) {
			throw new Error(file + " is not a file");
		}
		FileTag info = new FileTag(file, path);
		list.addInfo(info);
	}

	private void absorbFolder(File folder, RelativePath path, TagsList list) {
		if (!folder.isFolder()) {
			throw new Error(folder + " is not a folder");
		}
		FileTag info = new FileTag(folder, path);
		list.addInfo(info);
		absrobCollection(folder.listChildren(), path, list);
	}

	private void absrobCollection(Iterable<File> input, RelativePath path, TagsList list) {
		for (File file : input) {
			RelativePath path_i = path.child(file.getName());
			absorb(file, path_i, list);
		}
	}

	private void skip(int i, java.io.InputStream jis) throws IOException {
		for (; i > 0; i--) {
			jis.read();
		}
	}

	@Override
	public CompressionSchema readSchema(InputStream jis) throws IOException {
		java.io.InputStream is = jis.toJavaInputStream();
		java.io.ObjectInputStream jos = new ObjectInputStream(is);
		long schema_len = jos.readLong();
		L.d("schema_len", schema_len);
		skip(5, jos);

		byte[] shema_bytes = new byte[(int) schema_len];
		jos.read(shema_bytes);
		jos.close();
		String schema_string = new String(shema_bytes, "UTF-8");

		L.d("schema_string", schema_string);

		FilePointers pointers = Json.deserializeFromString(FilePointers.class, schema_string);

		R3ArrayCompressionSchema schema = new R3ArrayCompressionSchema(pointers);

		return schema;
	}
}
