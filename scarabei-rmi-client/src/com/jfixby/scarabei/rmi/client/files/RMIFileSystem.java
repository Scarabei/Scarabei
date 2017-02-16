
package com.jfixby.scarabei.rmi.client.files;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FileInputStream;
import com.jfixby.scarabei.api.file.FileOutputStream;
import com.jfixby.scarabei.api.file.FileSystem;
import com.jfixby.scarabei.api.util.path.AbsolutePath;
import com.jfixby.scarabei.red.filesystem.AbstractFileSystem;

public class RMIFileSystem extends AbstractFileSystem implements FileSystem {

	public static final String OS_SEPARATOR = "/";

	final private RMIDataContainer content;

	public RMIFileSystem (final RMIFileSystemConfig config) {
		final String server_host = Debug.checkNull("server_host", config.getRemoteHost());
		final int server_port = config.getRemotePort();
		final String mail_box = Debug.checkNull("mail_box", config.getMailBox());
		this.content = new RMIDataContainer(server_host, server_port, mail_box);
	}

	@Override
	public RMIFile newFile (final AbsolutePath<FileSystem> file_path) {
		if (file_path == null) {
			Err.reportError("Filepath is null.");
		}
		if (file_path.getMountPoint() != this) {
			Err.reportError("Path does not belong to this filesystem: " + file_path);
		}
		return new RMIFile(this, file_path);
	}

	@Override
	public FileOutputStream newFileOutputStream (final File output_file, final boolean append) {
		if (output_file == null) {
			Err.reportError("Output file is null.");
		}
		if (output_file.getFileSystem() != this) {
			Err.reportError("Output file does not belong to this filesystem: " + output_file);
		}
		// return new RMIFileOutputStream((RMIFile) output_file);
		final RMIFile v_file = (RMIFile)output_file;
		return v_file.getOutputStream(append);
	}

	@Override
	public FileInputStream newFileInputStream (final File input_file) {
		if (input_file == null) {
			Err.reportError("Input file is null.");
		}
		if (input_file.getFileSystem() != this) {
			Err.reportError("Input file does not belong to this filesystem: " + input_file);
		}
		final RMIFile v_file = (RMIFile)input_file;
		return v_file.getInputStream();
	}

	final private String name = "RMIFileSystem";

	public static String toNativePathString (final String string) {
		return string;
	}

	@Override
	public String toString () {
		return this.name;
	}

	public RMIDataContainer getContent () {
		return this.content;
	}

	public String getName () {
		return this.name;
	}

	public boolean ping () {
		try {
			this.content.lookup().ping();
			return true;
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			Err.reportError(e);
			return false;
		}
	}

}
