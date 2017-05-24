
package com.jfixby.scarabei.red.aws.test;

import java.io.IOException;

import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FileHash;
import com.jfixby.scarabei.api.file.LocalFileSystem;
import com.jfixby.scarabei.aws.api.s3.S3;
import com.jfixby.scarabei.aws.api.s3.S3Component;
import com.jfixby.scarabei.aws.api.s3.S3FileSystem;
import com.jfixby.scarabei.aws.api.s3.S3FileSystemConfig;
import com.jfixby.scarabei.aws.desktop.s3.DesktopS3;

public class DownloadTest {

	public static void main (final String[] args) throws IOException {
		ScarabeiDesktop.deploy();

		S3.installComponent(new DesktopS3());
		final S3Component s3 = S3.invoke();
		final S3FileSystemConfig specs = s3.newFileSystemConfig();
		specs.setBucketName("com.red-triplane.assets");//
		final S3FileSystem S3 = s3.newFileSystem(specs);

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
