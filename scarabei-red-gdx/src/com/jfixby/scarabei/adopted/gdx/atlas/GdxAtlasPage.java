package com.jfixby.scarabei.adopted.gdx.atlas;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;

public class GdxAtlasPage {
    public final FileHandle textureFile;

    public final float width, height;
    public final boolean useMipMaps;
    public Format format;
    public final TextureFilter minFilter;
    public final TextureFilter magFilter;
    public final TextureWrap uWrap;
    public final TextureWrap vWrap;

    @Override
    public String toString() {
	return "Page [textureFile=" + textureFile + "]";
    }

    public GdxAtlasPage(FileHandle handle, float width, float height, boolean useMipMaps, Format format,
	    TextureFilter minFilter, TextureFilter magFilter, TextureWrap uWrap, TextureWrap vWrap) {
	this.width = width;
	this.height = height;
	this.textureFile = handle;
	this.useMipMaps = useMipMaps;
	this.format = format;
	this.minFilter = minFilter;
	this.magFilter = magFilter;
	this.uWrap = uWrap;
	this.vWrap = vWrap;
    }

}