
package com.jfixby.red.filesystem.http.fs;

import java.io.IOException;

import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.debug.DebugTimer;
import com.jfixby.cmns.api.file.FolderSupportingIndex;
import com.jfixby.cmns.api.io.Buffer;
import com.jfixby.cmns.api.io.BufferInputStream;
import com.jfixby.cmns.api.io.GZipInputStream;
import com.jfixby.cmns.api.io.GZipOutputStream;
import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.io.OutputStream;
import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.net.http.Http;
import com.jfixby.cmns.api.net.http.HttpConnection;
import com.jfixby.cmns.api.net.http.HttpConnectionInputStream;
import com.jfixby.cmns.api.net.http.HttpURL;

public class HTTPOperator {

	private static String url (final HttpURL url) {
		return url.getURLString();
	}

	public static ByteArray readFile (final HttpURL url) throws IOException {

		L.d("FETCHING: " + url);
		final HttpConnection conn = Http.newConnection(url);
		final DebugTimer timer = Debug.newTimer();
		timer.reset();

		conn.open();
		final HttpConnectionInputStream is = conn.getInputStream();
		is.open();
		final ByteArray data = is.readAll();
		is.close();
		conn.close();

		timer.printTime("          ");

		return data;

	}

	public static void encode (final FolderSupportingIndex desc, final OutputStream os) throws IOException {
		final GZipOutputStream gzip = IO.newGZipStream(os);
		gzip.open();
		IO.serialize(desc, gzip);
		gzip.close();
	}

	public static FolderSupportingIndex decode (final ByteArray data) throws IOException {

// final String raw_json = JUtils.newString(data);
// final HttpFolderDescriptor desc = Json.deserializeFromString(HttpFolderDescriptor.class, raw_json);

		final Buffer buffer = IO.newBuffer(data);
		final BufferInputStream is = IO.newBufferInputStream(buffer);
		final GZipInputStream gzip = IO.newGZipStream(is);
		is.open();
		gzip.open();
		final FolderSupportingIndex desc = IO.deserialize(FolderSupportingIndex.class, gzip);
		gzip.close();
		is.close();
		return desc;
	}

}
