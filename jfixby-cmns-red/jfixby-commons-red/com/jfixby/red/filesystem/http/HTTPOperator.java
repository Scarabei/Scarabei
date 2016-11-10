
package com.jfixby.red.filesystem.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.debug.DebugTimer;
import com.jfixby.cmns.api.io.Buffer;
import com.jfixby.cmns.api.io.BufferInputStream;
import com.jfixby.cmns.api.io.GZipInputStream;
import com.jfixby.cmns.api.io.GZipOutputStream;
import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.io.InputStreamOpener;
import com.jfixby.cmns.api.io.OutputStream;
import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.net.http.HttpURL;
import com.jfixby.cmns.api.sys.Sys;
import com.jfixby.red.filesystem.http.descript.HttpFolderDescriptor;

public class HTTPOperator {

	static long open = 0;

// public static HTTPFileInfo getFileInfo (final HttpURL url) {
// Sys.sleep(SPEEP);
// final String urlString = url(url);
//
// final HTTPFileInfo result = new HTTPFileInfo(url);
// try {
// final URL java_url = new URL(urlString);
// final HttpURLConnection conn = (HttpURLConnection)java_url.openConnection();
// open_PP(url);
// final int code = conn.getResponseCode();
// result.setCode(code);
//
// java_url.getPath();
// final long len = conn.getContentLengthLong();
// result.setContentLength(len);
//
// open_MM();
// conn.disconnect();
// } catch (final IOException e) {
// e.printStackTrace();
// }
//
// return result;
// }

	private static String url (final HttpURL url) {
		return url.getURLString();
	}

	private static void open_MM () {
		open--;
// L.d("open", open);
	}

	private static void open_PP (final HttpURL url) {
		open++;
// L.d("open", open + " " + url);

	}

	static long SPEEP = 0;

	public static ByteArray readFile (final HttpURL url) throws IOException {
// Sys.sleep(0);
		final DebugTimer timer = Debug.newTimer();

		Sys.sleep(SPEEP);
		final String urlString = url(url);

		final URL java_url = new URL(urlString);
		final HttpURLConnection conn = (HttpURLConnection)java_url.openConnection();
		open_PP(url);
		timer.reset();
		final int code = conn.getResponseCode();
		timer.printTime("FETCHING: " + url);
		java_url.getPath();
		final long len = conn.getContentLengthLong();

		final InputStreamOpener inputStreamOpener = new InputStreamOpener() {

			@Override
			public InputStream open () throws IOException {
				return conn.getInputStream();
			}
		};
		final com.jfixby.cmns.api.io.InputStream is = IO.newInputStream(inputStreamOpener);
		is.open();
		final ByteArray data = is.readAll();
		is.close();

		open_MM();
		conn.disconnect();
		return data;

	}

	public static void encode (final HttpFolderDescriptor desc, final OutputStream os) throws IOException {
		final GZipOutputStream gzip = IO.newGZipStream(os);
		gzip.open();
		IO.serialize(desc, gzip);
		gzip.close();
	}

	public static HttpFolderDescriptor decode (final ByteArray data) throws IOException {

// final String raw_json = JUtils.newString(data);
// final HttpFolderDescriptor desc = Json.deserializeFromString(HttpFolderDescriptor.class, raw_json);

		final Buffer buffer = IO.newBuffer(data);
		final BufferInputStream is = IO.newBufferInputStream(buffer);
		final GZipInputStream gzip = IO.newGZipStream(is);
		is.open();
		gzip.open();
		final HttpFolderDescriptor desc = IO.deserialize(HttpFolderDescriptor.class, gzip);
		gzip.close();
		is.close();
		return desc;
	}

}
