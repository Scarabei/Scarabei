
package com.jfixby.red.aws.test;

import java.io.IOException;

import com.jfixby.amazon.aws.s3.AWSS3FileSystem;
import com.jfixby.amazon.aws.s3.AWSS3FileSystemConfig;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileHash;
import com.jfixby.cmns.api.file.LocalFileSystem;
import com.jfixby.red.desktop.DesktopSetup;

public class DownloadTest {

	public static void main (final String[] args) throws IOException {
		DesktopSetup.deploy();

		final AWSS3FileSystemConfig specs = new AWSS3FileSystemConfig();
		specs.setBucketName("com.red-triplane.assets");//
		final AWSS3FileSystem S3 = new AWSS3FileSystem(specs);
		final File remote = S3.ROOT().child("test");
		;

		final File local = LocalFileSystem.ApplicationHome().child("output");

		S3.copyFolderContentsToFolder(remote, local, (from, to) -> {
			try {
				final FileHash hashLocal = from.calculateHash();
				final FileHash hashRemote = to.calculateHash();
				return !hashLocal.equals(hashRemote);
			} catch (final IOException e) {
				e.printStackTrace();
				return false;
			}
		});

		local.listDirectChildren().print("output direct");

		local.listAllChildren().print("output all");

	}

}
