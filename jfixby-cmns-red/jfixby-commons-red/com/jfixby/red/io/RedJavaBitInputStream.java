package com.jfixby.red.io;

import java.io.InputStream;

import com.jfixby.cmns.api.io.JavaBitInputStream;

public class RedJavaBitInputStream implements JavaBitInputStream {

    final private InputStream is;

    public RedJavaBitInputStream(java.io.InputStream is) {
	this.is = is;
    }

}
