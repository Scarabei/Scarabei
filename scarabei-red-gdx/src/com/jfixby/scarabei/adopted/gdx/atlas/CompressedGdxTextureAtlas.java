
package com.jfixby.scarabei.adopted.gdx.atlas;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.zip.GZIPInputStream;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;

/** Loads images from texture atlases created by TexturePacker.<br>
 * <br>
 * A TextureAtlas must be disposed to free up the resources consumed by the backing textures.
 *
 * @author Nathan Sweet */
public class CompressedGdxTextureAtlas implements Disposable {
	static final String[] tuple = new String[4];

	private final ObjectSet<AtlasTexturePage> textures = new ObjectSet(4);
	private final Array<AtlasRegion> regions = new Array<AtlasRegion>();
	private final Array<AtlasRegion> alphaRegions = new Array<AtlasRegion>();

	private boolean loadAlphaTextures = true;

	public boolean loadAlphaTextures () {
		return this.loadAlphaTextures;
	}

	/** Creates an empty atlas to which regions can be added. */
	public CompressedGdxTextureAtlas () {
	}

	/** Loads the specified pack file using {@link FileType#Internal}, using the parent directory of the pack file to find the page
	 * images.
	 * 
	 * @throws IOException */
	public CompressedGdxTextureAtlas (final String internalPackFile) throws IOException {
		this(Gdx.files.internal(internalPackFile));
	}

	/** Loads the specified pack file, using the parent directory of the pack file to find the page images.
	 * 
	 * @throws IOException */
	public CompressedGdxTextureAtlas (final FileHandle packFile) throws IOException {
		this(packFile, packFile.parent());
	}

	public CompressedGdxTextureAtlas (final FileHandle packFile, final boolean flip) throws IOException {
		this(packFile, packFile.parent(), flip);
	}

	public CompressedGdxTextureAtlas (final FileHandle packFile, final FileHandle imagesDir) throws IOException {
		this(packFile, imagesDir, false);
	}

	/** @param flip If true, all regions loaded will be flipped for use with a perspective where 0,0 is the upper left corner.
	 * @throws IOException */
	public CompressedGdxTextureAtlas (final FileHandle packFile, final FileHandle imagesDir, final boolean flip)
		throws IOException {
		this(new CompressedGdxTextureAtlasData(packFile, imagesDir, flip), true);
	}

	/** @param data May be null.
	 * @throws IOException */
	public CompressedGdxTextureAtlas (final CompressedGdxTextureAtlasData data, final boolean loadAlphaTextures)
		throws IOException {
		this.loadAlphaTextures = loadAlphaTextures;
		if (data != null) {
			this.load(data);
		}
	}

	private void load (final CompressedGdxTextureAtlasData data) throws IOException {
		final ObjectMap<GdxAtlasPage, AtlasTexturePage> pageToTexture = new ObjectMap<GdxAtlasPage, AtlasTexturePage>();
		for (final GdxAtlasPage page : data.pages) {
			AtlasTexturePage texture = null;

			texture = this.loadTexture(page, data);

			this.textures.add(texture);
			pageToTexture.put(page, texture);
		}

		for (final GdxAtlasRegionData region : data.regions) {

			final AtlasTexturePage texture = pageToTexture.get(region.page);
			final Texture baseTexture = texture.getTexture();
			final Texture alphaTexture = texture.getAlphaTexture();
			final AtlasRegion atlasRegion = newAtlasRegion(baseTexture, region);
			atlasRegion.setAlphaTexture(alphaTexture);

			this.regions.add(atlasRegion);

			final AtlasRegion atlasAlphaRegion = newAtlasRegion(alphaTexture, region);

			this.alphaRegions.add(atlasAlphaRegion);
		}
	}

