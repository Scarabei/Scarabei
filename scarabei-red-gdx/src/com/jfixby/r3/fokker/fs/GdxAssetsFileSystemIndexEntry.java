
package com.jfixby.r3.fokker.fs;

import java.io.Serializable;

public class GdxAssetsFileSystemIndexEntry implements Serializable {
	public String path;
	public boolean is_file = false;

	@Override
	public String toString () {
		return "# ./" + path;
	};

}
