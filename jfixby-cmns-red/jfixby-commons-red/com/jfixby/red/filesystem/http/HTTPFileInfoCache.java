
package com.jfixby.red.filesystem.http;

import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.Map;
import com.jfixby.cmns.api.net.http.HttpURL;

public class HTTPFileInfoCache {

	int max_cache_size = 100;
	final Map<Object, HTTPFileInfo> cache = Collections.newMap();

	public HTTPFileInfo get (final HttpURL key) {
		// this.cache.print("key");
		final HTTPFileInfo result = this.cache.get(key);
		if (result == null) {
		}
		return result;

	}

	public void put (final HttpURL key, final HTTPFileInfo info) {
		this.cache.put(key, info);
		if (this.cache.size() > this.max_cache_size) {
			this.cache.clear();
		}
	}

}
