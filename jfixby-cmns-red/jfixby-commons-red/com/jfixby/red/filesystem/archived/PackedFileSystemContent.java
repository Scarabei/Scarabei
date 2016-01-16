package com.jfixby.red.filesystem.archived;

import java.io.IOException;

import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.packing.CompressionSchema;
import com.jfixby.cmns.api.file.packing.FileData;
import com.jfixby.cmns.api.util.path.RelativePath;

public class PackedFileSystemContent {

	private CompressionSchema schema;
	private File archive;

	public PackedFileSystemContent(CompressionSchema schema, File archive) {
		this.schema = schema;
		this.archive = archive;

	}

	public boolean isFile(RelativePath relativePath) {
		return schema.isFile(relativePath);
	}

	public long lastModified(RelativePath relativePath) {
		return schema.lastModified(relativePath);
	}

	public boolean isFolder(RelativePath relativePath) {
		return schema.isFolder(relativePath);
	}

	public boolean delete(RelativePath relativePath) {
		throw new Error("Not supported (yet?)");
	}

	public boolean exists(RelativePath relativePath) {
		return this.isFile(relativePath) || this.isFolder(relativePath);
	}

	public boolean mkdirs(RelativePath relativePath) {
		throw new Error("Not supported (yet?)");
	}

	public void rename(RelativePath relativePath, String new_name) {
		throw new Error("Not supported (yet?)");
	}

	public Iterable<String> listChildren(RelativePath relativePath) {
		return schema.listChildren(relativePath);
	}

	public FileData createFile(RelativePath relativePath) {
		throw new Error("Not supported (yet?)");
	}

	public FileData getContentLeaf(RelativePath relativePath) throws IOException {
		return schema.readFileData(relativePath, archive);
	}

}
