package com.jfixby.red.desktop.log.mq;

import com.jfixby.cmns.api.log.LoggerComponent;
import com.jfixby.red.log.AlpaeroLogger;

public class ActiveMQLogger extends AlpaeroLogger implements LoggerComponent {
	private MQPipe mq_pipe;

	@Override
	public String arrayToString(int indent, Object[] array) {

		String canonocal_name = array.getClass().getCanonicalName();
		final int n = array.length;
		if (n == 0) {
			return canonocal_name;
			// return "[]";
		}

		String canonical = canonocal_name.substring(0,
				canonocal_name.length() - 1) + n + "]\n";
		// String canonical = "Array";
		String t = canonical;
		String indent_str = indent(indent);
		for (int i = 0; i < n; i++) {
			t = t + indent_str + "(" + i + ") " + array[i] + "\n";
		}
		return t;
	}

	@Override
	public void System_err_println(Object string) {
		mq_pipe.e(string);
	}

	@Override
	public void System_out_println(Object string) {
		mq_pipe.d(string);
	}

	@Override
	public void System_err_println() {
		mq_pipe.e();
	}

	@Override
	public void System_out_println() {
		mq_pipe.d();
	}

	@Override
	public void System_out_print(Object string) {
		mq_pipe.d(string);
	}

	@Override
	public String toString(Object[] array) {
		return this.arrayToString(0, array);
	}

	public void init(String url, String logger_box,
			String mq_server_file_password, long MESSAGE_EXPIRE_PERIOD) {
		this.mq_pipe = new MQPipe();
		this.mq_pipe.init(url, logger_box, mq_server_file_password,
				MESSAGE_EXPIRE_PERIOD);
	}

	public void start() {
		mq_pipe.start();
	}
}
