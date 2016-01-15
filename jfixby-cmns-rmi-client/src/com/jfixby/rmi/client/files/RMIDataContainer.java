package com.jfixby.rmi.client.files;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.util.path.RelativePath;
import com.jfixby.rmi.api.files.RMIFilesDataContainer;
import com.jfixby.rmi.client.RMIClient;

public class RMIDataContainer extends RMIClient<RMIFilesDataContainer> {

	public RMIDataContainer(String ip_name, int port, String postOfficeId) {
		super(ip_name, port, postOfficeId);
	}

	public boolean delete(RelativePath relativePath) {
		try {
			return this.lookup().delete(relativePath.steps().toJavaList());
		} catch (MalformedURLException e) {
			Err.reportError(e);
		} catch (RemoteException e) {
			Err.reportError(e);
		} catch (NotBoundException e) {
			Err.reportError(e);
		}
		return false;
	}

	public boolean isFolder(RelativePath relativePath) {
		try {
			return this.lookup().isFolder(relativePath.steps().toJavaList());
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			Err.reportError(e);
		}
		return false;
	}

	public boolean isFile(RelativePath relativePath) {
		try {
			return this.lookup().isFile(relativePath.steps().toJavaList());
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			Err.reportError(e);
		}
		return false;
	}

	public boolean exists(RelativePath relativePath) {
		try {
			return this.lookup().exists(relativePath.steps().toJavaList());
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			Err.reportError(e);
		}
		return false;
	}

	public List<String> listChildren(RelativePath relativePath) {
		try {
			return Collections.newList(this.lookup().listChildren(relativePath.steps().toJavaList()));
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			Err.reportError(e);
		}
		return null;
	}

	public boolean mkdirs(RelativePath relativePath) {
		try {
			return this.lookup().mkdirs(relativePath.steps().toJavaList());
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			Err.reportError(e);
		}
		return false;
	}

	public void rename(RelativePath relativePath, String new_name) {
		try {
			this.lookup().rename(relativePath.steps().toJavaList(), new_name);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			Err.reportError(e);
		}

	}

	public long lastModified(RelativePath relativePath) {
		try {
			return this.lookup().lastModified(relativePath.steps().toJavaList());
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			Err.reportError(e);
		}
		return -1;
	}

	public long getSize(RelativePath relativePath) {
		try {
			return this.lookup().getSize(relativePath.steps().toJavaList());
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			Err.reportError(e);
		}
		return -1;
	}

	public FileInputStream getInputStream(RelativePath relativePath) throws IOException {
		try {
			byte[] data = this.lookup().readDataFromFile(relativePath.steps().toJavaList());
			return (FileInputStream) IO.newBufferInputStream(IO.newBuffer(data));
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			throw new IOException(e);
		}
	}

	public RMIFileOutputStream getOutputStream(RelativePath relativePath) throws IOException {
		return new RMIFileOutputStream(this, relativePath);
	}

}