	private static AtlasRegion newAtlasRegion (final Texture texture, final GdxAtlasRegionData region) {
		final int width = region.width;
		final int height = region.height;
		final AtlasRegion atlasRegion = new AtlasRegion(texture, region.left, region.top, region.rotate ? height : width,
			region.rotate ? width : height);
		atlasRegion.index = region.index;
		atlasRegion.name = region.name;
		atlasRegion.offsetX = region.offsetX;
		atlasRegion.offsetY = region.offsetY;
		atlasRegion.originalHeight = region.originalHeight;
		atlasRegion.originalWidth = region.originalWidth;
		atlasRegion.rotate = region.rotate;
		atlasRegion.splits = region.splits;
		atlasRegion.pads = region.pads;
		if (region.flip) {
			atlasRegion.flip(false, true);
		}

		return atlasRegion;
	}

	private AtlasTexturePage loadTexture (final GdxAtlasPage page, final CompressedGdxTextureAtlasData data) throws IOException {
		Texture base_texture = null;
		FileHandle compressedAlphaChannelFile = null;
		base_texture = new Texture(page.textureFile, page.format, page.useMipMaps);
		base_texture.setFilter(page.minFilter, page.magFilter);
		base_texture.setWrap(page.uWrap, page.vWrap);

		Texture alpha_texture = base_texture;

		if (this.loadAlphaTextures) {

			final String compressedAlphaChannelFileName = page.textureFile.nameWithoutExtension()
				+ CompressedFokkerAtlas.ALPHA_CHANNEL_FILE_EXTENTION;
			compressedAlphaChannelFile = page.textureFile.parent().child(compressedAlphaChannelFileName);

			final java.io.InputStream javaInputStream = compressedAlphaChannelFile.read();
			final GZIPInputStream zipInputStream = new GZIPInputStream(javaInputStream);
			final Gdx2DPixmap gdxPixmap = new Gdx2DPixmap(zipInputStream, Gdx2DPixmap.GDX2D_FORMAT_ALPHA);
			zipInputStream.close();
			javaInputStream.close();
			final Pixmap pixmap = new Pixmap(gdxPixmap);
			// pixmap.setColor(Color.RED);
			// pixmap.fill();
			alpha_texture = new Texture(pixmap);
			pixmap.dispose();
		}
		final AtlasTexturePage texture = new AtlasTexturePage(base_texture, alpha_texture, compressedAlphaChannelFile);

		return texture;
	}

	// /**
	// * Adds a region to the atlas. The specified texture will be disposed when
	// * the atlas is disposed.
	// */
	// public AtlasRegion addRegion(String name, Texture texture, int x, int y,
	// int width, int height) {
	// textures.add(texture);
	// AtlasRegion region = new AtlasRegion(texture, x, y, width, height);
	// region.name = name;
	// region.originalWidth = width;
	// region.originalHeight = height;
	// region.index = -1;
	// regions.add(region);
	// return region;
	// }
	//
	// /**
	// * Adds a region to the atlas. The texture for the specified region will
	// be
	// * disposed when the atlas is disposed.
	// */
	// public AtlasRegion addRegion(String name, TextureRegion textureRegion) {
	// return addRegion(name, textureRegion.getTexture(),
	// textureRegion.getRegionX(), textureRegion.getRegionY(),
	// textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
	// }

	/** Returns all regions in the atlas. */
	public Array<AtlasRegion> getRegions () {
		return this.regions;
	}

	/** Returns the first region found with the specified name. This method uses string comparison to find the region, so the
	 * result should be cached rather than calling this method multiple times.
	 * 
	 * @return The region, or null. */
	public AtlasRegion findRegion (final String name) {
		for (int i = 0, n = this.regions.size; i < n; i++) {
			if (this.regions.get(i).name.equals(name)) {
				return this.regions.get(i);
			}
		}
		return null;
	}

	/** Returns the first region found with the specified name and index. This method uses string comparison to find the region, so
	 * the result should be cached rather than calling this method multiple times.
	 * 
	 * @return The region, or null. */
	public AtlasRegion findRegion (final String name, final int index) {
		for (int i = 0, n = this.regions.size; i < n; i++) {
			final AtlasRegion region = this.regions.get(i);
			if (!region.name.equals(name)) {
				continue;
			}
			if (region.index != index) {
				continue;
			}
			return region;
		}
		return null;
	}

