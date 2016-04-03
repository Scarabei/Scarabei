package com.jfixby.red.filesystem.archived;

import java.io.IOException;

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
	java.io.OutputStream jos = os.toJavaOutputStream();

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

	String shema_string = Json.serializeToString(pointers).toString();
	byte[] shema_data = shema_string.getBytes();

	writeLong(jos, shema_string.length());
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
	writeLong(jos, offset);
	endLine(jos);
	for (FileTag tag : list.tags) {
	    if (tag.file.isFile()) {
		byte[] data = tag.file.readBytes().toArray();
		jos.write(data);
	    }
	}
	jos.flush();
    }

    private void writeLong(java.io.OutputStream jos, long offset) throws IOException {
	byte[] array = longToByteArray(offset);
	jos.write(array);
    }

    public byte[] longToByteArray(long value) {
	return new byte[] { (byte) (value >> 8 * 7), (byte) (value >> 8 * 6), (byte) (value >> 8 * 5),
		(byte) (value >> 8 * 4), (byte) (value >> 8 * 3), (byte) (value >> 8 * 2), (byte) (value >> 8),
		(byte) value };
    }

    public static void endLine(java.io.OutputStream jos) throws IOException {
	jos.write(END_LINE.getBytes());
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

    private void skip(int k, java.io.InputStream jis) throws IOException {
	for (int i = k; i > 0; i--) {
	    jis.read();
	}
    }

    public static final String END_LINE = "#";// " ←\n"

    @Override
    public CompressionSchema readSchema(InputStream jis) throws IOException {
	java.io.InputStream is = jis.toJavaInputStream();

	long schema_len = this.readLong(is);
	L.d("schema_len", schema_len);
	skip(END_LINE.length(), is);

	byte[] shema_bytes = new byte[(int) schema_len];
	is.read(shema_bytes);
	is.close();
	String schema_string = JUtils.newString(shema_bytes);

	// L.d("schema_string", schema_string);

	FilePointers pointers = Json.deserializeFromString(FilePointers.class, schema_string);

	R3ArrayCompressionSchema schema = new R3ArrayCompressionSchema(pointers);

	return schema;
    }

    private long readLong(java.io.InputStream jis) throws IOException {
	byte[] tmp = new byte[8];
	jis.read(tmp);
	return byteArrayToLong(tmp);
    }

    private long byteArrayToLong(byte[] tmp) {
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

}
