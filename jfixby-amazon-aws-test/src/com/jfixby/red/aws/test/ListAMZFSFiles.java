
package com.jfixby.red.aws.test;

import java.io.IOException;

import com.jfixby.cmns.api.desktop.DesktopSetup;
import com.jfixby.cmns.aws.api.AWS;
import com.jfixby.cmns.aws.api.FileSystemConfig;
import com.jfixby.cmns.aws.api.S3;
import com.jfixby.cmns.aws.api.S3FileSystem;

public class ListAMZFSFiles {

	public static void main (final String[] args) throws IOException {
		DesktopSetup.deploy();

		AWS.installComponent("com.jfixby.amazon.aws.RedAWS");
		final S3 s3 = AWS.getS3();
		final FileSystemConfig specs = s3.newFileSystemConfig();
		specs.setBucketName("com.red-triplane.assets");//
		final S3FileSystem fileSystem = s3.newFileSystem(specs);

// specs.setBucketName("jfix.by");// amzfs

		fileSystem.ROOT().listDirectChildren()//
			.print("root" + " direct");

		fileSystem.ROOT().listAllChildren()//
			.print("root" + " all");

	}

}
