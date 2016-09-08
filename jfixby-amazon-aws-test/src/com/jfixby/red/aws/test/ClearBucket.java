
package com.jfixby.red.aws.test;

import java.io.IOException;

import com.jfixby.amazon.aws.s3.AWSS3FileSystem;
import com.jfixby.amazon.aws.s3.AWSS3FileSystemConfig;
import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.debug.DebugTimer;
import com.jfixby.cmns.api.file.File;
import com.jfixby.red.desktop.DesktopSetup;

public class ClearBucket {

	public static void main (final String[] args) throws IOException {
		DesktopSetup.deploy();

		final AWSS3FileSystemConfig specs = new AWSS3FileSystemConfig();
		specs.setBucketName("amzfs");//
		final AWSS3FileSystem S3 = new AWSS3FileSystem(specs);
		final DebugTimer timer = Debug.newTimer();
		timer.reset();
		final File remote = S3.ROOT();
		timer.printTime("root");
		timer.reset();
		remote.clearFolder();
		timer.printTime("clear");

	}

}
