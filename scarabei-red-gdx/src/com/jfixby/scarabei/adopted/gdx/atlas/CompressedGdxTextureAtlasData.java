package com.jfixby.scarabei.adopted.gdx.atlas;

import static com.badlogic.gdx.graphics.Texture.TextureWrap.ClampToEdge;
import static com.badlogic.gdx.graphics.Texture.TextureWrap.Repeat;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StreamUtils;

public class CompressedGdxTextureAtlasData {
    final Array<GdxAtlasPage> pages = new Array();
    final Array<GdxAtlasRegionData> regions = new Array();

    public CompressedGdxTextureAtlasData(FileHandle packFile, FileHandle imagesDir, boolean flip) {
	BufferedReader reader = new BufferedReader(new InputStreamReader(packFile.read()), 64);
	try {
	    GdxAtlasPage pageImage = null;
	    while (true) {
		String line = reader.readLine();
		if (line == null)
		    break;
		if (line.trim().length() == 0)
		    pageImage = null;
		else if (pageImage == null) {
		    FileHandle file = imagesDir.child(line);

		    float width = 0, height = 0;
		    if (CompressedGdxTextureAtlas.readTuple(reader) == 2) { // size
									    // is
									    // only
									    // optional
			// for an atlas packed
			// with an old
			// TexturePacker.
			width = Integer.parseInt(CompressedGdxTextureAtlas.tuple[0]);
			height = Integer.parseInt(CompressedGdxTextureAtlas.tuple[1]);
			CompressedGdxTextureAtlas.readTuple(reader);
		    }
		    Format format = Format.valueOf(CompressedGdxTextureAtlas.tuple[0]);

		    CompressedGdxTextureAtlas.readTuple(reader);
		    TextureFilter min = TextureFilter.valueOf(CompressedGdxTextureAtlas.tuple[0]);
		    TextureFilter max = TextureFilter.valueOf(CompressedGdxTextureAtlas.tuple[1]);

		    String direction = CompressedGdxTextureAtlas.readValue(reader);
		    TextureWrap repeatX = ClampToEdge;
		    TextureWrap repeatY = ClampToEdge;
		    if (direction.equals("x"))
			repeatX = Repeat;
		    else if (direction.equals("y"))
			repeatY = Repeat;
		    else if (direction.equals("xy")) {
			repeatX = Repeat;
			repeatY = Repeat;
		    }

		    pageImage = new GdxAtlasPage(file, width, height, min.isMipMap(), format, min, max, repeatX,
			    repeatY);
		    pages.add(pageImage);
		} else {
		    boolean rotate = Boolean.valueOf(CompressedGdxTextureAtlas.readValue(reader));

		    CompressedGdxTextureAtlas.readTuple(reader);
		    int left = Integer.parseInt(CompressedGdxTextureAtlas.tuple[0]);
		    int top = Integer.parseInt(CompressedGdxTextureAtlas.tuple[1]);

		    CompressedGdxTextureAtlas.readTuple(reader);
		    int width = Integer.parseInt(CompressedGdxTextureAtlas.tuple[0]);
		    int height = Integer.parseInt(CompressedGdxTextureAtlas.tuple[1]);

		    GdxAtlasRegionData region = new GdxAtlasRegionData();
		    region.page = pageImage;
		    region.left = left;
		    region.top = top;
		    region.width = width;
		    region.height = height;
		    region.name = line;
		    region.rotate = rotate;

		    if (CompressedGdxTextureAtlas.readTuple(reader) == 4) { // split
									    // is
									    // optional
			region.splits = new int[] { Integer.parseInt(CompressedGdxTextureAtlas.tuple[0]),
				Integer.parseInt(CompressedGdxTextureAtlas.tuple[1]),
				Integer.parseInt(CompressedGdxTextureAtlas.tuple[2]),
				Integer.parseInt(CompressedGdxTextureAtlas.tuple[3]) };

			if (CompressedGdxTextureAtlas.readTuple(reader) == 4) { // pad
										// is
										// optional,
			    // but only present
			    // with splits
			    region.pads = new int[] { Integer.parseInt(CompressedGdxTextureAtlas.tuple[0]),
				    Integer.parseInt(CompressedGdxTextureAtlas.tuple[1]),
				    Integer.parseInt(CompressedGdxTextureAtlas.tuple[2]),
				    Integer.parseInt(CompressedGdxTextureAtlas.tuple[3]) };

			    CompressedGdxTextureAtlas.readTuple(reader);
			}
		    }

		    region.originalWidth = Integer.parseInt(CompressedGdxTextureAtlas.tuple[0]);
		    region.originalHeight = Integer.parseInt(CompressedGdxTextureAtlas.tuple[1]);

		    CompressedGdxTextureAtlas.readTuple(reader);
		    region.offsetX = Integer.parseInt(CompressedGdxTextureAtlas.tuple[0]);
		    region.offsetY = Integer.parseInt(CompressedGdxTextureAtlas.tuple[1]);

		    region.index = Integer.parseInt(CompressedGdxTextureAtlas.readValue(reader));

		    if (flip)
			region.flip = true;

		    regions.add(region);
		}
	    }
	} catch (Exception ex) {
	    throw new GdxRuntimeException("Error reading pack file: " + packFile, ex);
	} finally {
	    StreamUtils.closeQuietly(reader);
	}

	regions.sort(CompressedGdxTextureAtlas.indexComparator);
    }

    public Array<GdxAtlasPage> getPages() {
	return pages;
    }

    public Array<GdxAtlasRegionData> getRegions() {
	return regions;
    }

//    public GdxAtlasPage findPageByTexture(Texture texture) {
//	for (int i = 0; i < this.pages.size; i++) {
//	    GdxAtlasPage page = this.pages.get(i);
//	    if (page.getTexture() == texture) {
//		return page;
//	    }
//	}
//	Collections.newList(this.pages).print("pages");
//	L.d("Texture", texture);
//	Err.reportError("Texture not found");
//	return null;
//    }
}