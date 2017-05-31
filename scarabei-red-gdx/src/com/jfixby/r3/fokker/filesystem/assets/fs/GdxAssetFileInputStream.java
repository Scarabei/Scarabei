
package com.jfixby.r3.fokker.filesystem.assets.fs;

import com.jfixby.scarabei.api.file.FileInputStream;
import com.jfixby.scarabei.red.io.AbstractRedInputStream;

public class GdxAssetFileInputStream extends AbstractRedInputStream<GdxAssetFileFileInputStreamOperator>
	implements FileInputStream {

	private final GdxAssetFile file;

	public GdxAssetFileInputStream (final GdxAssetFile file) {
		super(new GdxAssetFileFileInputStreamOperator(file));
		this.file = file;
	}

	@Override
	public long getFileSize () {
		return this.file.getSize();
	}

}
//
//
// private static JavaInputStreamOperator is (final GdxAssetFile input_file) throws IOException {
// final FileHandle file = Gdx.files.internal(input_file.getGdxInternalPathString());
// // L.d("decoding", input_file);
// // String dataInBase64 = file.readString();
// // byte[] data = Base64.decode(dataInBase64);
// final ByteArrayInputStream bis = new ByteArrayInputStream(file.readBytes());
// return bis;
// }
// }
