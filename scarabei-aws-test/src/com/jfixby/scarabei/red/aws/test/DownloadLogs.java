
package com.jfixby.scarabei.red.aws.test;

import java.io.IOException;

import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.LocalFileSystem;
import com.jfixby.scarabei.aws.api.AWS;
import com.jfixby.scarabei.aws.api.S3;
import com.jfixby.scarabei.aws.api.S3FileSystem;
import com.jfixby.scarabei.aws.api.S3FileSystemConfig;

public class DownloadLogs {

	public static void main (final String[] args) throws IOException {
		ScarabeiDesktop.deploy();

		AWS.installComponent("com.jfixby.amazon.aws.RedAWS");
		final S3 s3 = AWS.getS3();
		final S3FileSystemConfig specs = s3.newFileSystemConfig();
		specs.setBucketName("log.jfixby.com");//
		final S3FileSystem S3 = s3.newFileSystem(specs);

		final File remote = S3.ROOT();

		final File local = LocalFileSystem.ApplicationHome().child("logs");

		S3.copyFolderContentsToFolder(remote, local);

		local.listDirectChildren().print("output direct");

		local.listAllChildren().print("output all");
		remote.clearFolder();

	}

}
