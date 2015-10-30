package com.jfixby.red.filesystem.cache;

import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.filesystem.File;
import com.jfixby.cmns.api.filesystem.cache.FileCacheComponent;
import com.jfixby.cmns.api.filesystem.cache.TempFolder;

public class RedFileCache implements FileCacheComponent {

	public RedFileCache() {
	}

	@Override
	public TempFolder createTempFolder(File where) {
		JUtils.checkNull("where", where);
		where.checkExists();
		where.checkIsFolder();
		String tmp = newTempFolderName(where);
		return new RedTempFolder(where, tmp);
	}

	static private String newTempFolderName(File where) {
		String name;
		File candidate;
		do {
			name = "tmp-" + System.currentTimeMillis();
			candidate = where.child(name);
		} while (candidate.exists());

		return name;
	}
}
