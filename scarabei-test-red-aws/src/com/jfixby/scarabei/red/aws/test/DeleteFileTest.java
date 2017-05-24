
package com.jfixby.scarabei.red.aws.test;

import java.io.IOException;

import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.aws.api.s3.S3;
import com.jfixby.scarabei.aws.api.s3.S3Component;
import com.jfixby.scarabei.aws.api.s3.S3FileSystem;
import com.jfixby.scarabei.aws.api.s3.S3FileSystemConfig;
import com.jfixby.scarabei.aws.desktop.s3.DesktopS3;

public class DeleteFileTest {

	public static void main (final String[] args) throws IOException {
		ScarabeiDesktop.deploy();
		S3.installComponent(new DesktopS3());
		final S3Component s3 = S3.invoke();
		final S3FileSystemConfig specs = s3.newFileSystemConfig();
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
