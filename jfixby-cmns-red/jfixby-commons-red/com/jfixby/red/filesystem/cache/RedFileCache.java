package com.jfixby.red.filesystem.cache;

import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.cache.FileCacheComponent;
import com.jfixby.cmns.api.file.cache.TempFolder;

public class RedFileCache implements FileCacheComponent {

	public RedFileCache() {
	}

	@Override
	public TempFolder createTempFolder(File where) {
		Debug.checkNull("where", where);
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
