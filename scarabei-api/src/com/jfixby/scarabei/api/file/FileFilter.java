
package com.jfixby.scarabei.api.file;

import com.jfixby.scarabei.api.collections.CollectionFilter;

public interface FileFilter extends CollectionFilter<File> {

	public static final FileFilter ALL = new FileFilter() {
		@Override
		final public boolean fits (final File element) {
			return true;
		}
	};

}
