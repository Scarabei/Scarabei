
package com.jfixby.scarabei.adopted.gdx.atlas;

import com.jfixby.scarabei.api.file.File;

public class CompressedFokkerAtlas extends FokkerAtlas {

	public static final String ALPHA_CHANNEL_FILE_EXTENTION = ".zipng";

	public CompressedFokkerAtlas (final File gdx_atlas_file) {
		super(gdx_atlas_file);
	}

}
