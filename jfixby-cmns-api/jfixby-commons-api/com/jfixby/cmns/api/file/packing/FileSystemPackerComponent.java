package com.jfixby.cmns.api.file.packing;

import java.io.IOException;

public interface FileSystemPackerComponent {

	FileSystemUnpackingSpecs newUnpackingSpecs();

	void unpack(FileSystemUnpackingSpecs unpacking_spec) throws IOException;

	FileSystemPackingSpecs newPackingSpecs();

	void pack(FileSystemPackingSpecs packing_spec) throws IOException;

	void installCompressionSchema(CompressionSchema schema);

}
