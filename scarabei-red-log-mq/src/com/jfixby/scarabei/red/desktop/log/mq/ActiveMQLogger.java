
package com.jfixby.scarabei.red.desktop.log.mq;

import com.jfixby.scarabei.api.log.LoggerComponent;
import com.jfixby.scarabei.api.log.MESSAGE_MARKER;
import com.jfixby.scarabei.red.log.AbstractLogger;

public class ActiveMQLogger extends AbstractLogger implements LoggerComponent {
	private MQPipe mq_pipe;

	@Override
	public String arrayToString (final int indent, final Object[] array) {

		final String canonocal_name = array.getClass().getCanonicalName();
		final int n = array.length;
		if (n == 0) {
			return canonocal_name;
			// return "[]";
		}

		final String canonical = canonocal_name.substring(0, canonocal_name.length() - 1) + n + "]\n";
		// String canonical = "Array";
		String t = canonical;
		final String indent_str = this.indent(indent);
		for (int i = 0; i < n; i++) {
			t = t + indent_str + "(" + i + ") " + array[i] + "\n";
		}
		return t;
	}

	@Override
	public String toString (final Object[] array) {
		return this.arrayToString(0, array);
	}

	public void init (final String url, final String logger_box, final String mq_server_file_password,
		final long MESSAGE_EXPIRE_PERIOD) {
		this.mq_pipe = new MQPipe();
		this.mq_pipe.init(url, logger_box, mq_server_file_password, MESSAGE_EXPIRE_PERIOD);
	}

	public void start () {
		this.mq_pipe.start();
	}

	@Override
	public void logLine (final MESSAGE_MARKER marker, final Object string) {
		this.mq_pipe.logLine(marker, string);
	}

	@Override
	public void logAppend (final MESSAGE_MARKER marker, final Object string) {
		this.mq_pipe.logAppend(marker, string);
	}

	@Override
	public void logLine (final MESSAGE_MARKER marker) {
		this.mq_pipe.logLine(marker);
	}

	@Override
	public void logAppend (final MESSAGE_MARKER marker) {
		this.mq_pipe.logAppend(marker);
	}
}
