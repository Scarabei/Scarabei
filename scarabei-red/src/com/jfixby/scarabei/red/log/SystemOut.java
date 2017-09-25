
package com.jfixby.scarabei.red.log;

public class SystemOut implements OutputLogStreams {

	private final LogStream out = new LogStream() {
		@Override
		public void println (final Object msg) {
			System.out.println(msg);
		}
	};
	private final LogStream err = new LogStream() {
		@Override
		public void println (final Object msg) {
			System.err.println(msg);
		}
	};

	@Override
	public LogStream out () {
		return this.out;
	}

	@Override
	public LogStream err () {
		return this.err;
	}

}
