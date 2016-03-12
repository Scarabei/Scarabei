package com.jfixby.red.image.argb;

import com.jfixby.cmns.api.image.argb.ExtractorSpecs;

public class RedAlphaChannelExtractorSpecs implements ExtractorSpecs {

    private boolean useZIPCompression;

    public boolean useZIPCompression() {
	return useZIPCompression;
    }

    @Override
    public void setUseZIPCompression(boolean useZIPCompression) {
	this.useZIPCompression = useZIPCompression;
    }

}
