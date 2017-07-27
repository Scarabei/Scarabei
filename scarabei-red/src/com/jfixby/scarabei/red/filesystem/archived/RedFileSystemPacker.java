
package com.jfixby.scarabei.red.filesystem.archived;

import java.io.IOException;
import java.io.InputStream;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FileInputStream;
import com.jfixby.scarabei.api.file.packing.CompressionMethod;
import com.jfixby.scarabei.api.file.packing.CompressionSchema;
import com.jfixby.scarabei.api.file.packing.FileSystemPackerComponent;
import com.jfixby.scarabei.api.file.packing.FileSystemPackingSpecs;
import com.jfixby.scarabei.api.file.packing.FileSystemUnpackingSpecs;
import com.jfixby.scarabei.api.file.packing.PackedFileSystem;
import com.jfixby.scarabei.api.io.OutputStream;
import com.jfixby.scarabei.api.util.JUtils;

public class RedFileSystemPacker implements FileSystemPackerComponent {

	@Override
	public FileSystemUnpackingSpecs newUnpackingSpecs () {
		return new RedFileSystemUnpackingSpecs();
	}

	@Override
	public PackedFileSystem unpack (final FileSystemUnpackingSpecs unpacking_spec) throws IOException {
		final File archive = Debug.checkNull("DataFile", unpacking_spec.getDataFile());
		final FileInputStream is = archive.newInputStream();
		is.open();
		final InputStream jis = is.toJavaInputStream();
		final int schema_name_len = jis.read();
		// L.d("schema_name_len", schema_name_len);
		this.skip(END_LINE.length(), jis);
		final byte[] name_array = new byte[schema_name_len];
		jis.read(name_array);
		final String schema_name = JUtils.newString(name_array);

		// L.d("shema_name", schema_name);

		final CompressionMethod method = this.findMethod(schema_name);

		if (method == null) {
			Err.reportError("CompressionMethod [" + schema_name + "] not found.");
		}

		this.skip(END_LINE.length(), jis);

		final CompressionSchema schema = method.readSchema(is);
		jis.close();
		final PackedFileSystem packed_file_system = new RedPackedFileSystem(schema, archive);

		return packed_file_system;
	}

	private void skip (final int k, final InputStream jis) throws IOException {
		for (int i = k; i > 0; i--) {
			jis.read();
		}
	}

	@Override
	public FileSystemPackingSpecs newPackingSpecs () {
		return new RedFileSystemPackingSpecs();
	}

	@Override
	public void pack (final FileSystemPackingSpecs packing_spec) throws IOException {
		final String schema_name = Debug.checkNull("Schema", packing_spec.getCompressionSchemaName());
		final OutputStream os = Debug.checkNull("OutputStream", packing_spec.getOutputStream());
		final Iterable<File> input = Debug.checkNull("TargetFolder", packing_spec.listFiles());

		final CompressionMethod schema = this.findMethod(schema_name);

		if (schema == null) {
			Err.reportError("CompressionMethod [" + schema_name + "] not found.");
		}

		final java.io.OutputStream jos = os.toJavaOutputStream();
		final byte schema_len = (byte)schema_name.length();
		jos.write(schema_len);
		endLine(jos);

		final byte[] bytes = schema_name.getBytes();
		Debug.checkTrue(bytes.length == schema_len);
		jos.write(bytes);
		endLine(jos);

		schema.pack(input, os);
		os.flush();
	}

	public static final String END_LINE = "#";// " ←\n"

	public static void endLine (final java.io.OutputStream jos) throws IOException {
		jos.write(END_LINE.getBytes());
	}

	private CompressionMethod findMethod (final String schema_name) {
		return this.methods.get(schema_name);
	}

	@Override
	public void installCompressionMethod (final CompressionMethod schema) {
		final String schema_name = schema.getName();
		final CompressionMethod schema_stored = this.methods.get(schema_name);
		if (schema_stored != null) {
			Err.reportError("CompressionMethod [" + schema_name + "] is already installed: " + schema_stored);
		}
		this.methods.put(schema_name, schema);
	}

	final Map<String, CompressionMethod> methods = Collections.newMap();
}
