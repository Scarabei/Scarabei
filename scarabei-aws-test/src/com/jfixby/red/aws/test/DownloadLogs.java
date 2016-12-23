
package com.jfixby.red.aws.test;

import java.io.IOException;

import com.jfixby.cmns.api.desktop.DesktopSetup;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.LocalFileSystem;
import com.jfixby.cmns.aws.api.AWS;
import com.jfixby.cmns.aws.api.S3FileSystemConfig;
import com.jfixby.cmns.aws.api.S3;
import com.jfixby.cmns.aws.api.S3FileSystem;

public class DownloadLogs {

	public static void main (final String[] args) throws IOException {
		DesktopSetup.deploy();

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
