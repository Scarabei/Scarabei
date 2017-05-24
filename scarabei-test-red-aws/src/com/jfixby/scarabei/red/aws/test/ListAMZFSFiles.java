
package com.jfixby.scarabei.red.aws.test;

import java.io.IOException;

import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.aws.api.s3.S3;
import com.jfixby.scarabei.aws.api.s3.S3Component;
import com.jfixby.scarabei.aws.api.s3.S3FileSystem;
import com.jfixby.scarabei.aws.api.s3.S3FileSystemConfig;
import com.jfixby.scarabei.aws.desktop.s3.DesktopS3;

public class ListAMZFSFiles {

	public static void main (final String[] args) throws IOException {
		ScarabeiDesktop.deploy();

		S3.installComponent(new DesktopS3());
		final S3Component s3 = S3.invoke();
		final S3FileSystemConfig specs = s3.newFileSystemConfig();
		specs.setRegionName("eu-central-1");
		specs.setBucketName("com.red-triplane.assets");//
		final S3FileSystem fileSystem = s3.newFileSystem(specs);

// specs.setBucketName("jfix.by");// amzfs

		fileSystem.ROOT().listDirectChildren()//
			.print("root" + " direct");

		fileSystem.ROOT().listAllChildren()//
			.print("root" + " all");

	}

}
