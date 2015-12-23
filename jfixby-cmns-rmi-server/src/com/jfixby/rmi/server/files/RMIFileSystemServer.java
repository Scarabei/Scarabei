package com.jfixby.rmi.server.files;

import java.io.IOException;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.api.util.path.RelativePath;
import com.jfixby.rmi.api.files.RMIFilesDataContainer;

public class RMIFileSystemServer extends UnicastRemoteObject implements RMIFilesDataContainer {
	private static final long serialVersionUID = -7042525299205791827L;
	private int port;
	private String box_name;
	private File rootFolder;
	private Registry stReg;

	public RMIFileSystemServer(RMIFileSystemServerConfig config) throws RemoteException {
		box_name = Debug.checkNull("getMailBoxName", config.getMailBoxName());
		port = Debug.checkNull("getPort", config.getPort());
		rootFolder = Debug.checkNull("getServerRootFolder", config.getServerRootFolder());
	}

	public void start() throws RemoteException {
		stReg = LocateRegistry.createRegistry(port);
		stReg.rebind(box_name, this);
		L.d("Open:   " + stReg);
		L.d("			at: rmi://localhost:" + port + "/" + box_name);

	}

	public void stop(boolean force) {
		try {
			UnicastRemoteObject.unexportObject(this, force);
			UnicastRemoteObject.unexportObject(stReg, force);
			L.d("Closed: " + stReg);
		} catch (NoSuchObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean ping() throws RemoteException {
		return true;
	}

	@Override
	public boolean delete(List<String> steps) throws RemoteException {
		RelativePath relative = toRelative(steps);
		File target = rootFolder.proceed(relative);
		return target.delete();
	}

	private RelativePath toRelative(List<String> steps) {
		return JUtils.newRelativePath(steps);
	}

	@Override
	public boolean isFolder(List<String> steps) throws RemoteException {
		RelativePath relative = toRelative(steps);
		File target = rootFolder.proceed(relative);
		return target.isFolder();
	}

	@Override
	public boolean isFile(List<String> steps) throws RemoteException {
		RelativePath relative = toRelative(steps);
		File target = rootFolder.proceed(relative);
		return target.isFile();
	}

	@Override
	public boolean exists(List<String> steps) throws RemoteException {
		RelativePath relative = toRelative(steps);
		File target = rootFolder.proceed(relative);
		return target.exists();
	}

	@Override
	public String[] listChildren(List<String> steps) throws RemoteException {
		RelativePath relative = toRelative(steps);
		File target = rootFolder.proceed(relative);
		return target.toJavaFile().list();
	}

	@Override
	public boolean mkdirs(List<String> steps) throws RemoteException {
		RelativePath relative = toRelative(steps);
		File target = rootFolder.proceed(relative);
		return target.parent().makeFolder();
	}

	@Override
	public boolean rename(List<String> steps, String new_name) throws RemoteException {
		RelativePath relative = toRelative(steps);
		File target = rootFolder.proceed(relative);
		return target.rename(new_name);
	}

	@Override
	public long lastModified(List<String> steps) throws RemoteException {
		RelativePath relative = toRelative(steps);
		File target = rootFolder.proceed(relative);
		return target.lastModified();
	}

	@Override
	public long getSize(List<String> steps) throws RemoteException {
		RelativePath relative = toRelative(steps);
		File target = rootFolder.proceed(relative);
		return target.getSize();
	}

	@Override
	public boolean writeDataToFile(List<String> steps, byte[] data) throws RemoteException, IOException {
		RelativePath relative = toRelative(steps);
		File target = rootFolder.proceed(relative);
		target.writeBytes(data);
		return true;
	}

	@Override
	public byte[] readDataFromFile(List<String> steps) throws RemoteException, IOException {
		RelativePath relative = toRelative(steps);
		File target = rootFolder.proceed(relative);
		return target.readBytes();
	}

}
