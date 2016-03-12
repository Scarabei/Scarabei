package com.jfixby.cmns.api.image.argb;

import com.jfixby.cmns.api.file.File;

public interface ExtractionSettings {

    void setInputFile(File input_png);

    void setNameTag(String name);

    File getInputFile();

    String getNameTag();

}
