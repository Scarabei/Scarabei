
package com.jfixby.scarabei.red.aws.test;

import java.io.IOException;

import com.jfixby.scarabei.api.desktop.DesktopSetup;
import com.jfixby.scarabei.aws.api.AWS;
import com.jfixby.scarabei.aws.api.S3;
import com.jfixby.scarabei.aws.api.S3FileSystem;
import com.jfixby.scarabei.aws.api.S3FileSystemConfig;

public class ListFiles {

	public static void main (final String[] args) throws IOException {
		DesktopSetup.deploy();

		DesktopSetup.deploy();

		AWS.installComponent("com.jfixby.amazon.aws.RedAWS");
		final S3 s3 = AWS.getS3();
		final S3FileSystemConfig specs = s3.newFileSystemConfig();
		specs.setBucketName("com.red-triplane.assets");//
		final S3FileSystem fileSystem = s3.newFileSystem(specs);

		fileSystem.ROOT().child("wp-content").child("uploads").child("2011").listDirectChildren()//
			.print("wp-content" + " direct");
		fileSystem.ROOT().listDirectChildren()//
			.print("root" + " direct");

		fileSystem.ROOT().child("wp-content").child("uploads").child("2011").listAllChildren()//
			.print("wp-content" + " all");
		fileSystem.ROOT().listAllChildren()//
			.print("root" + " all");

	}

}
