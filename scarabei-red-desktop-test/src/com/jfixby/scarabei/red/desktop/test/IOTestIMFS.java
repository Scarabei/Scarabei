
package com.jfixby.scarabei.red.desktop.test;

import java.io.IOException;
import java.util.Random;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.debug.DebugTimer;
import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.LocalFileSystem;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.red.filesystem.virtual.InMemoryFileSystem;

public class IOTestIMFS {

	private static final long MB = 1024 * 1024;

	public static void main (final String[] args) throws IOException {
		ScarabeiDesktop.deploy();

		final InMemoryFileSystem IMFS = new InMemoryFileSystem();

		final File ssdFolder = LocalFileSystem.ApplicationHome().child("test");
		final File imfFolder = IMFS.ROOT().child("test");

		final File testFolder = ssdFolder;

		final DebugTimer testTimer = Debug.newTimer();
		double total = 0;

		final int NUMBER_OF_TESTS = 4;

		for (int i = 0; i < NUMBER_OF_TESTS; i++) {

			final byte[] TEST_DATA = newTestData(i, NUMBER_OF_TESTS);
// final double testSize = FloatMath.roundToDigit(TEST_DATA.length * 1d / MB, 8);
			String msg;
			if (TEST_DATA.length <= MB) {
				msg = TEST_DATA.length + " bytes";
			} else {
				msg = TEST_DATA.length / MB + " Mb";
			}
// L.d("file size", msg);
			testTimer.reset();
			testFolder.child("file")//
				.writeBytes(TEST_DATA);
			L.d("test " + i + ", write, size, " + msg + ", time, " + testTimer.getTime());
			total = total + testTimer.getTime();
			testTimer.reset();
			testFolder.child("file")//
				.readBytes();
			L.d("test " + i + ", read, size, " + msg + ", time, " + testTimer.getTime());
			total = total + testTimer.getTime();
		}
		L.d("total , , size, , time, " + total);

	}

	private static byte[] newTestData (final int i, final int NUMBER_OF_TESTS) {
		final float param = i * 1f / NUMBER_OF_TESTS;
		final int mb = (int)(MB * 1000 * param + 1);
		if (mb <= 0) {
			L.d("mb", mb);
			L.d("param", param);
		}
		final byte[] data = new byte[mb];
		final Random random = new Random(i);
		random.nextBytes(data);
		return data;
	}

}
