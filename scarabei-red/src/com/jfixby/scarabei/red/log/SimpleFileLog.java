
package com.jfixby.scarabei.red.log;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.File;

public class SimpleFileLog implements OutputLogStreams {

	private final File outputFile;

	public SimpleFileLog (final File outputFile) {
		this.outputFile = outputFile;
	}

	private final LogStream out = new LogStream() {
		@Override
		public void println (final Object msg) {
			final String tag = "D";
			final String line = SimpleFileLog.this.write(tag, msg);
			System.out.println(line);
		}

	};
	private final LogStream err = new LogStream() {
		@Override
		public void println (final Object msg) {
			final String tag = "E";
			final String line = SimpleFileLog.this.write(tag, msg);
			System.err.println(line);
		}
	};

	private String write (final String tag, final Object msg) {
		final String string = tag + ": " + this.timestamp() + " | " + msg;
		try {
			SimpleFileLog.this.outputFile.writeString(string + "\n", true);
		} catch (final IOException e) {
			e.printStackTrace();

			Err.reportError(e);
		}
		return string;

	}

	final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	{
		this.sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
	}

	final private String timestamp () {
		return this.sdf.format(new Date());
	}

	@Override
	public LogStream out () {
		return this.out;
	}

	@Override
	public LogStream err () {
		return this.err;
	}

}
