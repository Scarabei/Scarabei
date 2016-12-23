
package com.jfixby.red.desktop.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.jfixby.cmns.api.desktop.DesktopSetup;
import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.io.InputStream;
import com.jfixby.cmns.api.java.gc.GCFisher;

public class StreamLeakTest {

	public static void main (final String[] args) throws IOException {
		DesktopSetup.deploy();

		while (true) {
			final InputStream bys = IO.newInputStream( () -> new ByteArrayInputStream(new byte[1024 * 1024 * 20]));
			bys.open();
			GCFisher.throwBait(1024 * 1024, false);
		}

	}

}
