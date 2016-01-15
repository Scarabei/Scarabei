package com.jfixby.cmns.api.io;

public interface U_StreamPipeProgressListener {

	U_StreamPipeProgressListener NULL = new U_StreamPipeProgressListener() {

		@Override
		public void begin(int available) {
		}

		@Override
		public void update(int available) {
		}

		@Override
		public void end() {
		}
	};

	void begin(int total);

	void update(int progress);

	void end();

}
