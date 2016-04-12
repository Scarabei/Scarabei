
package com.jfixby.cmns.adopted.gdx.atlas.legacy;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class GdxTextureAtlas extends TextureAtlas {

	public GdxTextureAtlas () {
		super();
	}

	public GdxTextureAtlas (final FileHandle packFile, final boolean flip) {
		super(packFile, flip);
	}

	public GdxTextureAtlas (final FileHandle packFile, final FileHandle imagesDir, final boolean flip) {
		super(packFile, imagesDir, flip);
	}

	public GdxTextureAtlas (final FileHandle packFile, final FileHandle imagesDir) {
		super(packFile, imagesDir);
	}

	public GdxTextureAtlas (final FileHandle packFile) {
		super(packFile);
	}

	public GdxTextureAtlas (final String internalPackFile) {
		super(internalPackFile);
	}

	public GdxTextureAtlas (final TextureAtlasData data) {
		super(data);
	}

}
