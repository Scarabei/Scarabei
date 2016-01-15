package com.jfixby.red.filesystem.archived;

import java.io.IOException;
import java.io.ObjectOutputStream;

import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.Map;
import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.packing.CompressionSchema;
import com.jfixby.cmns.api.file.packing.FileSystemPackerComponent;
import com.jfixby.cmns.api.file.packing.FileSystemPackingSpecs;
import com.jfixby.cmns.api.file.packing.FileSystemUnpackingSpecs;
import com.jfixby.cmns.api.io.OutputStream;

public class RedFileSystemPacker implements FileSystemPackerComponent {

	@Override
	public FileSystemUnpackingSpecs newUnpackingSpecs() {
		return new RedFileSystemUnpackingSpecs();
	}

	@Override
	public void unpack(FileSystemUnpackingSpecs unpacking_spec) throws IOException {
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

		CompressionSchema schema = this.findSchema(schema_name);

		if (schema == null) {
			throw new Error("Schema [" + schema_name + "] not found.");
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

	public static void endLine(java.io.OutputStream jos) throws IOException {
		jos.write(" ←\n".getBytes());
	}

	private CompressionSchema findSchema(String schema_name) {
		return schemas.get(schema_name);
	}

	@Override
	public void installCompressionSchema(CompressionSchema schema) {
		String schema_name = schema.getName();
		CompressionSchema schema_stored = schemas.get(schema_name);
		if (schema_stored != null) {
			throw new Error("Schema [" + schema_name + "] is already installed: " + schema_stored);
		}
		schemas.put(schema_name, schema);
	}

	final Map<String, CompressionSchema> schemas = Collections.newMap();
}