	/** Returns all regions with the specified name, ordered by smallest to largest {@link AtlasRegion#index index}. This method
	 * uses string comparison to find the regions, so the result should be cached rather than calling this method multiple
	 * times. */
	public Array<AtlasRegion> findRegions (final String name) {
		final Array<AtlasRegion> matched = new Array();
		for (int i = 0, n = this.regions.size; i < n; i++) {
			final AtlasRegion region = this.regions.get(i);
			if (region.name.equals(name)) {
				matched.add(new AtlasRegion(region));
			}
		}
		return matched;
	}

	/** Returns all regions in the atlas as sprites. This method creates a new sprite for each region, so the result should be
	 * stored rather than calling this method multiple times.
	 * 
	 * @see #createSprite(String) */
	public Array<GdxSprite> createSprites () {
		final Array sprites = new Array(this.regions.size);
		for (int i = 0, n = this.regions.size; i < n; i++) {
			sprites.add(this.newSprite(this.regions.get(i)));
		}
		return sprites;
	}

	/** Returns the first region found with the specified name as a sprite. If whitespace was stripped from the region when it was
	 * packed, the sprite is automatically positioned as if whitespace had not been stripped. This method uses string comparison to
	 * find the region and constructs a new sprite, so the result should be cached rather than calling this method multiple times.
	 * 
	 * @return The sprite, or null. */
	public Sprite createSprite (final String name) {
		for (int i = 0, n = this.regions.size; i < n; i++) {
			if (this.regions.get(i).name.equals(name)) {
				return this.newSprite(this.regions.get(i));
			}
		}
		return null;
	}

	/** Returns the first region found with the specified name and index as a sprite. This method uses string comparison to find
	 * the region and constructs a new sprite, so the result should be cached rather than calling this method multiple times.
	 * 
	 * @return The sprite, or null.
	 * @see #createSprite(String) */
	public Sprite createSprite (final String name, final int index) {
		for (int i = 0, n = this.regions.size; i < n; i++) {
			final AtlasRegion region = this.regions.get(i);
			if (!region.name.equals(name)) {
				continue;
			}
			if (region.index != index) {
				continue;
			}
			return this.newSprite(this.regions.get(i));
		}
		return null;
	}

	/** Returns all regions with the specified name as sprites, ordered by smallest to largest {@link AtlasRegion#index index}.
	 * This method uses string comparison to find the regions and constructs new sprites, so the result should be cached rather
	 * than calling this method multiple times.
	 * 
	 * @see #createSprite(String) */
	public Array<Sprite> createSprites (final String name) {
		final Array<Sprite> matched = new Array();
		for (int i = 0, n = this.regions.size; i < n; i++) {
			final AtlasRegion region = this.regions.get(i);
			if (region.name.equals(name)) {
				matched.add(this.newSprite(region));
			}
		}
		return matched;
	}

	private GdxSprite newSprite (final AtlasRegion region) {
		if (region.packedWidth == region.originalWidth && region.packedHeight == region.originalHeight) {
			if (region.rotate) {
				final GdxSprite sprite = new GdxSprite(region);
				sprite.setBounds(0, 0, region.getRegionHeight(), region.getRegionWidth());
				sprite.rotate90(true);
				return sprite;
			}
			return new GdxSprite(region);
		}
		return new AtlasSprite(region);
	}

	/** Returns the first region found with the specified name as a {@link NinePatch}. The region must have been packed with
	 * ninepatch splits. This method uses string comparison to find the region and constructs a new ninepatch, so the result should
	 * be cached rather than calling this method multiple times.
	 * 
	 * @return The ninepatch, or null. */
	public NinePatch createPatch (final String name) {
		for (int i = 0, n = this.regions.size; i < n; i++) {
			final AtlasRegion region = this.regions.get(i);
			if (region.name.equals(name)) {
				final int[] splits = region.splits;
				if (splits == null) {
					throw new IllegalArgumentException("Region does not have ninepatch splits: " + name);
				}
				final NinePatch patch = new NinePatch(region, splits[0], splits[1], splits[2], splits[3]);
				if (region.pads != null) {
					patch.setPadding(region.pads[0], region.pads[1], region.pads[2], region.pads[3]);
				}
				return patch;
			}
		}
		return null;
	}

