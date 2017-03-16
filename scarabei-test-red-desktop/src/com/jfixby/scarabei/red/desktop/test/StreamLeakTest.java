
package com.jfixby.scarabei.red.desktop.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.io.IO;
import com.jfixby.scarabei.api.io.InputStream;
import com.jfixby.scarabei.api.java.gc.GCFisher;

public class StreamLeakTest {

	public static void main (final String[] args) throws IOException {
		ScarabeiDesktop.deploy();

		while (true) {
			final InputStream bys = IO.newInputStream( () -> new ByteArrayInputStream(new byte[1024 * 1024 * 20]));
			bys.open();
			GCFisher.throwBait(1024 * 1024, false);
		}

	}

}
