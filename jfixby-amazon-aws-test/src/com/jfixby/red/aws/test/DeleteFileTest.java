
package com.jfixby.red.aws.test;

import java.io.IOException;

import com.jfixby.amazon.aws.s3.AWSS3FileSystem;
import com.jfixby.amazon.aws.s3.AWSS3FileSystemConfig;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.log.L;
import com.jfixby.red.desktop.DesktopSetup;

public class DeleteFileTest {

	public static void main (final String[] args) throws IOException {
		DesktopSetup.deploy();

		final AWSS3FileSystemConfig specs = new AWSS3FileSystemConfig();
		specs.setBucketName("amzfs");//
		final AWSS3FileSystem S3 = new AWSS3FileSystem(specs);

		final File file = S3.ROOT().child("badFile.txt");
		file.writeString("bad file data!");
		L.d("written");
		final String data = file.readToString();
		L.d("data", data);
		file.delete();

	}

}
