package com.jfixby.red.filesystem.archived;

import java.io.IOException;
import java.io.InputStream;

import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.Map;
import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.cmns.api.file.packing.CompressionMethod;
import com.jfixby.cmns.api.file.packing.CompressionSchema;
import com.jfixby.cmns.api.file.packing.FileSystemPackerComponent;
import com.jfixby.cmns.api.file.packing.FileSystemPackingSpecs;
import com.jfixby.cmns.api.file.packing.FileSystemUnpackingSpecs;
import com.jfixby.cmns.api.file.packing.PackedFileSystem;
import com.jfixby.cmns.api.io.OutputStream;
import com.jfixby.cmns.api.util.JUtils;

public class RedFileSystemPacker implements FileSystemPackerComponent {

    @Override
    public FileSystemUnpackingSpecs newUnpackingSpecs() {
	return new RedFileSystemUnpackingSpecs();
    }

    @Override
    public PackedFileSystem unpack(FileSystemUnpackingSpecs unpacking_spec) throws IOException {
	File archive = Debug.checkNull("DataFile", unpacking_spec.getDataFile());
	FileInputStream is = archive.newInputStream();
	InputStream jis = is.toJavaInputStream();
	int schema_name_len = jis.read();
	// L.d("schema_name_len", schema_name_len);
	skip(END_LINE.length(), jis);
	byte[] name_array = new byte[schema_name_len];
	jis.read(name_array);
	String schema_name = JUtils.newString(name_array);

	// L.d("shema_name", schema_name);

	CompressionMethod method = this.findMethod(schema_name);

	if (method == null) {
	    throw new Error("CompressionMethod [" + schema_name + "] not found.");
	}

	skip(END_LINE.length(), jis);

	CompressionSchema schema = method.readSchema(is);
	jis.close();
	PackedFileSystem packed_file_system = new RedPackedFileSystem(schema, archive);

	return packed_file_system;
    }

    private void skip(int k, InputStream jis) throws IOException {
	for (int i = k; i > 0; i--) {
	    jis.read();
	}
    }

    @Override
    public FileSystemPackingSpecs newPackingSpecs() {
	return new RedFileSystemPackingSpecs();
    }

    @Override
    public void pack(FileSystemPackingSpecs packing_spec) throws IOException {
	String schema_name = Debug.checkNull("Schema", packing_spec.getCompressionSchemaName());
	OutputStream os = Debug.checkNull("OutputStream", packing_spec.getOutputStream());
	Iterable<File> input = Debug.checkNull("TargetFolder", packing_spec.listFiles());

	CompressionMethod schema = this.findMethod(schema_name);

	if (schema == null) {
	    throw new Error("CompressionMethod [" + schema_name + "] not found.");
	}

	java.io.OutputStream jos = os.toJavaOutputStream();
	byte schema_len = (byte) schema_name.length();
	jos.write(schema_len);
	endLine(jos);

	byte[] bytes = schema_name.getBytes();
	Debug.checkTrue(bytes.length == schema_len);
	jos.write(bytes);
	endLine(jos);

	schema.pack(input, os);
	os.flush();
    }

    public static final String END_LINE = "#";// " ←\n"

    public static void endLine(java.io.OutputStream jos) throws IOException {
	jos.write(END_LINE.getBytes());
    }

    private CompressionMethod findMethod(String schema_name) {
	return methods.get(schema_name);
    }

    @Override
    public void installCompressionMethod(CompressionMethod schema) {
	String schema_name = schema.getName();
	CompressionMethod schema_stored = methods.get(schema_name);
	if (schema_stored != null) {
	    throw new Error("CompressionMethod [" + schema_name + "] is already installed: " + schema_stored);
	}
	methods.put(schema_name, schema);
    }

    final Map<String, CompressionMethod> methods = Collections.newMap();
}
