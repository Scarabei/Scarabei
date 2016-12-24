
package com.jfixby.scarabei.api.net.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface HttpCallProgress {

	public String readResultAsString (String encoding_string) throws UnsupportedEncodingException;

	public Object readObject () throws IOException, ClassNotFoundException;

}
