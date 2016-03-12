package com.jfixby.red.image.argb;

import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.Map;
import com.jfixby.cmns.api.image.argb.ChannelsList;

public class RedAlphaPages implements ChannelsList {

    @Override
    public String toString() {
	return "Pages [pages=" + pages + "]";
    }

    final Map<String, RedAlphaPage> pages = Collections.newMap();

    public void add(RedAlphaPage newPage) {
	pages.put(newPage.getName(), newPage);
    }

    public int size() {
	return pages.size();
    }

    public RedAlphaPage get(int i) {
	return pages.getValueAt(i);
    }

    public RedAlphaPage findAlphaPage(String name) {
	return pages.get(name);
    }

}
