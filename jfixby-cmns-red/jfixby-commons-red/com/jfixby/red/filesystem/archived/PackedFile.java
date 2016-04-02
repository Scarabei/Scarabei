package com.jfixby.red.filesystem.archived;

import java.io.IOException;

import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.file.ChildrenList;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileHash;
import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.cmns.api.file.FileSystem;
import com.jfixby.cmns.api.file.packing.FileData;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.util.path.AbsolutePath;
import com.jfixby.cmns.api.util.path.RelativePath;
import com.jfixby.red.filesystem.AbstractRedFile;
import com.jfixby.red.filesystem.FilesList;
import com.jfixby.red.filesystem.RedFileHash;

public class PackedFile extends AbstractRedFile implements File {

    private RedPackedFileSystem virtualFileSystem;
    private AbsolutePath<FileSystem> absolute_path;
    private RelativePath relativePath;

    public PackedFile(RedPackedFileSystem virtualFileSystem, AbsolutePath<FileSystem> file_path) {
	this.virtualFileSystem = virtualFileSystem;
	this.absolute_path = file_path;
	this.relativePath = file_path.getRelativePath();
    }

    @Override
    public AbsolutePath<FileSystem> getAbsoluteFilePath() {
	return absolute_path;
    }

    @Override
    public boolean delete() {
	if (this.isFolder()) {
	    this.clearFolder();
	}
	PackedFileSystemContent content = this.virtualFileSystem.getContent();
	// L.d("deleting", this.absolute_path);
	;
	return content.delete(this.absolute_path.getRelativePath());

    }

    @Override
    public boolean isFolder() {
	PackedFileSystemContent content = this.virtualFileSystem.getContent();
	return content.isFolder(this.absolute_path.getRelativePath());
    }

    @Override
    public boolean isFile() {
	PackedFileSystemContent content = this.virtualFileSystem.getContent();
	return content.isFile(this.absolute_path.getRelativePath());
    }

    @Override
    public void clearFolder() {
	if (this.isFolder()) {
	    ChildrenList children = listChildren();
	    for (int i = 0; i < children.size(); i++) {
		File child = children.getElementAt(i);

		child.delete();
		// L.d("deleting", child.getAbsoluteFilePath());
	    }
	} else {
	    L.e("Unable to clear", absolute_path);
	    L.e("       this is not a folder.");
	}
    }

    @Override
    public String toString() {
	return "File [" + absolute_path + "]";
    }

    @Override
    public ChildrenList listChildren() {
	PackedFileSystemContent content = this.virtualFileSystem.getContent();

	if (!content.exists(relativePath)) {
	    throw new Error("File does not exist: " + absolute_path);
	}
	if (content.isFolder(relativePath)) {
	    List<String> files = Collections.newList(content.listChildren(relativePath));

	    FilesList listFiles = new FilesList();
	    for (int i = 0; i < files.size(); i++) {
		String file_i = files.getElementAt(i);

		AbsolutePath<FileSystem> absolute_file = absolute_path.child(file_i);
		;
		listFiles.add(absolute_file.getMountPoint().newFile(absolute_file));
	    }
	    // L.d("listFiles", listFiles);

	    //
	    return listFiles;
	} else {
	    throw new Error("This is not a folder: " + this.absolute_path);
	}
    }

    @Override
    public File child(String child_name) {
	return new PackedFile(this.getFileSystem(), this.absolute_path.child(child_name));
    }

    @Override
    public boolean exists() {
	PackedFileSystemContent content = this.virtualFileSystem.getContent();
	return content.exists(this.absolute_path.getRelativePath());
    }

    @Override
    public boolean makeFolder() {
	PackedFileSystemContent content = this.virtualFileSystem.getContent();
	return content.mkdirs(this.absolute_path.getRelativePath());
    }

    @Override
    public boolean rename(String new_name) {
	PackedFileSystemContent content = this.virtualFileSystem.getContent();
	content.rename(this.absolute_path.getRelativePath(), new_name);
	this.absolute_path = this.absolute_path.parent().child(new_name);
	this.relativePath = this.absolute_path.getRelativePath();
	return true;
    }

    @Override
    public String getName() {
	if (this.relativePath.isRoot()) {
	    return this.virtualFileSystem.getName();
	}
	return this.absolute_path.getName();
    }

    @Override
    public RedPackedFileSystem getFileSystem() {
	return this.virtualFileSystem;
    }

    @Override
    public String nameWithoutExtension() {
	String name = getName();
	int dotIndex = name.lastIndexOf('.');
	if (dotIndex == -1)
	    return name;
	return name.substring(0, dotIndex);
    }

    public FileData createFile() {
	if (this.relativePath.isRoot()) {
	    return null;
	}
	PackedFileSystemContent content = this.virtualFileSystem.getContent();
	if (!content.mkdirs(relativePath.parent())) {
	    return null;
	}
	return content.createFile(this.relativePath);
    }

    public FileData getContent() throws IOException {
	PackedFileSystemContent content = this.virtualFileSystem.getContent();
	return content.getContentLeaf(this.relativePath);
    }

    @Override
    public FileHash calculateHash() throws IOException {
	return new RedFileHash(this.virtualFileSystem.md5Hex(this));
    }

    @Override
    public FileInputStream newInputStream() throws IOException {
	return absolute_path.getMountPoint().newFileInputStream(this);
    }

    @Override
    public FileOutputStream newOutputStream() throws IOException {
	return absolute_path.getMountPoint().newFileOutputStream(this);
    }

    @Override
    public long getSize() {
	if (this.isFile()) {
	    try {
		return this.getContent().getSize();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    return 0;
	} else {
	    return 0;
	}
    }

    @Override
    public java.io.File toJavaFile() {
	throw new Error("The method is not supported");
    }

    @Override
    public File parent() {
	return new PackedFile(virtualFileSystem, this.absolute_path.parent());
    }

    @Override
    public long lastModified() {
	PackedFileSystemContent content = this.virtualFileSystem.getContent();
	return content.lastModified(this.absolute_path.getRelativePath());
    }

}
