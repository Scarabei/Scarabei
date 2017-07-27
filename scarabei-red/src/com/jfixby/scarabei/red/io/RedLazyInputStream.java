
package com.jfixby.scarabei.red.io;

import com.jfixby.scarabei.api.io.InputStream;
import com.jfixby.scarabei.api.io.LazyInputStream;

public class RedLazyInputStream implements LazyInputStream {

	private final InputStream inputStream;

	public RedLazyInputStream (final InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