	/** @return the textures of the pages, unordered */
	public ObjectSet<AtlasTexturePage> getTextures () {
		return this.textures;
	}

	/** Releases all resources associated with this TextureAtlas instance. This releases all the textures backing all
	 * TextureRegions and Sprites, which should no longer be used after calling dispose. */
	@Override
	public void dispose () {
		for (final AtlasTexturePage texture : this.textures) {
			texture.getTexture().dispose();
		}
		this.textures.clear();
	}

	static final Comparator<GdxAtlasRegionData> indexComparator = new Comparator<GdxAtlasRegionData>() {
		@Override
		public int compare (final GdxAtlasRegionData region1, final GdxAtlasRegionData region2) {
			int i1 = region1.index;
			if (i1 == -1) {
				i1 = Integer.MAX_VALUE;
			}
			int i2 = region2.index;
			if (i2 == -1) {
				i2 = Integer.MAX_VALUE;
			}
			return i1 - i2;
		}
	};

	static String readValue (final BufferedReader reader) throws IOException {
		final String line = reader.readLine();
		final int colon = line.indexOf(':');
		if (colon == -1) {
			throw new GdxRuntimeException("Invalid line: " + line);
		}
		return line.substring(colon + 1).trim();
	}

	/** Returns the number of tuple values read (1, 2 or 4). */
	static int readTuple (final BufferedReader reader) throws IOException {
		final String line = reader.readLine();
		final int colon = line.indexOf(':');
		if (colon == -1) {
			throw new GdxRuntimeException("Invalid line: " + line);
		}
		int i = 0, lastMatch = colon + 1;
		for (i = 0; i < 3; i++) {
			final int comma = line.indexOf(',', lastMatch);
			if (comma == -1) {
				break;
			}
			tuple[i] = line.substring(lastMatch, comma).trim();
			lastMatch = comma + 1;
		}
		tuple[i] = line.substring(lastMatch).trim();
		return i + 1;
	}

	/** Describes the region of a packed image and provides information about the original image before it was packed. */
	static public class AtlasRegion extends TextureRegion {
		/** The number at the end of the original image file name, or -1 if none. <br>
		 * <br>
		 * When sprites are packed, if the original file name ends with a number, it is stored as the index and is not considered as
		 * part of the sprite's name. This is useful for keeping animation frames in order.
		 * 
		 * @see CompressedGdxTextureAtlas#findRegions(String) */
		public int index;

		/** The name of the original image file, up to the first underscore. Underscores denote special instructions to the texture
		 * packer. */
		public String name;

		/** The offset from the left of the original image to the left of the packed image, after whitespace was removed for
		 * packing. */
		public float offsetX;

		/** The offset from the bottom of the original image to the bottom of the packed image, after whitespace was removed for
		 * packing. */
		public float offsetY;

		/** The width of the image, after whitespace was removed for packing. */
		public int packedWidth;

		/** The height of the image, after whitespace was removed for packing. */
		public int packedHeight;

		/** The width of the image, before whitespace was removed and rotation was applied for packing. */
		public int originalWidth;

		/** The height of the image, before whitespace was removed for packing. */
		public int originalHeight;

		/** If true, the region has been rotated 90 degrees counter clockwise. */
		public boolean rotate;

		/** The ninepatch splits, or null if not a ninepatch. Has 4 elements: left, right, top, bottom. */
		public int[] splits;

		/** The ninepatch pads, or null if not a ninepatch or the has no padding. Has 4 elements: left, right, top, bottom. */
		public int[] pads;

		private Texture alphaTexture;

		public AtlasRegion (final Texture texture, final int x, final int y, final int width, final int height) {
			super(texture, x, y, width, height);
			this.originalWidth = width;
			this.originalHeight = height;
			this.packedWidth = width;
			this.packedHeight = height;
		}

