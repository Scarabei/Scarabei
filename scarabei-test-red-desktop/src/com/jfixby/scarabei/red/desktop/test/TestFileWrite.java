
package com.jfixby.scarabei.red.desktop.test;

import java.io.IOException;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.LocalFileSystem;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.md5.MD5;

public class TestFileWrite {

	public static void main (final String[] args) throws IOException {
		ScarabeiDesktop.deploy();
		Json.installComponent("com.jfixby.cmns.adopted.gdx.json.RedJson");

		final File testFile = LocalFileSystem.ApplicationHome().child("RW-test.txt");

		String testString = MD5.md5String(System.currentTimeMillis() + "").toString();
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
