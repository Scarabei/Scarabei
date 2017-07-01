
package com.jfixby.r3.fokker.fs;

import java.io.Serializable;

public class GdxAssetsFileSystemIndexEntry implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 5618108335997217148L;
	public String path;
	public boolean is_file = false;

	@Override
	public String toString () {
		return "# ./" + this.path;
	};

}
