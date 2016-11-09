
package com.jfixby.cmns.api.file;

public interface FileConflistResolver {

	public boolean overwrite (File fileToCopy, File existing);

	public static final FileConflistResolver OVERWRITE_ALL = new FileConflistResolver() {
		@Override
		final public boolean overwrite (final File fileToCopy, final File existing) {
			return true;
		}
	};

}
