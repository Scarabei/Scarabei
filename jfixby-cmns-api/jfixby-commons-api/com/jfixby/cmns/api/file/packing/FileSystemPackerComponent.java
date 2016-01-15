package com.jfixby.cmns.api.file.packing;

import java.io.IOException;

import com.jfixby.cmns.api.file.File;

public interface FileSystemPackerComponent {

	FileSystemUnpackingSpecs newUnpackingSpecs();

	PackedFileSystem unpack(FileSystemUnpackingSpecs unpacking_spec) throws IOException;

	FileSystemPackingSpecs newPackingSpecs();

	void pack(FileSystemPackingSpecs packing_spec) throws IOException;

	void installCompressionMethod(CompressionMethod schema);

}
