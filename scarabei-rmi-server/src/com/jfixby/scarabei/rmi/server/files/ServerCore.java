
package com.jfixby.scarabei.rmi.server.files;

import java.io.IOException;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.java.ByteArray;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.util.JUtils;
import com.jfixby.scarabei.api.util.path.RelativePath;
import com.jfixby.scarabei.rmi.api.files.RMIFilesDataContainer;

class ServerCore extends UnicastRemoteObject implements RMIFilesDataContainer {
	private static final long serialVersionUID = -7042525299205791827L;

	private Registry stReg;
	private final int port;
	private final String box_name;
	private final File rootFolder;
	private final String host_name;

	public ServerCore (final String host_name, final String box_name, final int port, final File rootFolder)
		throws RemoteException {
		this.host_name = host_name;
		this.box_name = box_name;
		this.port = port;
		this.rootFolder = rootFolder;
	}

	public void start () throws RemoteException {
		this.stReg = LocateRegistry.createRegistry(this.port);
		this.stReg.rebind(this.box_name, this);
		L.d("Open:   " + this.stReg);
		L.d("			at: rmi://" + this.host_name + ":" + this.port + "/" + this.box_name);

	}

	public void stop (final boolean force) {
		try {
			UnicastRemoteObject.unexportObject(this, force);
			UnicastRemoteObject.unexportObject(this.stReg, force);
			L.d("Closed: " + this.stReg);
		} catch (final NoSuchObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean ping () throws RemoteException {
		return true;
	}

	@Override
	public boolean delete (final List<String> steps) throws RemoteException {
		final RelativePath relative = this.toRelative(steps);
		final File target = this.rootFolder.proceed(relative);
		try {
			return target.delete();
		} catch (final IOException e) {
			e.printStackTrace();
			throw new RemoteException();
		}
	}

	private RelativePath toRelative (final List<String> steps) {
		return JUtils.newRelativePath(steps);
	}

	@Override
	public boolean isFolder (final List<String> steps) throws IOException {
		final RelativePath relative = this.toRelative(steps);
		final File target = this.rootFolder.proceed(relative);
		return target.isFolder();
	}

	@Override
	public boolean isFile (final List<String> steps) throws IOException {
		final RelativePath relative = this.toRelative(steps);
		final File target = this.rootFolder.proceed(relative);
		return target.isFile();
	}

	@Override
	public boolean exists (final List<String> steps) throws IOException {
		final RelativePath relative = this.toRelative(steps);
		final File target = this.rootFolder.proceed(relative);
		return target.exists();
	}

	@Override
	public String[] listChildren (final List<String> steps) throws IOException {
		final RelativePath relative = this.toRelative(steps);
		final File target = this.rootFolder.proceed(relative);
		return target.toJavaFile().list();
	}

	@Override
	public boolean mkdirs (final List<String> steps) throws IOException {
		final RelativePath relative = this.toRelative(steps);
		final File target = this.rootFolder.proceed(relative);
		return target.parent().makeFolder();
	}

	@Override
	public boolean rename (final List<String> steps, final String new_name) throws IOException {
		final RelativePath relative = this.toRelative(steps);
		final File target = this.rootFolder.proceed(relative);
		return target.rename(new_name);
	}

	@Override
	public long lastModified (final List<String> steps) throws IOException {
		final RelativePath relative = this.toRelative(steps);
		final File target = this.rootFolder.proceed(relative);
		return target.lastModified();
	}

	@Override
	public long getSize (final List<String> steps) throws IOException {
		final RelativePath relative = this.toRelative(steps);
		final File target = this.rootFolder.proceed(relative);
		return target.getSize();
	}

	@Override
	public boolean writeDataToFile (final List<String> steps, final ByteArray data, final boolean append)
		throws RemoteException, IOException {
		final RelativePath relative = this.toRelative(steps);
		final File target = this.rootFolder.proceed(relative);
		target.writeBytes(data, append);
		return true;
	}

	@Override
	public ByteArray readDataFromFile (final List<String> steps) throws RemoteException, IOException {
		final RelativePath relative = this.toRelative(steps);
		final File target = this.rootFolder.proceed(relative);
		return target.readBytes();
	}

}
