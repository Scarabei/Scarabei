
package com.jfixby.scarabei.red.aws.test;

import java.io.IOException;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.debug.DebugTimer;
import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.aws.api.AWS;
import com.jfixby.scarabei.aws.api.S3;
import com.jfixby.scarabei.aws.api.S3FileSystem;
import com.jfixby.scarabei.aws.api.S3FileSystemConfig;

public class ClearBucket {

	public static void main (final String[] args) throws IOException {
		ScarabeiDesktop.deploy();
		AWS.installComponent("com.jfixby.amazon.aws.RedAWS");
		final S3 s3 = AWS.getS3();
		final S3FileSystemConfig specs = s3.newFileSystemConfig();
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
