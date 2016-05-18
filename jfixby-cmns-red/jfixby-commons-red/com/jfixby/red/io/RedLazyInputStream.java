
package com.jfixby.red.io;

import com.jfixby.cmns.api.io.InputStream;
import com.jfixby.cmns.api.io.LazyInputStream;

public class RedLazyInputStream implements LazyInputStream {

	private final InputStream inputStream;

	public RedLazyInputStream (final InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
