
package com.jfixby.scarabei.adopted.gdx.fs;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StreamUtils;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FileInputStream;
import com.jfixby.scarabei.api.file.FileSystem;
import com.jfixby.scarabei.api.java.ByteArray;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.util.path.AbsolutePath;

public class ToGdxFileAdaptor extends FileHandle {

	private final File fixby_file;

	@Override
	public String path () {
		this.err("path");
		return super.path();
	}

	@Override
	public String name () {
		return this.fixby_file.getName();
	}

	@Override
	public OutputStream write (final boolean append) {
		this.err("write");
		return super.write(append);
	}

	@Override
	public String extension () {
		this.err("extension");
		return super.extension();
	}

	@Override
	public String nameWithoutExtension () {
		return this.fixby_file.nameWithoutExtension();
	}

	@Override
	public String pathWithoutExtension () {
		// L.d(super.pathWithoutExtension());
		// err("pathWithoutExtension");
		return this.fixby_file.getName();
	}

	@Override
	public FileType type () {
		this.err("type");
		return super.type();
	}

	@Override
	public InputStream read () {
		try {
			final FileSystem filesystem = this.fixby_file.getFileSystem();
			FileInputStream is;
			is = filesystem.newFileInputStream(this.fixby_file);
			is.open();
			final ByteArray data = is.readAll();
			is.close();
			return new ByteArrayInputStream(data.toArray());
		} catch (final IOException e) {
			e.printStackTrace();
			Err.throwNotImplementedYet();
		}
		return null;
	}

	@Override
	public BufferedInputStream read (final int bufferSize) {
		this.err("read");
		return super.read(bufferSize);
	}

	@Override
	public Reader reader () {
		this.err("reader");
		return super.reader();
	}

	@Override
	public Reader reader (final String charset) {
		this.L("reader");
		final InputStream stream = this.read();
		try {
			return new InputStreamReader(stream, charset);
		} catch (final UnsupportedEncodingException ex) {
			StreamUtils.closeQuietly(stream);
			throw new GdxRuntimeException("Error reading file: " + this, ex);
		}
	}

	@Override
	public BufferedReader reader (final int bufferSize) {
		this.err("reader");
		return super.reader(bufferSize);
	}

	@Override
	public BufferedReader reader (final int bufferSize, final String charset) {
		this.err("reader");
		return super.reader(bufferSize, charset);
	}

	@Override
	public String readString () {
		this.err("readString");
		return super.readString();
	}

	@Override
	public String readString (final String charset) {
		this.err("readString");
		return super.readString(charset);
	}

	@Override
	public byte[] readBytes () {
		// L("readBytes()");
		try {
			return this.fixby_file.readBytes().toArray();
		} catch (final IOException e) {
			L.e(e + "");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int readBytes (final byte[] bytes, final int offset, final int size) {
		this.err("readBytes byte[] bytes");
		return super.readBytes(bytes, offset, size);
	}

	@Override
	public OutputStream write (final boolean append, final int bufferSize) {
		this.err("write");
		return super.write(append, bufferSize);
	}

	@Override
	public void write (final InputStream input, final boolean append) {
		this.err("write");
		super.write(input, append);
	}

	@Override
	public Writer writer (final boolean append) {
		this.err("writer");
		return super.writer(append);
	}

	@Override
	public Writer writer (final boolean append, final String charset) {
		this.err("writer");
		return super.writer(append, charset);
	}

	@Override
	public void writeString (final String string, final boolean append) {
		this.err("writeString");
		super.writeString(string, append);
	}

	@Override
	public void writeString (final String string, final boolean append, final String charset) {
		this.err("writeString");
		super.writeString(string, append, charset);
	}

	@Override
	public void writeBytes (final byte[] bytes, final boolean append) {
		this.err("writeBytes");
		super.writeBytes(bytes, append);
	}

	@Override
	public void writeBytes (final byte[] bytes, final int offset, final int length, final boolean append) {
		this.err("writeBytes");
		super.writeBytes(bytes, offset, length, append);
	}

	@Override
	public FileHandle[] list () {
		this.err("list");
		return super.list();
	}

	@Override
	public FileHandle[] list (final FileFilter filter) {
		this.err("list");
		return super.list(filter);
	}

	@Override
	public FileHandle[] list (final FilenameFilter filter) {
		this.err("list");
		return super.list(filter);
	}

	@Override
	public FileHandle[] list (final String suffix) {
		this.err("list");
		return super.list(suffix);
	}

	@Override
	public boolean isDirectory () {
		this.err("isDirectory");
		return super.isDirectory();
	}

	@Override
	public FileHandle child (final String name) {
		this.L("child: " + name);
		return new ToGdxFileAdaptor(this.fixby_file.child(name));
	}

	@Override
	public FileHandle sibling (final String name) {
		this.err("sibling");
		return super.sibling(name);
	}

	@Override
	public ToGdxFileAdaptor parent () {
		this.L("parent");
		return new ToGdxFileAdaptor(this.fixby_file.parent());
	}

	@Override
	public void mkdirs () {
		this.L("mkdirs");
		try {
			this.fixby_file.makeFolder();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	private void L (final String string) {
		// L.d(string);
	}

	@Override
	public boolean exists () {
		this.L("exists");
		try {
			return this.fixby_file.exists();
		} catch (final IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete () {
		this.err("delete");
		return super.delete();
	}

	@Override
	public boolean deleteDirectory () {
		this.err("deleteDirectory");
		return super.deleteDirectory();
	}

	@Override
	public void copyTo (final FileHandle dest) {
		this.err("dest");
		super.copyTo(dest);
	}

	@Override
	public void moveTo (final FileHandle dest) {
		this.err("dest");
		super.moveTo(dest);
	}

	@Override
	public long length () {
		try {
			return this.fixby_file.getSize();
		} catch (final IOException e) {
			e.printStackTrace();
			return 0;
		}
		// err("length");
		// return super.length();
	}

	@Override
	public long lastModified () {
		this.err("lastModified");
		return super.lastModified();
	}

	@Override
	public boolean equals (final Object obj) {
		this.err("obj");
		return super.equals(obj);
	}

	@Override
	public int hashCode () {
		this.err("hashCode");
		return super.hashCode();
	}

	@Override
	public String toString () {
		this.L("ToGdxFileAdaptor.toString");
		return this.fixby_file.toString();
		// err("toString");
		// return super.toString();
	}

	private void err (final String msg) {
		Err.reportError(msg + ": not implemented yet");
	}

	public ToGdxFileAdaptor (final File fokker_file) {
		this.fixby_file = fokker_file;
		// L.d("ToGdxFileAdaptor", fokker_file + " " + this.exists());
	}

	public AbsolutePath<FileSystem> getPath () {
		return this.fixby_file.getAbsoluteFilePath();
	}

	public File getFixbyFile () {
		return this.fixby_file;
	}

}
