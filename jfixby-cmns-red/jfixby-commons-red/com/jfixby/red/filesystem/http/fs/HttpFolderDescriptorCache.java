
package com.jfixby.red.filesystem.http.fs;

import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.Map;
import com.jfixby.cmns.api.net.http.HttpURL;
import com.jfixby.red.filesystem.http.descript.HttpFolderDescriptor;

public class HttpFolderDescriptorCache {

	long max_cache_size = 100;
	final Map<Object, HttpFolderDescriptor> cache = Collections.newMap();

	public HttpFolderDescriptor get (final HttpURL key) {
// this.cache.print("key");

		final HttpFolderDescriptor result = this.cache.get(key);
		if (result == null) {

		}
		return result;

	}

	public void put (final HttpURL key, final HttpFolderDescriptor info) {
		this.cache.put(key, info);

		if (this.cache.size() > this.max_cache_size) {
// this.cache.remove(this.cache.getKeyAt(0));
			this.cache.clear();
		}
	}

	public void setSize (final long cache_size) {
		this.max_cache_size = cache_size;
	}

	public void print () {
		this.cache.print("cache");
	}
}
