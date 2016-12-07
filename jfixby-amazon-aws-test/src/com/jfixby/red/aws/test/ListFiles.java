
package com.jfixby.red.aws.test;

import java.io.IOException;

import com.jfixby.amazon.aws.s3.AWSS3FileSystem;
import com.jfixby.amazon.aws.s3.AWSS3FileSystemConfig;
import com.jfixby.cmns.api.desktop.DesktopSetup;

public class ListFiles {

	public static void main (final String[] args) throws IOException {
		DesktopSetup.deploy();

		final AWSS3FileSystemConfig specs = new AWSS3FileSystemConfig();

		specs.setBucketName("jfix.by");// amzfs
// specs.setBucketName("amzfs");// amzfs

		final AWSS3FileSystem fileSystem = new AWSS3FileSystem(specs);

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