		public void setAlphaTexture (final Texture alphaTexture) {
			this.alphaTexture = alphaTexture;
		}

		public AtlasRegion (final AtlasRegion region) {
			this.setRegion(region);
			this.index = region.index;
			this.name = region.name;
			this.offsetX = region.offsetX;
			this.offsetY = region.offsetY;
			this.packedWidth = region.packedWidth;
			this.packedHeight = region.packedHeight;
			this.originalWidth = region.originalWidth;
			this.originalHeight = region.originalHeight;
			this.rotate = region.rotate;
			this.splits = region.splits;
		}

		@Override
		/** Flips the region, adjusting the offset so the image appears to be flip as if no whitespace has been removed for
		 * packing. */
		public void flip (final boolean x, final boolean y) {
			super.flip(x, y);
			if (x) {
				this.offsetX = this.originalWidth - this.offsetX - this.getRotatedPackedWidth();
			}
			if (y) {
				this.offsetY = this.originalHeight - this.offsetY - this.getRotatedPackedHeight();
			}
		}

		/** Returns the packed width considering the rotate value, if it is true then it returns the packedHeight, otherwise it
		 * returns the packedWidth. */
		public float getRotatedPackedWidth () {
			return this.rotate ? this.packedHeight : this.packedWidth;
		}

		/** Returns the packed height considering the rotate value, if it is true then it returns the packedWidth, otherwise it
		 * returns the packedHeight. */
		public float getRotatedPackedHeight () {
			return this.rotate ? this.packedWidth : this.packedHeight;
		}

		@Override
		public String toString () {
			return this.name;
		}

		public Texture getAlphaTexture () {
			return this.alphaTexture;
		}
	}

	/** A sprite that, if whitespace was stripped from the region when it was packed, is automatically positioned as if whitespace
	 * had not been stripped. */
	static public class AtlasSprite extends GdxSprite {
		final AtlasRegion region;
		float originalOffsetX, originalOffsetY;

		public AtlasSprite (final AtlasRegion region) {
			this.region = new AtlasRegion(region);
			this.originalOffsetX = region.offsetX;
			this.originalOffsetY = region.offsetY;
			this.setRegion(region);
			this.setOrigin(region.originalWidth / 2f, region.originalHeight / 2f);
			final int width = region.getRegionWidth();
			final int height = region.getRegionHeight();
			if (region.rotate) {
				super.rotate90(true);
				super.setBounds(region.offsetX, region.offsetY, height, width);
			} else {
				super.setBounds(region.offsetX, region.offsetY, width, height);
			}
			this.setColor(1, 1, 1, 1);
		}

		public AtlasSprite (final AtlasSprite sprite) {
			this.region = sprite.region;
			this.originalOffsetX = sprite.originalOffsetX;
			this.originalOffsetY = sprite.originalOffsetY;
			this.set(sprite);
		}

		@Override
		public void setPosition (final float x, final float y) {
			super.setPosition(x + this.region.offsetX, y + this.region.offsetY);
		}

		@Override
		public void setX (final float x) {
			super.setX(x + this.region.offsetX);
		}

		@Override
		public void setY (final float y) {
			super.setY(y + this.region.offsetY);
		}

		@Override
		public void setBounds (final float x, final float y, final float width, final float height) {
			final float widthRatio = width / this.region.originalWidth;
			final float heightRatio = height / this.region.originalHeight;
			this.region.offsetX = this.originalOffsetX * widthRatio;
			this.region.offsetY = this.originalOffsetY * heightRatio;
			final int packedWidth = this.region.rotate ? this.region.packedHeight : this.region.packedWidth;
			final int packedHeight = this.region.rotate ? this.region.packedWidth : this.region.packedHeight;
			super.setBounds(x + this.region.offsetX, y + this.region.offsetY, packedWidth * widthRatio, packedHeight * heightRatio);
		}

