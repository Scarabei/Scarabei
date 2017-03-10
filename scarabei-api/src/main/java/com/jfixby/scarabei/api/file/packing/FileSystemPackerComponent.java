
package com.jfixby.scarabei.api.file.packing;

import java.io.IOException;

public interface FileSystemPackerComponent {

	FileSystemUnpackingSpecs newUnpackingSpecs ();

	PackedFileSystem unpack (FileSystemUnpackingSpecs unpacking_spec) throws IOException;

	FileSystemPackingSpecs newPackingSpecs ();

	void pack (FileSystemPackingSpecs packing_spec) throws IOException;

	void installCompressionMethod (CompressionMethod schema);

}
