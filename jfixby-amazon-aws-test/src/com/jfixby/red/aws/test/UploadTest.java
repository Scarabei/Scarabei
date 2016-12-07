
package com.jfixby.red.aws.test;

import java.io.IOException;

import com.jfixby.amazon.aws.s3.AWSS3FileSystem;
import com.jfixby.amazon.aws.s3.AWSS3FileSystemConfig;
import com.jfixby.cmns.api.desktop.DesktopSetup;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileHash;
import com.jfixby.cmns.api.file.LocalFileSystem;
import com.jfixby.cmns.api.log.L;

public class UploadTest {

	public static void main (final String[] args) throws IOException {
		DesktopSetup.deploy();

		final AWSS3FileSystemConfig specs = new AWSS3FileSystemConfig();
		specs.setBucketName("com.red-triplane.assets");//
		final AWSS3FileSystem S3 = new AWSS3FileSystem(specs);
		final File remote = S3.ROOT().child("test");

		final File local = LocalFileSystem.ApplicationHome().child("input");

		S3.copyFolderContentsToFolder(local, remote, (from, to) -> {
			try {
				final FileHash hashLocal = from.calculateHash();
				final FileHash hashRemote = to.calculateHash();
				return !hashLocal.equals(hashRemote);
			} catch (final IOException e) {
				e.printStackTrace();
				return false;
			}
		});

		remote.listDirectChildren().print("remote");

		L.d("remote.png", remote.child("sprite2.png").calculateHash());
		L.d("sprite2.png", local.child("sprite2.png").calculateHash());

	}

}
