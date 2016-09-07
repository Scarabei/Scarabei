
package com.jfixby.amazon.aws.s3;

import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.red.io.AbstractRedInputStream;

public class S3FileInputStream extends AbstractRedInputStream<S3FileFileInputStreamOperator>
	implements FileInputStream {

	private final S3File file;

	public S3FileInputStream (final S3File file) {
		super(new S3FileFileInputStreamOperator(file));
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
