package com.jfixby.cmns.api.image.argb;

public interface ChannelsList {

    Channel findAlphaPage(String name);

    int size();

    Channel get(int i);

}
