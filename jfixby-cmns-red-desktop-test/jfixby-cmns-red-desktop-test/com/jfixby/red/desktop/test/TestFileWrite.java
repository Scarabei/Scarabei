
package com.jfixby.red.desktop.test;

import java.io.IOException;

import com.jfixby.cmns.adopted.gdx.json.RedJson;
import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.desktop.DesktopSetup;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.LocalFileSystem;
import com.jfixby.cmns.api.json.Json;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.md5.MD5;

public class TestFileWrite {

	public static void main (final String[] args) throws IOException {
		DesktopSetup.deploy();
		Json.installComponent(new RedJson());

		final File testFile = LocalFileSystem.ApplicationHome().child("RW-test.txt");

		String testString = MD5.md5String(System.currentTimeMillis() + "");
		testString = testString.toLowerCase() + "-" + testString.toUpperCase();
		testFile.writeString(testString);
		testFile.writeString(testString);
		testFile.writeString(testString);

		final String result = testFile.readToString();
		L.d("out", testString);
		L.d(" in", result);
		Debug.checkTrue(result.equals(testString));
	}

}
