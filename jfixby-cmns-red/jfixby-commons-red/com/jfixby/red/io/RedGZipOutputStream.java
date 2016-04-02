package com.jfixby.red.io;

import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import com.jfixby.cmns.api.io.GZipOutputStream;

public class RedGZipOutputStream extends AbstractRedOutputStream implements GZipOutputStream {

    public RedGZipOutputStream(com.jfixby.cmns.api.io.OutputStream os) throws IOException {
	super(new GZIPOutputStream(os.toJavaOutputStream()));
    }

}
