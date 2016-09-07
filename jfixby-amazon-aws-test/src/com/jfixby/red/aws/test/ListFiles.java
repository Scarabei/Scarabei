
package com.jfixby.red.aws.test;

import com.jfixby.amazon.aws.s3.AWSS3FileSystem;
import com.jfixby.amazon.aws.s3.AWSS3FileSystemConfig;
import com.jfixby.red.desktop.DesktopSetup;

public class ListFiles {

	public static void main (final String[] args) {
		DesktopSetup.deploy();

		final AWSS3FileSystemConfig specs = new AWSS3FileSystemConfig();

		specs.setBucketName("jfix.by");

		final AWSS3FileSystem fileSystem = new AWSS3FileSystem(specs);

		fileSystem.ROOT().child("wp-content").child("uploads").listChildren()//
			.print("fileSystem");

	}

}
