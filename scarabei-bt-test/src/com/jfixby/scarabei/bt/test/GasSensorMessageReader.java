
package com.jfixby.scarabei.bt.test;

import java.io.DataInputStream;
import java.io.IOException;

import javax.microedition.io.Connector;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.debug.Debug;

public class GasSensorMessageReader {

	private final String url;
	private DataInputStream is;

	public GasSensorMessageReader (final GasSensorMessageReaderSpecs specs) {
		this.url = specs.url;
	}

	public void open () throws IOException {
		Debug.checkTrue("is closed", this.is == null);
		this.is = Connector.openDataInputStream(this.url);

	}

	public static String STRING_Message = "~Message№";
	public static String STRING_FromABCboard = "~FromABCboard";
	public static String STRING_HUITA_IN = "~#####>";
	public static String STRING_HUITA_OUT = "<##\n";

	public GasSensorMessage read () throws IOException {
		final GasSensorMessage msg = new GasSensorMessage();
		String b = "";
		boolean valveOpen = false;
		final List<Byte> bytes = Collections.newList();
		final Long globalCounter = null;
		while (!b.endsWith(STRING_HUITA_OUT)) {
			final byte bt = this.is.readByte();
			final char c = (char)bt;
			b = b + c;
			if (valveOpen) {
				bytes.add(bt);
			}
			if (b.contains(STRING_HUITA_IN)) {
				valveOpen = true;
			}
			if (globalCounter == null) {
				if (b.contains(STRING_FromABCboard)) {
					final int beginIndex = STRING_Message.length();
					final int endIndex = b.indexOf(STRING_FromABCboard);
					String num = b.substring(beginIndex, endIndex);
// L.d("num", num);
					num = removeNonDigits(num);
// L.d("num", num);
					msg.globalCounter = Integer.parseInt(num);
// Sys.exit();

				}
			}
		}
		bytes.splitAt(bytes.size() - STRING_HUITA_OUT.length());

		final byte[] data = new byte[bytes.size()];
		for (int i = 0; i < bytes.size(); i++) {
			data[i] = bytes.getElementAt(i);
		}
		msg.data = data;
// L.d("data read", b);
// L.d(" bytes", data);
		return msg;

	}

	public static String removeNonDigits (final String str) {
		if (str == null || str.length() == 0) {
			return "";
		}
		return str.replaceAll("[^0-9]+", "");
	}

	private void skip (final int length) throws IOException {
// L.d("skip " + length + ":");
		for (int i = 0; i < length; i++) {
			final int c = this.is.read();
// L.d_appendChars((char)c);
		}
// L.d();
	}

}
