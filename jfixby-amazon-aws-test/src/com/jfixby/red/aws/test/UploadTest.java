
package com.jfixby.red.aws.test;

import java.io.IOException;

import com.jfixby.amazon.aws.s3.AWSS3FileSystem;
import com.jfixby.amazon.aws.s3.AWSS3FileSystemConfig;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.LocalFileSystem;
import com.jfixby.cmns.api.log.L;
import com.jfixby.red.desktop.DesktopSetup;

public class UploadTest {

	public static void main (final String[] args) throws IOException {
		DesktopSetup.deploy();

		final AWSS3FileSystemConfig specs = new AWSS3FileSystemConfig();
		specs.setBucketName("amzfs");//
		final AWSS3FileSystem S3 = new AWSS3FileSystem(specs);
		final File remote = S3.ROOT();

		final File local = LocalFileSystem.ApplicationHome().child("input");

		S3.copyFolderContentsToFolder(local, remote);

		remote.listDirectChildren().print("remote");

		L.d("remote.png", remote.child("sprite2.png").calculateHash());
		L.d("sprite2.png", local.child("sprite2.png").calculateHash());

	}

}
