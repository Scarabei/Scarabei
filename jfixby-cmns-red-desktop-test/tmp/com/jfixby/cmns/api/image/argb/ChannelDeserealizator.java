package com.jfixby.cmns.api.image.argb;

import java.io.IOException;

public interface ChannelDeserealizator {

    ChannelsList deserialize(byte[] alphas_bytes, boolean zip) throws IOException;

}
