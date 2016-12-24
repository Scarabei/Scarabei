
package com.jfixby.scarabei.adopted.gdx.atlas;

import java.io.IOException;

import com.jfixby.scarabei.adopted.gdx.fs.ToGdxFileAdaptor;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.util.JUtils;
import com.jfixby.scarabei.api.util.StateSwitcher;

public class FokkerAtlas {
	private CompressedGdxTextureAtlas gdx_atlas;

	private final StateSwitcher<ATLAS_STATE> state;

	private final File gdx_atlas_file;

	public FokkerAtlas (final File gdx_atlas_file) {
		this.state = JUtils.newStateSwitcher(ATLAS_STATE.NOT_LOADED);
		this.gdx_atlas_file = Debug.checkNull("gdx_atlas_file", gdx_atlas_file);
	}

	private ATLAS_LOAD_MODE mode = ATLAS_LOAD_MODE.MERGED_ALPHA_CHANNEL;

	public void setLoadMode (final ATLAS_LOAD_MODE mode) {
		if (this.state.currentState() != ATLAS_STATE.NOT_LOADED) {
			Err.reportError("ATLAS_LOAD_MODE can be changed only for unloaded atlas");
		}
		this.mode = Debug.checkNull("mode", mode);
	}

	public ATLAS_LOAD_MODE getLoadMode () {
		return this.mode;
	}

	public void load (final boolean loadAlphaTextures) throws IOException {
		this.state.expectState(ATLAS_STATE.NOT_LOADED);
		final ToGdxFileAdaptor adaptor = new ToGdxFileAdaptor(this.gdx_atlas_file);
		final CompressedGdxTextureAtlasData atlas_data = new CompressedGdxTextureAtlasData(adaptor, adaptor.parent(), false);
		this.gdx_atlas = new CompressedGdxTextureAtlas(atlas_data, true);

		if (this.mode == ATLAS_LOAD_MODE.MERGED_ALPHA_CHANNEL) {
			// Err.reportError("Mode is not supported yet: " + mode);
			// Err.reportError("Mode is not supported yet: " + mode);
		}
		if (this.mode == ATLAS_LOAD_MODE.SECOND_ALPHA_TEXTURE_SHADER) {
			// Err.reportError("Mode is not supported yet: " + mode);

		}
		if (this.mode == ATLAS_LOAD_MODE.FUXIA_ALPHA_COLOR_SHADER) {
			// Err.reportError("Mode is not supported yet: " + mode);
		}

		this.state.switchState(ATLAS_STATE.LOADED);
	}

	public CompressedGdxTextureAtlas getGdxAtlas () {
		return this.gdx_atlas;
	}

	// private void loadMergedAlphaChannel(AlphaPages alphaPages) {
	// List<AtlasTexturePage> gdxPagesList = Collections.newList();
	// ObjectSet<AtlasTexturePage> gdxTextures = gdx_atlas.getTextures();
	// gdxPagesList.addAll(gdxTextures);
	// gdxPagesList.print("gdxPagesList");
	// //
	//
	// for (int i = 0; i < gdxPagesList.size(); i++) {
	// AtlasTexturePage container = gdxPagesList.getElementAt(i);
	// final Texture oldGdxTexture = container.getTexture();
	//
	// final Texture newGdxTexture = newTexture(oldGdxTexture, container,
	// alphaPages);
	// oldGdxTexture.dispose();
	// container.setTexture(newGdxTexture);
	//
	// fixRegions(oldGdxTexture, newGdxTexture, gdx_atlas);
	//
	// }
	// }
	//
	// private Texture newTexture(Texture texture, AtlasTexturePage container,
	// AlphaPages alphaPages) {
	//
	// ETC1Data etc1Data = new ETC1Data(container.getTextureFile());
	// Pixmap etc1Pixmap = ETC1.decodeImage(etc1Data, Format.RGB888);
	// final float W = texture.getWidth();
	// final float H = texture.getHeight();
	// String name = container.getTextureFile().name();
	// AlphaPage alphaPage = alphaPages.findAlphaPage(name);
	// alphaPage.checkValid(name);
	// alphaPage.checkValid((int) W, (int) H);
	// Pixmap mergedPixmap = new Pixmap((int) W, (int) H, Format.RGBA8888);
	// // mergedPixmap.setColor(0x0000ff00);
	// // mergedPixmap.fill();
	// // mergedPixmap.drawPixmap(etc1Pixmap, 0, 0);
	// Pixmap.setBlending(Pixmap.Blending.None);
	// // mergedPixmap.setBlending(Blending.SourceOver);
	// for (int x = 0; x < W; x++) {
	// for (int y = 0; y < H; y++) {
	// final float alpha = alphaPage.getAlphaValue(x, y) * 1f + 0;
	// final int color_int = etc1Pixmap.getPixel(x, y);
	// Color color = new Color(color_int);
	// color.a = alpha * 1 + 0 * 0.5f;
	// mergedPixmap.setColor(color);
	// mergedPixmap.drawPixel(x, y);
	// }
	// }
	// L.d();
	//
	// final Texture newTexture = new Texture(mergedPixmap);
	// etc1Pixmap.dispose();
	// mergedPixmap.dispose();
	// texture.dispose();
	// return newTexture;
	// }
	//
	// static private void fixRegions(Texture oldGdxTexture, Texture
	// newGdxTexture, CompressedGdxTextureAtlas gdx_atlas) {
	// Array<AtlasRegion> regions = gdx_atlas.getRegions();
	// for (int i = 0; i < regions.size; i++) {
	// AtlasRegion region = regions.get(i);
	// Texture regionTexture = region.getTexture();
	// if (regionTexture == oldGdxTexture) {
	// region.setTexture(newGdxTexture);
	// }
	// }
	// }

}
