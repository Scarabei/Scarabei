
package com.jfixby.red.filesystem.cache;

import java.io.IOException;

import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.LocalFileSystem;
import com.jfixby.cmns.api.file.cache.FileCacheComponent;
import com.jfixby.cmns.api.file.cache.TempFolder;

public class RedFileCache implements FileCacheComponent {

	public RedFileCache () {
	}

	@Override
	public TempFolder createTempFolder (final File where) throws IOException {
		Debug.checkNull("where", where);
		where.checkExists();
		where.checkIsFolder();
		final String tmp = newTempFolderName(where);
		return new RedTempFolder(where, tmp);
	}

	static private String newTempFolderName (final File where) throws IOException {
		String name;
		File candidate;
		do {
			name = "tmp-" + System.currentTimeMillis();
			candidate = where.child(name);
		} while (candidate.exists());

		return name;
	}

	@Override
	public TempFolder createTempFolder () throws IOException {
		return this.createTempFolder(LocalFileSystem.ApplicationHome());
	}
}
