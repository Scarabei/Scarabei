
package com.jfixby.scarabei.red.net.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.CollectionScanner;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FileInputStream;
import com.jfixby.scarabei.api.file.FileOutputStream;
import com.jfixby.scarabei.api.file.FileSystem;
import com.jfixby.scarabei.api.file.FolderSupportingIndex;
import com.jfixby.scarabei.api.math.IntegerMath;
import com.jfixby.scarabei.api.net.http.Http;
import com.jfixby.scarabei.api.net.http.HttpFileSystem;
import com.jfixby.scarabei.api.net.http.HttpFileSystemSpecs;
import com.jfixby.scarabei.api.net.http.HttpURL;
import com.jfixby.scarabei.api.util.JUtils;
import com.jfixby.scarabei.api.util.path.AbsolutePath;
import com.jfixby.scarabei.api.util.path.RelativePath;
import com.jfixby.scarabei.red.filesystem.AbstractFileSystem;

class RedHttpFileSystem extends AbstractFileSystem implements FileSystem, HttpFileSystem {

	public static final String OS_SEPARATOR = "/";

	private final HttpURL url;
	final private String name;

	public RedHttpFileSystem (final HttpFileSystemSpecs specs) {
		this.url = Debug.checkNull("rootUrl", specs.getRootUrl());
		this.name = "HttpFileSystem<" + this.url + ">";
		long cache_size = specs.getCacheSize();
		cache_size = IntegerMath.limit(0, cache_size, Integer.MAX_VALUE);
		this.httpFolderDescriptorCache.setSize(cache_size);
	}

	public HttpURL getURLFor (final AbsolutePath<FileSystem> abs) {
		RelativePath relative;
		{
			relative = abs.getRelativePath();
			final Collection<String> steps = relative.steps();
			final List<String> encodedSteps = Collections.newList();
			Collections.scanCollection(steps, new CollectionScanner<String>() {
				@Override
				public void scanElement (final String e, final long i) {
					encodedSteps.add(urlEncodeString(steps.getElementAt(i)));
				}
			});
			relative = JUtils.newRelativePath(encodedSteps);
		}

		final String prefix = (this.url.getURLString());
		final String urlString = prefix + this.mid(prefix) + relative;
		final HttpURL url = Http.newURL(urlString);
		return url;
	}

	private static String urlEncodeString (final String string) {
		try {
			return URLEncoder.encode(string, "UTF-8");
		} catch (final UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String mid (final String prefix) {
		return prefix.endsWith(OS_SEPARATOR) ? "" : OS_SEPARATOR;
	}

	@Override
	public RedHttpFile newFile (final AbsolutePath<FileSystem> file_path) {
		if (file_path == null) {
			Err.reportError("Filepath is null.");
		}
		if (file_path.getMountPoint() != this) {
			Err.reportError("Path does not belong to this filesystem: " + file_path);
		}
		return new RedHttpFile(this, file_path);
	}

	@Override
	public FileOutputStream newFileOutputStream (final File output_file, final boolean append) {
		Err.reportError("Read only file system");
		return null;// final FileFilter F
	}

	@Override
	public FileInputStream newFileInputStream (final File input_file) {
		if (input_file == null) {
			Err.reportError("Input file is null.");
		}
		if (input_file.getFileSystem() != this) {
			Err.reportError("Input file does not belong to this filesystem: " + input_file);
		}

		return new RedHttpFileInputStream((RedHttpFile)input_file);
	}

	public static String toNativePathString (final String string) {
		return string;
	}

	@Override
	public String toString () {
		return this.name;
	}

	@Override
	public boolean isReadOnlyFileSystem () {
		return true;
	}

	public String getName () {
		return this.name;
	}

	final HttpFolderDescriptorCache httpFolderDescriptorCache = new HttpFolderDescriptorCache();

	public FolderSupportingIndex getCachedDescriptor (final HttpURL key) {
		return this.httpFolderDescriptorCache.get(key);
	}

	public void caheValue (final HttpURL key, final FolderSupportingIndex desc) {
// L.d("cache: " + key, desc);
		this.httpFolderDescriptorCache.put(key, desc);
	}

	public void printCache () {
		this.httpFolderDescriptorCache.print();
	}
}
