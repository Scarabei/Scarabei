package com.jfixby.cmns.api.io;

import java.io.IOException;

public interface JavaBitOutputStream {

    void setFrameSize(int frameSize) throws IOException;

    void write(int bits) throws IOException;

    void finalizeStream() throws IOException;

    

}
