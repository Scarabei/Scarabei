
package com.jfixby.scarabei.red.filesystem.archived;

import java.io.IOException;

import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.packing.CompressionSchema;
import com.jfixby.scarabei.api.file.packing.FileData;
import com.jfixby.scarabei.api.util.path.RelativePath;

public class PackedFileSystemContent {

	private final CompressionSchema schema;
	private final File archive;

	public PackedFileSystemContent (final CompressionSchema schema, final File archive) {
		this.schema = schema;
		this.archive = archive;

	}

	public boolean isFile (final RelativePath relativePath) {
		return this.schema.isFile(relativePath);
	}

	public long lastModified (final RelativePath relativePath) {
		return this.schema.lastModified(relativePath);
	}

	public boolean isFolder (final RelativePath relativePath) {
		return this.schema.isFolder(relativePath);
	}

	public boolean delete (final RelativePath relativePath) {
		Err.reportError("Not supported (yet?)");
		return false;
	}

	public boolean exists (final RelativePath relativePath) {
		return this.isFile(relativePath) || this.isFolder(relativePath);
	}

	public boolean mkdirs (final RelativePath relativePath) {
		Err.reportError("Not supported (yet?)");
		return false;
	}

	public void rename (final RelativePath relativePath, final String new_name) {
		Err.reportError("Not supported (yet?)");
	}

	public Iterable<String> listChildren (final RelativePath relativePath) {
		return this.schema.listChildren(relativePath);
	}

	public FileData createFile (final RelativePath relativePath) {
		Err.reportError("Not supported (yet?)");
		return null;
	}

	public FileData getContentLeaf (final RelativePath relativePath) throws IOException {
		return this.schema.readFileData(relativePath, this.archive);
	}

}
