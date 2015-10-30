package com.jfixby.cmns.adopted.gdx.fs;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
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
import com.jfixby.cmns.api.filesystem.File;
import com.jfixby.cmns.api.filesystem.FileInputStream;
import com.jfixby.cmns.api.filesystem.FileSystem;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.path.AbsolutePath;

public class ToGdxFileAdaptor extends FileHandle {

	private File fokker_file;

	@Override
	public String path() {
		err("path");
		return super.path();
	}

	@Override
	public String name() {
		return this.fokker_file.getName();
	}

	@Override
	public String extension() {
		err("extension");
		return super.extension();
	}

	@Override
	public String nameWithoutExtension() {
		return fokker_file.nameWithoutExtension();
	}

	@Override
	public String pathWithoutExtension() {
		// L.d(super.pathWithoutExtension());
		// err("pathWithoutExtension");
		return this.fokker_file.getName();
	}

	@Override
	public FileType type() {
		err("type");
		return super.type();
	}

	@Override
	public InputStream read() {
		try {
			FileSystem filesystem = this.fokker_file.getFileSystem();
			FileInputStream is;
			is = filesystem.newFileInputStream(fokker_file);
			return is.toJavaInputStream();
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error();
		}
	}

	@Override
	public BufferedInputStream read(int bufferSize) {
		err("read");
		return super.read(bufferSize);
	}

	@Override
	public Reader reader() {
		err("reader");
		return super.reader();
	}

	@Override
	public Reader reader(String charset) {
		L("reader");
		InputStream stream = read();
		try {
			return new InputStreamReader(stream, charset);
		} catch (UnsupportedEncodingException ex) {
			StreamUtils.closeQuietly(stream);
			throw new GdxRuntimeException("Error reading file: " + this, ex);
		}
	}

	@Override
	public BufferedReader reader(int bufferSize) {
		err("reader");
		return super.reader(bufferSize);
	}

	@Override
	public BufferedReader reader(int bufferSize, String charset) {
		err("reader");
		return super.reader(bufferSize, charset);
	}

	@Override
	public String readString() {
		err("readString");
		return super.readString();
	}

	@Override
	public String readString(String charset) {
		err("readString");
		return super.readString(charset);
	}

	@Override
	public byte[] readBytes() {
		// L("readBytes()");
		try {
			return fokker_file.readBytes();
		} catch (IOException e) {
			L.e(e + "");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int readBytes(byte[] bytes, int offset, int size) {
		err("readBytes byte[] bytes");
		return super.readBytes(bytes, offset, size);
	}

	@Override
	public OutputStream write(boolean append) {
		err("write");
		return super.write(append);
	}

	@Override
	public OutputStream write(boolean append, int bufferSize) {
		err("write");
		return super.write(append, bufferSize);
	}

	@Override
	public void write(InputStream input, boolean append) {
		err("write");
		super.write(input, append);
	}

	@Override
	public Writer writer(boolean append) {
		err("writer");
		return super.writer(append);
	}

	@Override
	public Writer writer(boolean append, String charset) {
		err("writer");
		return super.writer(append, charset);
	}

	@Override
	public void writeString(String string, boolean append) {
		err("writeString");
		super.writeString(string, append);
	}

	@Override
	public void writeString(String string, boolean append, String charset) {
		err("writeString");
		super.writeString(string, append, charset);
	}

	@Override
	public void writeBytes(byte[] bytes, boolean append) {
		err("writeBytes");
		super.writeBytes(bytes, append);
	}

	@Override
	public void writeBytes(byte[] bytes, int offset, int length, boolean append) {
		err("writeBytes");
		super.writeBytes(bytes, offset, length, append);
	}

	@Override
	public FileHandle[] list() {
		err("list");
		return super.list();
	}

	@Override
	public FileHandle[] list(FileFilter filter) {
		err("list");
		return super.list(filter);
	}

	@Override
	public FileHandle[] list(FilenameFilter filter) {
		err("list");
		return super.list(filter);
	}

	@Override
	public FileHandle[] list(String suffix) {
		err("list");
		return super.list(suffix);
	}

	@Override
	public boolean isDirectory() {
		err("isDirectory");
		return super.isDirectory();
	}

	@Override
	public FileHandle child(String name) {
		L("child: " + name);
		return new ToGdxFileAdaptor(this.fokker_file.child(name));
	}

	@Override
	public FileHandle sibling(String name) {
		err("sibling");
		return super.sibling(name);
	}

	@Override
	public ToGdxFileAdaptor parent() {
		L("parent");
		return new ToGdxFileAdaptor(this.fokker_file.parent());
	}

	@Override
	public void mkdirs() {
		L("mkdirs");
		fokker_file.makeFolder();
	}

	private void L(String string) {
		// L.d(string);
	}

	@Override
	public boolean exists() {
		L("exists");
		return this.fokker_file.exists();
	}

	@Override
	public boolean delete() {
		err("delete");
		return super.delete();
	}

	@Override
	public boolean deleteDirectory() {
		err("deleteDirectory");
		return super.deleteDirectory();
	}

	@Override
	public void copyTo(FileHandle dest) {
		err("dest");
		super.copyTo(dest);
	}

	@Override
	public void moveTo(FileHandle dest) {
		err("dest");
		super.moveTo(dest);
	}

	@Override
	public long length() {
		return this.fokker_file.getSize();
		// err("length");
		// return super.length();
	}

	@Override
	public long lastModified() {
		err("lastModified");
		return super.lastModified();
	}

	@Override
	public boolean equals(Object obj) {
		err("obj");
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		err("hashCode");
		return super.hashCode();
	}

	@Override
	public String toString() {
		L("ToGdxFileAdaptor.toString");
		return this.fokker_file.toString();
		// err("toString");
		// return super.toString();
	}

	private void err(String msg) {
		throw new Error(msg + ": not implemented yet");
	}

	public ToGdxFileAdaptor(final File fokker_file) {
		this.fokker_file = fokker_file;
		// L.d("ToGdxFileAdaptor", fokker_file + " " + this.exists());
	}


	public AbsolutePath<FileSystem> getPath() {
		return this.fokker_file.getAbsoluteFilePath();
	}

	public File getTriplaneFile() {
		return this.fokker_file;
	}

}
