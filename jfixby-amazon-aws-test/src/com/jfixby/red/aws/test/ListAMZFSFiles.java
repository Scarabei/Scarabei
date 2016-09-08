
package com.jfixby.red.aws.test;

import com.jfixby.amazon.aws.s3.AWSS3FileSystem;
import com.jfixby.amazon.aws.s3.AWSS3FileSystemConfig;
import com.jfixby.red.desktop.DesktopSetup;

public class ListAMZFSFiles {

	public static void main (final String[] args) {
		DesktopSetup.deploy();

		final AWSS3FileSystemConfig specs = new AWSS3FileSystemConfig();

// specs.setBucketName("jfix.by");// amzfs
		specs.setBucketName("amzfs");// amzfs

		final AWSS3FileSystem fileSystem = new AWSS3FileSystem(specs);

		fileSystem.ROOT().listDirectChildren()//
			.print("root" + " direct");

		fileSystem.ROOT().listAllChildren()//
			.print("root" + " all");

	}

}
