package com.jfixby.cmns.api.image.argb;

import java.io.IOException;

import com.jfixby.cmns.api.file.File;

public interface ChannelExtractor {

    ExtractionResult process(ExtractionSettings settings) throws IOException;

    ExtractionSettings newExtractionSettings();

    byte[] serialize() throws IOException;

    void saveAsPng(File folder) throws IOException;

}
