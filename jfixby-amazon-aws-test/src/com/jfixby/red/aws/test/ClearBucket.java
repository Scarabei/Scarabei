
package com.jfixby.red.aws.test;

import java.io.IOException;

import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.debug.DebugTimer;
import com.jfixby.cmns.api.desktop.DesktopSetup;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.aws.api.AWS;
import com.jfixby.cmns.aws.api.FileSystemConfig;
import com.jfixby.cmns.aws.api.S3;
import com.jfixby.cmns.aws.api.S3FileSystem;

public class ClearBucket {

	public static void main (final String[] args) throws IOException {
		DesktopSetup.deploy();
		AWS.installComponent("com.jfixby.amazon.aws.RedAWS");
		final S3 s3 = AWS.getS3();
		final FileSystemConfig specs = s3.newFileSystemConfig();
		specs.setBucketName("amzfs");//
		final S3FileSystem fs = s3.newFileSystem(specs);
		final DebugTimer timer = Debug.newTimer();
		timer.reset();
		final File remote = fs.ROOT();
		timer.printTime("root");
		timer.reset();
		remote.clearFolder();
		timer.printTime("clear");

	}

}
