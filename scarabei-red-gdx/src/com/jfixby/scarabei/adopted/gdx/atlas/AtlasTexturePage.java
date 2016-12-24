package com.jfixby.scarabei.adopted.gdx.atlas;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public class AtlasTexturePage {

    private Texture texture;
    private FileHandle textureFile;
    private Texture alpha_texture;

    public AtlasTexturePage(Texture texture, Texture alpha_texture, FileHandle textureFile) {
	this.textureFile = textureFile;
	this.texture = texture;
	this.alpha_texture = alpha_texture;
    }

    public Texture getTexture() {
	return this.texture;
    }
    
    public Texture getAlphaTexture() {
   	return this.alpha_texture;
       }

    public void setTexture(Texture newGdxTexture) {
	this.texture = newGdxTexture;
    }

    public FileHandle getTextureFile() {
	return textureFile;
    }

}
