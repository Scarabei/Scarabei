
package com.jfixby.scarabei.red.net.http;

import java.io.IOException;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.debug.DebugTimer;
import com.jfixby.scarabei.api.file.FolderSupportingIndex;
import com.jfixby.scarabei.api.io.Buffer;
import com.jfixby.scarabei.api.io.BufferInputStream;
import com.jfixby.scarabei.api.io.GZipInputStream;
import com.jfixby.scarabei.api.io.GZipOutputStream;
import com.jfixby.scarabei.api.io.IO;
import com.jfixby.scarabei.api.io.OutputStream;
import com.jfixby.scarabei.api.java.ByteArray;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.net.http.Http;
import com.jfixby.scarabei.api.net.http.HttpConnection;
import com.jfixby.scarabei.api.net.http.HttpConnectionInputStream;
import com.jfixby.scarabei.api.net.http.HttpConnectionSpecs;
import com.jfixby.scarabei.api.net.http.HttpURL;

public class HTTPOperator {

	private static String url (final HttpURL url) {
		return url.getURLString();
	}

	public static ByteArray readFile (final HttpURL url) throws IOException {

		L.d("FETCHING: " + url);
		final HttpConnectionSpecs spec = Http.newConnectionSpecs();
		spec.setDoOutput(false);
		spec.setURL(url);
		spec.setConnectTimeout(3000);
		spec.setReadTimeout(3000);
		final HttpConnection conn = Http.newConnection(spec);

		final DebugTimer timer = Debug.newTimer();
		timer.reset();

		conn.open();
		final HttpConnectionInputStream is = conn.getInputStream();
		is.open();
		IOException t = null;
		ByteArray data = null;
		try {
			data = is.readAll();
		} catch (final IOException e) {
			t = e;
		}
		is.close();
		conn.close();

		timer.printTime("          ");
		if (t != null) {
			throw t;
		}
		return data;

	}

	public static void encode (final FolderSupportingIndex index, final OutputStream os) throws IOException {
		final GZipOutputStream gzip = IO.newGZipStream(os);
		gzip.open();
		IO.serialize(index, gzip);
		gzip.close();
	}

	public static FolderSupportingIndex decode (final ByteArray bytes) throws IOException {

// final String raw_json = JUtils.newString(data);
// final HttpFolderDescriptor desc = Json.deserializeFromString(HttpFolderDescriptor.class, raw_json);

		final Buffer buffer = IO.newBuffer(bytes);
		final BufferInputStream is = IO.newBufferInputStream(buffer);
		final GZipInputStream gzip = IO.newGZipStream(is);
		is.open();
		gzip.open();
		try {

			final ByteArray dat = gzip.readAll();
			final FolderSupportingIndex index = IO.deserialize(FolderSupportingIndex.class, dat);
			return index;
		} catch (final IOException e) {
// L.e(new String(dat.toArray()));
			throw e;
		} finally {
			gzip.close();
			is.close();
		}

	}

}
