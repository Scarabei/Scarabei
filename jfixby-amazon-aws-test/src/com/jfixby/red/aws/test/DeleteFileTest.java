
package com.jfixby.red.aws.test;

import java.io.IOException;

import com.jfixby.cmns.api.desktop.DesktopSetup;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.aws.api.AWS;
import com.jfixby.cmns.aws.api.FileSystemConfig;
import com.jfixby.cmns.aws.api.S3;
import com.jfixby.cmns.aws.api.S3FileSystem;

public class DeleteFileTest {

	public static void main (final String[] args) throws IOException {
		DesktopSetup.deploy();
		AWS.installComponent("com.jfixby.amazon.aws.RedAWS");
		final S3 s3 = AWS.getS3();
		final FileSystemConfig specs = s3.newFileSystemConfig();
		specs.setBucketName("amzfs");//
		final S3FileSystem S3 = s3.newFileSystem(specs);

		final File file = S3.ROOT().child("badFile.txt");
		file.writeString("bad file data!");
		L.d("written");
		final String data = file.readToString();
		L.d("data", data);
		file.delete();

	}

}
