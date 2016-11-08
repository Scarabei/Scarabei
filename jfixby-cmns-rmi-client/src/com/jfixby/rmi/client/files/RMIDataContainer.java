
package com.jfixby.rmi.client.files;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.cmns.api.util.path.RelativePath;
import com.jfixby.rmi.api.files.RMIFilesDataContainer;
import com.jfixby.rmi.client.RMIClient;

public class RMIDataContainer extends RMIClient<RMIFilesDataContainer> {

	public RMIDataContainer (final String ip_name, final int port, final String postOfficeId) {
		super(ip_name, port, postOfficeId);
	}

	public boolean delete (final RelativePath relativePath) {
		try {
			return this.lookup().delete(relativePath.steps().toJavaList());
		} catch (final MalformedURLException e) {
			Err.reportError(e);
		} catch (final RemoteException e) {
			Err.reportError(e);
		} catch (final NotBoundException e) {
			Err.reportError(e);
		}
		return false;
	}

	public boolean isFolder (final RelativePath relativePath) throws IOException {
		try {
			return this.lookup().isFolder(relativePath.steps().toJavaList());
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			Err.reportError(e);
		}
		return false;
	}

	public boolean isFile (final RelativePath relativePath) throws IOException {
		try {
			return this.lookup().isFile(relativePath.steps().toJavaList());
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			Err.reportError(e);
		}
		return false;
	}

	public boolean exists (final RelativePath relativePath) throws IOException {
		try {
			return this.lookup().exists(relativePath.steps().toJavaList());
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			Err.reportError(e);
		}
		return false;
	}

	public List<String> listChildren (final RelativePath relativePath) {
		try {
			return Collections.newList(this.lookup().listChildren(relativePath.steps().toJavaList()));
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			Err.reportError(e);
		}
		return null;
	}

	public boolean mkdirs (final RelativePath relativePath) {
		try {
			return this.lookup().mkdirs(relativePath.steps().toJavaList());
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			Err.reportError(e);
		}
		return false;
	}

	public void rename (final RelativePath relativePath, final String new_name) {
		try {
			this.lookup().rename(relativePath.steps().toJavaList(), new_name);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			Err.reportError(e);
		}

	}

	public long lastModified (final RelativePath relativePath) throws IOException {
		try {
			return this.lookup().lastModified(relativePath.steps().toJavaList());
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			Err.reportError(e);
		}
		return -1;
	}

	public long getSize (final RelativePath relativePath) throws IOException {
		try {
			return this.lookup().getSize(relativePath.steps().toJavaList());
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			Err.reportError(e);
		}
		return -1;
	}

	public FileInputStream getInputStream (final RelativePath relativePath) {
		return new RMIFileInputStream(this, relativePath);
	}

	public RMIFileOutputStream getOutputStream (final RelativePath relativePath, final boolean append) {
		return new RMIFileOutputStream(this, relativePath, append);
	}

}
