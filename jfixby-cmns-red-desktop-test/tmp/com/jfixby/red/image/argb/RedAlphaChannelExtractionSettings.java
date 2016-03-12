package com.jfixby.red.image.argb;

import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.image.argb.ExtractionSettings;

 class RedAlphaChannelExtractionSettings implements ExtractionSettings {

    private File input_png;
    private String name;

    @Override
    public void setInputFile(File input_png) {
	this.input_png = input_png;
    }

    @Override
    public void setNameTag(String name) {
	this.name = name;
    }

    @Override
    public File getInputFile() {
	return input_png;
    }

    @Override
    public String getNameTag() {
	return name;
    }

}
