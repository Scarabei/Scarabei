
package com.jfixby.scarabei.red.net.http;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.file.FolderSupportingIndex;
import com.jfixby.scarabei.api.net.http.HttpURL;

public class HttpFolderDescriptorCache {

	long max_cache_size = 100;
	final Map<Object, FolderSupportingIndex> cache = Collections.newMap();

	public FolderSupportingIndex get (final HttpURL key) {
// this.cache.print("key");

		final FolderSupportingIndex result = this.cache.get(key);
		if (result == null) {

		}
		return result;

	}

	public void put (final HttpURL key, final FolderSupportingIndex info) {
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