		@Override
		public void setSize (final float width, final float height) {
			this.setBounds(this.getX(), this.getY(), width, height);
		}

		@Override
		public void setOrigin (final float originX, final float originY) {
			super.setOrigin(originX - this.region.offsetX, originY - this.region.offsetY);
		}

		@Override
		public void setOriginCenter () {
			super.setOrigin(this.getWidth() / 2 - this.region.offsetX, this.getHeight() / 2 - this.region.offsetY);
		}

		@Override
		public void flip (final boolean x, final boolean y) {
			// Flip texture.
			if (this.region.rotate) {
				super.flip(y, x);
			} else {
				super.flip(x, y);
			}

			final float oldOriginX = this.getOriginX();
			final float oldOriginY = this.getOriginY();
			final float oldOffsetX = this.region.offsetX;
			final float oldOffsetY = this.region.offsetY;

			final float widthRatio = this.getWidthRatio();
			final float heightRatio = this.getHeightRatio();

			this.region.offsetX = this.originalOffsetX;
			this.region.offsetY = this.originalOffsetY;
			this.region.flip(x, y); // Updates x and y offsets.
			this.originalOffsetX = this.region.offsetX;
			this.originalOffsetY = this.region.offsetY;
			this.region.offsetX *= widthRatio;
			this.region.offsetY *= heightRatio;

			// Update position and origin with new offsets.
			this.translate(this.region.offsetX - oldOffsetX, this.region.offsetY - oldOffsetY);
			this.setOrigin(oldOriginX, oldOriginY);
		}

		@Override
		public void rotate90 (final boolean clockwise) {
			// Rotate texture.
			super.rotate90(clockwise);

			final float oldOriginX = this.getOriginX();
			final float oldOriginY = this.getOriginY();
			final float oldOffsetX = this.region.offsetX;
			final float oldOffsetY = this.region.offsetY;

			final float widthRatio = this.getWidthRatio();
			final float heightRatio = this.getHeightRatio();

			if (clockwise) {
				this.region.offsetX = oldOffsetY;
				this.region.offsetY = this.region.originalHeight * heightRatio - oldOffsetX - this.region.packedWidth * widthRatio;
			} else {
				this.region.offsetX = this.region.originalWidth * widthRatio - oldOffsetY - this.region.packedHeight * heightRatio;
				this.region.offsetY = oldOffsetX;
			}

			// Update position and origin with new offsets.
			this.translate(this.region.offsetX - oldOffsetX, this.region.offsetY - oldOffsetY);
			this.setOrigin(oldOriginX, oldOriginY);
		}

		@Override
		public float getX () {
			return super.getX() - this.region.offsetX;
		}

		@Override
		public float getY () {
			return super.getY() - this.region.offsetY;
		}

		@Override
		public float getOriginX () {
			return super.getOriginX() + this.region.offsetX;
		}

		@Override
		public float getOriginY () {
			return super.getOriginY() + this.region.offsetY;
		}

		@Override
		public float getWidth () {
			return super.getWidth() / this.region.getRotatedPackedWidth() * this.region.originalWidth;
		}

		@Override
		public float getHeight () {
			return super.getHeight() / this.region.getRotatedPackedHeight() * this.region.originalHeight;
		}

		public float getWidthRatio () {
			return super.getWidth() / this.region.getRotatedPackedWidth();
		}

		public float getHeightRatio () {
			return super.getHeight() / this.region.getRotatedPackedHeight();
		}

		public AtlasRegion getAtlasRegion () {
			return this.region;
		}

		@Override
		public String toString () {
			return this.region.toString();
		}
	}

	public Array<GdxSprite> createAlphaSprites () {
		final Array<GdxSprite> sprites = new Array<GdxSprite>(this.alphaRegions.size);
		GdxSprite sprite;
		for (int i = 0, n = this.alphaRegions.size; i < n; i++) {
			final AtlasRegion region = this.alphaRegions.get(i);
			// sprite = newSprite(region);
			sprite = this.newSprite(region);
			sprites.add(sprite);
		}
		return sprites;
	}

}
