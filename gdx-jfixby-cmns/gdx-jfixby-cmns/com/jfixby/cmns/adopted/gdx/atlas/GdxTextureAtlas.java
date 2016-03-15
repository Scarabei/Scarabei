package com.jfixby.cmns.adopted.gdx.atlas;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class GdxTextureAtlas extends TextureAtlas {

    public GdxTextureAtlas() {
	super();
    }

    public GdxTextureAtlas(FileHandle packFile, boolean flip) {
	super(packFile, flip);
    }

    public GdxTextureAtlas(FileHandle packFile, FileHandle imagesDir, boolean flip) {
	super(packFile, imagesDir, flip);
    }

    public GdxTextureAtlas(FileHandle packFile, FileHandle imagesDir) {
	super(packFile, imagesDir);
    }

    public GdxTextureAtlas(FileHandle packFile) {
	super(packFile);
    }

    public GdxTextureAtlas(String internalPackFile) {
	super(internalPackFile);
    }

    public GdxTextureAtlas(TextureAtlasData data) {
	super(data);
    }

}
